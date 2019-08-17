package cn.itcast.store.service.serviceImp;

import java.sql.Connection;
import java.util.List;

import cn.itcast.store.dao.OrderDao;
import cn.itcast.store.dao.OrderDaoImp;
import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.OrderService;
import cn.itcast.store.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {
	
	OrderDao orderDao = new OrderDaoImp();
	@Override
	public void saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
//		try {
//			JDBCUtils.startTransaction();
//			OrderDao orderDao = new OrderDaoImp();
//			orderDao.saveOrder(order);
//			for(OrderItem item:order.getList()) {
//				orderDao.saveItem(item);
//			}
//			JDBCUtils.commitAndClose();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			JDBCUtils.rollbackAndClose();
//		}
		Connection conn=null;
		try {
			
			conn=JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			OrderDao orderDao = new OrderDaoImp();
			orderDao.saveOrder(conn,order);
			for(OrderItem item:order.getList()) {
				orderDao.saveOrderItem(conn,item);
			}
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
		}finally {
			if(null!=conn) {
				conn.close();
				conn=null;
			}
		}
		
	}

	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
		// TODO Auto-generated method stub
		//CREATE PAGE MODEL WITH PARAMETER
	    //SELECT COUNT(*) FRIN ORDERS WHERE UID=?
		int totalRecords =orderDao.getTotalRecords(user);
		
		PageModel pm = new PageModel(curNum, totalRecords,3);
		
		//RELATE COLLECTION
		List list = orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setRecords(list);
		//RELTE URLi
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.findOrderByOid(oid);
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.updateDao(order);
		
	}

}
