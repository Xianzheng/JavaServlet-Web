package cn.itcast.store.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {

	@Override
	public List<Product> getHots() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from product where pflag = 0 and is_hot =1 order by pdate desc limit 0 , 9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> getNews() throws SQLException {
		String sql = "select * from product where pflag = 0 order by pdate desc limit 0 , 9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product getProductInfoById(String pid) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from product where pid = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		
	}



	@Override
	public int getTotalRecord(String cid) throws SQLException {
		String sql="select count(*) from product where cid = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(),cid);
		return num.intValue();
		
	}

	@Override
	public List<Product> getProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException{
		// TODO Auto-generated method stub
		String sql="select * from product where cid = ? limit ? , ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid, startIndex, pageSize);
		
	}

	

	

}
