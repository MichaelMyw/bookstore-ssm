package com.mjava.bookstore.controller.admin;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mjava.bookstore.domain.Book;
import com.mjava.bookstore.domain.Category;
import com.mjava.bookstore.service.BookService;
import com.mjava.bookstore.service.CategoryService;
import com.mjava.bookstore.utils.CommonUtils;

@Controller
@RequestMapping(value = "/admin/book")
public class AdminBookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 查找所有图书
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "findAllBook", method = RequestMethod.GET)
	public String findAllBook(Model model) {
		List<Book> bookList = bookService.findAllBook();
		model.addAttribute("bookList", bookList);
		return "adminjsps/admin/book/list";
	}

	/**
	 * 加载图书
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "load", method = RequestMethod.GET)
	public String load(Model model, HttpServletRequest request) {
		String bid = request.getParameter("bid");
		Book book = bookService.findBookAndBookCategory(bid);
		
		System.out.println(book);
		model.addAttribute("book", book);

		List<Category> categoryList = categoryService.findAllCategory();
		model.addAttribute("categoryList", categoryList);
		return "adminjsps/admin/book/desc";
	}

	
	@RequestMapping(value = "addBookPre", method = RequestMethod.GET)
	public String addBookPre(Model model) {
		List<Category> categoryList = categoryService.findAllCategory();
		model.addAttribute("categoryList", categoryList);
		return "adminjsps/admin/book/add";
		
		
	}
	
	
	
	
	
	/**
	 * 添加图书
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addBook", method = RequestMethod.POST)
	public String addBook(Model model, HttpServletRequest request) {
		/*
		 * 把表单数据封装到Book对象中 上传三步
		 */
		// 创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory(30 * 1024, new File("F:/f/temp"));
		// 得到解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 设置单个文件大小为30kb
		sfu.setFileSizeMax(30 * 1024);
		// 使用解析器得到request对象,得到List<FileItem>对象
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			// 把fileItemList的数据封装到Book对象中
			/*
			 * 把所有普通表单字段数据封装到map中，再把map中的数据封装到Book对象中
			 */
			Map<String, String> map = new HashMap<String, String>();
			for (FileItem fileItem : fileItemList) {
				if (fileItem.isFormField()) {
					map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				}
			}
			Book book = CommonUtils.toBean(map, Book.class);
			Category category = CommonUtils.toBean(map, Category.class);


			book.setBid(CommonUtils.uuid());
			book.setCategory(category);

			// 需要把map中的cid封装到category中，再把category赋给book
			

			/*
			 * 保存上传的文件 保存的目录 保存的文件名称
			 */

			// 得到保存的目录
			String savePath = request.getRealPath("/book_img");
			// 得到文件名称：给原来的文件名称添加uuid前缀，避免文件名冲突
			String filename = CommonUtils.uuid() + "_" + fileItemList.get(1).getName();
			// 使用目录和文件名称创建目标文件
			File deskFile = new File(savePath, filename);

			/*
			 * 校验文件的拓展名，只允许jpg
			 */
			if (!filename.toLowerCase().endsWith("jpg")) {
				request.setAttribute("msg", "您上传的图片必须要是jpg格式");
				List<Category> categoryList = categoryService.findAllCategory();
				request.setAttribute("categoryList", categoryList);
				return "adminjsps/admin/book/add";
			}

			// 保存上传文件到目标文件位置
			fileItemList.get(1).write(deskFile);

			/*
			 * 设置book对象的image，即把图片的路径设置给book的image
			 */
			book.setImage("book_img/" + filename);

			System.out.println(book);

			
			/*
			 * 使用bookservice完成保存
			 */
			bookService.add(book);

			/*
			 * 校验图片的尺寸
			 * 
			 */
			Image image = new ImageIcon(deskFile.getAbsolutePath()).getImage();
			if (image.getWidth(null) > 200 || image.getHeight(null) > 200) {
				// 删除这个文件
				deskFile.delete();
				request.setAttribute("msg", "您上传的图片尺寸超出了200*200");
				List<Category> categoryList = categoryService.findAllCategory();
				request.setAttribute("categoryList", categoryList);
				return "adminjsps/admin/book/add";
			}
			/*
			 * 返回图书列表
			 * 
			 */
		} catch (Exception e) {
			if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "您上传的文件超过了30kb");
				List<Category> categoryList = categoryService.findAllCategory();
				request.setAttribute("categoryList", categoryList);
				return "adminjsps/admin/book/add";
			}
		}
		return findAllBook(model);
	}
	
	/**
	 * 更新图书
	 * @param model
	 * @param request
	 * @param book
	 * @param category
	 * @return
	 */
	@RequestMapping(value="/editBook",method = RequestMethod.POST)
	public String editBook(Model model,HttpServletRequest request,Book book,Category category) {
		Category c = categoryService.findCategoryByCid(category.getCid());
		System.out.println(c);
		book.setCategory(c);
		System.out.println(book);
		bookService.edit(book);
		System.out.println("更新完成");
		return findAllBook(model);
	}
	
	/**
	 * 删除图书
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteBook",method = RequestMethod.POST)
	public String deleteBook(Model model,HttpServletRequest request) {
		String bid = request.getParameter("bid");
		bookService.delete(bid);
		return findAllBook(model);
	}

}
