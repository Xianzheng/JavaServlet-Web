package cn.itcast.store.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.dao.daoImp.CategoryDaoImp;
import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;

public class CategoryServiceImp implements CategoryService {
	
	CategoryDao categoryDao = new CategoryDaoImp();
	@Override
	public List<Category> getAllCats() throws SQLException {
		// TODO Auto-generated method stub
		
		
		return categoryDao.getAllCats();
		
	}

	@Override
	public void addCategory(Category c) throws SQLException {
		// TODO Auto-generated method stub
		categoryDao.addCategory(c);
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
		
	}

}
