package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;
import cn.itcast.store.service.serviceImp.UserServiceImp;
import cn.itcast.store.utils.MailUtils;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;


@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/register.jsp";
		
	}
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/login.jsp";
		
	}
	
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get parameter
		/*System.out.println("1111");
		Map<String, String[]> map = request.getParameterMap();
		
		Set<String> keySet = map.keySet();
		
		Iterator<String> iterator = keySet.iterator();
		
		while(iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str);
			String[] strs = map.get(str);
			for(String s:strs) {
				System.out.println(s);
			}
		}*/
		try {
		Map<String, String[]> map = request.getParameterMap();
		
		User user = new User();
		
		UserService userService = new UserServiceImp();
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		
		MyBeanUtils.populate(user, map);
		
		System.out.println(user);
		
		
			 userService.userRegist(user);
			 
			 MailUtils.sendMail(user.getEmail(), user.getCode());
			
			request.setAttribute("msg", "Regist successfully");
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", "Regist failed");
						
		}
		return "/jsp/info.jsp";
		
	}
	
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String code = request.getParameter("code");
		
		UserService userSerive = new UserServiceImp();
		
		boolean flage = userSerive.userActive(code);
		
		if(flage == true) {
			
			request.setAttribute("msg", "activate account successfully");
			
			return "/jsp/login.jsp";
			
		}else {
			
			request.setAttribute("msg", "activate account failed");
			
			return "/jsp/info.jsp";
			
		}
	}
	
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		UserService userService = new UserServiceImp();
		
		System.out.println("aaa");
		User user = new User();
		
		MyBeanUtils.populate(user, request.getParameterMap());
		
		//System.out.println(user);
		User user02 = new User();
		
		try {
			user02 = userService.userLogin(user);
			
			request.getSession().setAttribute("loginUser", user02);

			response.sendRedirect("/store_v5/index.jsp");
			
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String msg = e.getMessage();
			
			request.setAttribute("msg", msg);
			
			return "jsp/login.jsp";
		}
		
		
	}
	
	public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.getSession().invalidate();
		
		response.sendRedirect("/store_v5/index.jsp");
	}

	
}
