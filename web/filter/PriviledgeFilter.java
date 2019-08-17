package cn.itcast.store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.store.domain.User;

/**
 * Servlet Filter implementation class PriviledgeFilter
 */
@WebFilter({ "/jsp/cart.jsp", "/jsp/order_info.jsp", "/jsp/order_list.jsp" })
public class PriviledgeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PriviledgeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest myReq = (HttpServletRequest)request;
		User user = (User)myReq.getSession().getAttribute("loginUser");
		if(null!=user) {
			chain.doFilter(request, response);
		}else {
			myReq.setAttribute("msg", "请用户登陆后再去访问");
			myReq.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
