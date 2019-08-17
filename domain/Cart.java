package cn.itcast.store.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Cart {
	private double total=0;
	
	Map<String, CartItem> map = new HashMap<String, CartItem>();
	
	public double getTotal() {
		total = 0;
		Collection<CartItem> values = map.values();
		for(CartItem cartItem: values)
			total+=cartItem.getSubTotal();
		return total;

	}
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	public void setTotal(double total) {
		this.total = total;
			}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public void addCartItem(CartItem cartItem) {
		String pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)) {//CHECK IF THERE IS BEFORE
			CartItem oldItem = map.get(pid);
			oldItem.setNum(oldItem.getNum()+cartItem.getNum());
		}else {
			map.put(pid, cartItem);
		}
	}
	
	public void removeCartItem(String pid) {
		map.remove(pid);
	}
	public void removeCart() {
		map.clear();
	}
	
}
