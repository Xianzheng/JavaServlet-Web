package cn.itcast.store.service.serviceImp;

import java.sql.SQLException;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.daoImp.UserDaoImp;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;

public class UserServiceImp implements UserService {

	@Override
	public void userRegist(User user) throws SQLException {
		// TODO Auto-generated method stub
		UserDao UserDao = new UserDaoImp();
		UserDao.userRegist(user);
	}

	@Override
	public boolean userActive(String code) throws SQLException {
		// TODO Auto-generated method stub
		
		UserDao userDao = new UserDaoImp();
		
		User user = userDao.userActive(code);
		
		user.setState(1);
		
		user.setCode(null);
		
		{
			
			userDao.updateUser(user);
			return true;
			
		}
	}

	@Override
	public User userLogin(User user) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImp();
		
		User uu = userDao.userLogin(user);
		
		if(null == uu) {
			
			throw new RuntimeException("password is wrong!"); 
			
		}else if(uu.getState()!=1) {
			
			throw new RuntimeException("Account is not acticated yet!"); 
			
		}else {
			
			return uu;
			
		}
		
	}

}
