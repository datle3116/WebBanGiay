package web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.entity.Account;
import web.entity.OrderDetail;
import web.entity.Product;
import web.util.MyGenerator;

@Transactional
@Controller
public class LoginController {
	Account account = new Account();
	List<Product> listProduct = new ArrayList<>();

	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		model.addAttribute("account", new Account());
		return "client/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("account") Account account, HttpServletRequest request, ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Account";
		Query query = session.createQuery(hql);
		List<Account> listAcc = query.list();
		

		for (Account ktra : listAcc) {
			if (account.getUsername().equals(ktra.getUsername()) && account.getPassword().equals(ktra.getPassword())) {
				this.account = account;
				return "redirect:/admin/management.htm";
			} else {
				model.addAttribute("message", "Đăng nhập thất bại!");
			}
		}

		return "client/login";
	}

	//Trang chu ADMIN
	@RequestMapping(value = "/admin/management")
	public String management(ModelMap model) {
		updateListProd();
		model.addAttribute("account", this.account);
		model.addAttribute("list", this.listProduct);
		return "/admin/management";

	}

	@RequestMapping(value = "/admin/adminEdit/{code}", method = RequestMethod.GET)
	public String adminEdit(ModelMap model, @PathVariable("code") String code) {
		Product ediProd = findProdByCode(code);
		model.addAttribute("account", this.account);
		model.addAttribute("ediProd", ediProd);
		return "/admin/adminEdit";
	}

	@RequestMapping(value = "/admin/adminEdit/{code}", method = RequestMethod.POST)
	public String adminEdit(ModelMap model, @RequestParam(value="prodimage") MultipartFile image,
			@ModelAttribute("ediProd") Product ediProd) {
		Product prod = findProdByCode(ediProd.getCode());
		
		if (image.isEmpty()) {
			System.out.println("Lỗi upload ảnh");
		} else {
			saveImage(image);
			prod.setImage(image.getOriginalFilename());
		}
		prod.setName(ediProd.getName());
		prod.setCreateDate(new Date());
		prod.setPrice(ediProd.getPrice());
		prod.setType(ediProd.getType());
		updateProd(prod);

		return "redirect:/admin/management.htm";
	}
	
	@RequestMapping(value = "/admin/insert", method = RequestMethod.GET)
	public String adminInsert(ModelMap model) {
		Product ediProd = new Product();
		if(findProductCode() == null) {
			ediProd.setCode("S001");
		} else {
			ediProd.setCode(MyGenerator.generateProductId(findProductCode().getCode()));
		}
		model.addAttribute("account", this.account);
		model.addAttribute("ediProd", ediProd);
		return "/admin/insert";
	}

	@RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
	public String adminInsert(ModelMap model, @RequestParam(value="prodimage") MultipartFile image,
			@ModelAttribute("ediProd") Product ediProd) {
		Product prod = new Product();
		
		if (image.isEmpty()) {
			System.out.println("Lỗi upload ảnh");
		} else {
			saveImage(image);
			prod.setImage(image.getOriginalFilename());
		}
		prod.setCode(ediProd.getCode());
		prod.setName(ediProd.getName());
		prod.setCreateDate(new Date());
		prod.setPrice(ediProd.getPrice());
		prod.setType(ediProd.getType());
		saveProd(prod);

		return "redirect:/admin/management.htm";
	}
	
	//Xoa Product
	@RequestMapping(value = "/admin/delete/{code}")
	public String delete(ModelMap model, @PathVariable("code") String code) {
		Product delete = findProdByCode(code);
		if(findProductCode(delete.getCode()) == true) {
			model.addAttribute("message", "Không thể xóa: Hàng này đang được đặt hàng, hãy xong đơn hàng trong danh sách đơn đặt hàng");
			model.addAttribute("account", this.account);
			model.addAttribute("list", this.listProduct);
			return "/admin/management";
		}
		deleteProd(delete);
		deleteImage(delete.getImage());
		return "redirect:/admin/management.htm";

	}
	
	public boolean findProductCode(String code) {
		boolean kq = false;
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderDetail";
		Query query = session.createQuery(hql);
		
		List<OrderDetail> list = query.list();
		for(OrderDetail find : list) {
			if(code.equals(find.getProduct().getCode())) {
				kq = true;
				break;
			}
		}
		return kq;
	}
	
	public void deleteProd(Product deleteProd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(deleteProd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Product findProdByCode(String code) {
		Product findProd = null;

		for (Product prod : this.listProduct) {
			if (code.equals(prod.getCode())) {
				findProd = prod;
				break;
			}
		}
		return findProd;
	}

	
	public void updateProd(Product prod) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(prod);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}
	
	public void saveProd(Product prod) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(prod);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}
	
	public void deleteImage(String image) {
		File deletefile = new File("D:\\WebBanGiayBongDa\\WebBanGiayBongDa\\WebContent\\resources\\images\\"
							+ image);
		try {
			
			deletefile.delete();
		} catch (Exception e) {
			System.out.println("Lỗi Xóa ảnh");
			e.printStackTrace();
		}
	}
	
	public void saveImage(MultipartFile image) {
		try {
			image.transferTo(new File(
					"D:\\WebBanGiayBongDa\\WebBanGiayBongDa\\WebContent\\resources\\images\\"
							+ image.getOriginalFilename()));
		} catch (Exception e) {
			System.out.println("Lỗi Lưu Ảnh");
			e.printStackTrace();
		}
	}
	public Product findProductCode() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		Product findProd = new Product();

		List<Product> listProd = query.list();
		if (listProd.size() > 0) {
			findProd = listProd.get(listProd.size() - 1);
		} else {
			findProd = null;
		}

		// Kiem Tra co hang co trong CSDL?

		return findProd;
	}
	public void updateListProd() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		
		this.listProduct = query.list();
	}
	
}
