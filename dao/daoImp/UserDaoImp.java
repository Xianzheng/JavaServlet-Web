 package cn.itcast.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.domain.User;
import cn.itcast.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	@Override
	public void userRegist(User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),
				user.getSex(),user.getState(),user.getCode()};
		System.out.println(user);
		qr.update(sql,params);
	}

	@Override
	public User userActive(String code) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from user where code = ?";
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		User user = qr.query(sql, new BeanHandler<User>(User.class),code);
		
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update user set username=?, password=?, name=?, email=?, telephone=?, birthday=?,sex=?,state=?,code=? where uid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),
				user.getSex(),user.getState(),user.getCode(),user.getUid()};
		qr.update(sql,params);
		
	}

	@Override
	public User userLogin(User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = ? and password =?";
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		User uu = qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		
		return uu;
	}

}
