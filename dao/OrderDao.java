package cn.itcast.store.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.domain.User;

public interface OrderDao {

	void saveOrder(Connection conn, Order order) throws SQLException;

	void saveOrderItem(Connection conn, OrderItem item) throws SQLException;

	int getTotalRecords(User user) throws SQLException;

	List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception;

	 Order findOrderByOid(String oid) throws Exception;

	void updateDao(Order order) throws Exception;
	

	

}
