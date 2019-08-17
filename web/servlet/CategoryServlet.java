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
import cn.itcast.store.utils.JedisUtils;
import cn.itcast.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;


/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
	 
	public String getAllCats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		//get all category info
		CategoryService categoryService = new CategoryServiceImp();
		
		List<Category> list = categoryService.getAllCats();// GET LIST FROM DAO OF CATEGORY
		
		String jsonStr = JSONArray.fromObject(list).toString();
		
		response.setContentType("application/json;charset=utf-8");
		
		response.getWriter().print(jsonStr);
		
		return null;
		
		
		
		/*Jedis jedis = JedisUtils.getJedis();//USE JEDIS DATABASE TO DO TMPERARY SAVE
		jedis.del("allCats");
		
		String jsonStr = jedis.get("allCats");
		
		if(null==jsonStr||"".equals(jsonStr)) {
			CategoryService categoryService = new CategoryServiceImp();
			
			List<Category> list = categoryService.getAllCats();// GET LIST FROM DAO OF CATEGORY
			
			jsonStr = JSONArray.fromObject(list).toString();
			
			jedis.set("allCats", jsonStr);//allCats from jedis to store This List
			
			response.setContentType("application/json;charset=utf-8");
			
			response.getWriter().print(jsonStr);//?????????
			
			//System.out.println("no data");
			
		}else {
			//System.out.println("get data");
			
			response.setContentType("application/json;charset=utf-8");
			
			response.getWriter().print(jsonStr);
		}
		
		return null;*/
		
		
		
	}

	

}
