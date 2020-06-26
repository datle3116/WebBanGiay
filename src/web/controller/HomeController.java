package web.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import web.entity.Product;

@Transactional
@Controller
public class HomeController {
	@Autowired
	SessionFactory factory;

	@RequestMapping()
	public String home(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> listProduct = query.list();
		model.addAttribute("list", listProduct);
		
		return "client/home";
	}
	@RequestMapping("/contact")
	public String contact() {
		return "client/contact";
	}
}
