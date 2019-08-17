package cn.itcast.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Cart;
import cn.itcast.store.domain.CartItem;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.ProductServiceImp;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	public String execute(HttpServletRequest request, HttpServletResponse response) throws  Exception {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		if(null == cart) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		String pid = request.getParameter("pid");
		int num = Integer.parseInt(request.getParameter("quantity"));
		
		ProductService productService = new ProductServiceImp() ;
		Product product = productService.getProductInfoById(pid);
		
		CartItem cartItem = new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);
		
		cart.addCartItem(cartItem);
		
		response.sendRedirect("/store_v5/jsp/cart.jsp");
		return null;
		
	}
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws  Exception {
		
		String pid = request.getParameter("id");
		
		System.out.println(pid);
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		cart.removeCartItem(pid);
		
		System.out.println(pid);
		
		response.sendRedirect("/store_v5/jsp/cart.jsp");
		return null;
		
	}
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws  Exception {
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		cart.removeCart();
		
		
		response.sendRedirect("/store_v5/jsp/cart.jsp");
		return null;
	}
    

}
