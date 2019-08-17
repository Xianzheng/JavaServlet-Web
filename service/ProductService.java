package cn.itcast.store.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.Product;

public interface ProductService {

	List<Product> getHots() throws Exception;

	List<Product> getNews() throws Exception;

	Product getProductInfoById(String pid) throws Exception;

	PageModel getProductsByCidWithPage(String cid, int cnum) throws Exception;

}
