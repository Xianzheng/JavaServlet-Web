package cn.itcast.store.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.Product;

public interface ProductDao {

	List<Product> getHots() throws SQLException;

	List<Product> getNews() throws SQLException;

	Product getProductInfoById(String pid) throws SQLException;

	int getTotalRecord(String cid) throws SQLException;

	List<Product> getProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException;

}
