package cn.itcast.store.service.serviceImp;

import java.util.List;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.dao.daoImp.ProductDaoImp;
import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;

public class ProductServiceImp implements ProductService  {

	ProductDao productDao = new ProductDaoImp();
	@Override
	public List<Product> getHots() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getHots();
	}

	@Override
	public List<Product> getNews() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getNews();
	}

	@Override
	public Product getProductInfoById(String pid) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProductInfoById(pid);
	}

	@Override
	public PageModel getProductsByCidWithPage(String cid, int cnum) throws Exception {
		// TODO Auto-generated method stub
		int totalRecord = productDao.getTotalRecord(cid);
		//System.out.println("total Record is" + totalRecord);
		PageModel pm = new PageModel(cnum,totalRecord,12);
		
		List<Product> list = productDao.getProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		
		pm.setRecords(list);
		
		pm.setUrl("ProductServlet?method=getProductsByCidWithPage&cid="+cid);
		
		return pm;
	}

}
