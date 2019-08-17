package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.ProductServiceImp;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	 public String getProductInfoById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String pid = request.getParameter("pid");
		
		ProductService productService = new ProductServiceImp();
		
		Product product= productService.getProductInfoById(pid);
		
		request.setAttribute("p", product);
		
		return "/jsp/product_info.jsp";
	}
	
	 public String getProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 String cid = request.getParameter("cid");
		 
		 int Cnum = Integer.parseInt(request.getParameter("num"));
		 
		 ProductService productService = new ProductServiceImp();
		 
		 PageModel pm = productService.getProductsByCidWithPage(cid,Cnum);
		 	 
		// return "";
		 
		 request.setAttribute("page", pm);
		 
		 return "/jsp/product_list.jsp";
		 
	 }
}
