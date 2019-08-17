package cn.itcast.store.web.servlet;

import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Cart;
import cn.itcast.store.domain.CartItem;
import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.OrderService;
import cn.itcast.store.service.serviceImp.OrderServiceImp;
import cn.itcast.store.utils.PaymentUtil;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Confirm if User is in Login State
		
		User user = (User) request.getSession().getAttribute("loginUser");
		
		if(null==user) {
			request.setAttribute("msg", "please order after login");
			
			return "jsp/info.jsp";
		}
		
		//Create Order OBJECT and PUT VALUE TO OBJECT
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		Order order =new Order();
		
		order.setOid(UUIDUtils.getCode());	
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setUser(user);
		
		//Travelize the Item CREATE ORDER ITEM
		
		for(CartItem item:cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setQuantity(item.getNum());
			orderItem.setTotal(item.getSubTotal());
			orderItem.setProduct(item.getProduct());
			orderItem.setOrder(order);
			order.getList().add(orderItem);
		}
		
		
		//INVOKE THE SERVICE FUNCTION AND SAVE ORDER
		
		OrderService orderSerive =new OrderServiceImp();
		orderSerive.saveOrder(order);
		
		//CLEAR THE CART
		
		cart.removeCart();
		
		//PUT ORDER TO REQUEST
		
		request.setAttribute("order", order);
		
		//TRANSMIT TO JSP/ORDER_INFO
		 
		return "/jsp/order_info.jsp";
		
	}
	public String findMyOrdersWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//GET THE USER
		User user = (User)request.getSession().getAttribute("loginUser");
		//GET CURRENT PAGE
		int curNum = Integer.parseInt(request.getParameter("num"));
		//INVOKE THE FUNCTION OF SERVICE
		OrderService orderService = new OrderServiceImp();
		//SELECT * FORM ORDERS WHERE uid = ? limit ?, ?
		PageModel pm=orderService.findMyOrdersWithPage(user,curNum);
		//PUT PAGEMODEL TO REQUEST
		request.setAttribute("page", pm);
		
		return "/jsp/order_list.jsp";
	}
	public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid = request.getParameter("oid");
		OrderService orderService = new OrderServiceImp();
		Order order = orderService.findOrderByOid(oid);
		request.setAttribute("order", order);
		
		return "/jsp/order_info.jsp";
	}
	
	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid=request.getParameter("oid");
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String pd_FrpId= request.getParameter("pd_FrpId");
		
		OrderService OrderService = new OrderServiceImp();
		Order order = OrderService.findOrderByOid(oid);
	
		order.setName(name);
		order.setTelephone(telephone);
		order.setAddress(address); 
		OrderService.updateOrder(order);
		String p0_Cmd = "Buy";
		
		String p1_MerId = "10001126856";
		
		String p2_Order = oid;
		
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		
		String p8_Url = "http://localhost:8080/store_v5/OrderServlet?method=callBack";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
			
		
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
				
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		System.out.println(sb.toString());
		// 浣跨敤閲嶅畾鍚戯細
		response.sendRedirect(sb.toString());
		return null;
		
	}
	public String callBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");

		// hmac
		String hmac = request.getParameter("hmac");
		// 鍒╃敤鏈湴瀵嗛挜鍜屽姞瀵嗙畻娉� 鍔犲瘑鏁版嵁
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 鏈夋晥
			if (r9_BType.equals("1")) {
				OrderService orderService = new OrderServiceImp();
				Order order = orderService.findOrderByOid(r6_Order);
				order.setState(2);
				orderService.updateOrder(order);
				request.setAttribute("msg", "支付成功！订单号，"+r6_Order+"金额"+r3_Amt);
				
				// 娴忚鍣ㄩ噸瀹氬悜
				return "/jsp/info.jsp";
			} else if (r9_BType.equals("2")) {
				// 淇敼璁㈠崟鐘舵��:
				// 鏈嶅姟鍣ㄧ偣瀵圭偣锛屾潵鑷簬鏄撳疂鐨勯�氱煡
				System.out.println("鏀跺埌鏄撳疂閫氱煡锛屼慨鏀硅鍗曠姸鎬侊紒");//
				// 鍥炲缁欐槗瀹漵uccess锛屽鏋滀笉鍥炲锛屾槗瀹濅細涓�鐩撮�氱煡
				response.getWriter().print("success");
			}

		} else {
			throw new RuntimeException("鏁版嵁琚鏀癸紒");
		}
		return "/jsp/info/jsp";
	}
	


}
