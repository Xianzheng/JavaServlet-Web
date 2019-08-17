package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.serviceImp.CategoryServiceImp;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminCategoryServlet
 */
@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
	
	/**
	 * @throws SQLException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CategoryService categoryService = new CategoryServiceImp();
		List<Category> list = categoryService.getAllCats();
		request.setAttribute("allCats", list);
		return "/admin/category/list.jsp";
	}
	
	public String addCategoryUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/admin/category/add.jsp";
		
	}
	public String addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("adfasdfsadfasdfsafds");
		String cname = request.getParameter("cname");
		String id = UUIDUtils.getId();
		Category c =new Category();
		c.setCid(id);
		c.setCname(cname);
		CategoryService categoryService = new CategoryServiceImp();
		categoryService.addCategory(c);
		response.sendRedirect("/store_v5/AdminCategoryServlet?method=findAllCats");
		return null;
		
	}

}
