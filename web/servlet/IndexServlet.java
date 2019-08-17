package cn.itcast.store.web.servlet;


import java.util.List;


import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.CategoryServiceImp;
import cn.itcast.store.service.serviceImp.ProductServiceImp;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws  Exception {
		
		CategoryService categoryService = new CategoryServiceImp();
		
		List<Category> list = categoryService.getAllCats();
		
		request.setAttribute("allCats", list);
		
		ProductService productService = new ProductServiceImp();
		
		List<Product> list01 = productService.getHots();
		
		List<Product> list02 = productService.getNews();
		
		request.setAttribute("hots", list01);
		
		request.setAttribute("news", list02);
		
		return "/jsp/index.jsp";
		
	}

}
