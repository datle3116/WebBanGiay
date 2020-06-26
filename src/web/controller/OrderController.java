package web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import web.entity.Account;
import web.entity.CartProduct;
import web.entity.Order;
import web.entity.OrderDetail;
import web.entity.Product;

@Transactional
@Controller
public class OrderController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	JavaMailSender mailer;
	
	List<Account> listAccount = new ArrayList();
	List<Order> listOrderSql = new ArrayList<>();// Chứa thông tin khách hàng từng đơn hàng
	List<OrderDetail> listOrdDeSql = new ArrayList<>();//Chứa chi tiết đơn đặt hàng
	List<Product> listProdSql = new ArrayList<>();//Chứa thông tin toàn sản phẩm trong shop
	Account acc = new Account();
	
	
	@RequestMapping(value="/admin/orderlist/{username}")
	public String orderList(ModelMap model, @PathVariable("username") String username) {
		setListOrderSql();
		setListOrdDeSql();
		setListProdSql();
		setListAcc();
		
		
		for(Account a : this.listAccount) {
			if(username.equals(a.getUsername())) {
				this.acc = a;
				break;
			}
		}
		
		model.addAttribute("account", this.acc);
		model.addAttribute("listProdSql", listProdSql);
		model.addAttribute("listOrderSql", listOrderSql);
		model.addAttribute("listOrdDeSql",listOrdDeSql);
		return "admin/orderlist";
	}
	
	@RequestMapping(value= "/admin/takeOrder/{id}/{username}")
	public String takeOrder(@PathVariable("id") String id) {
		Order ord = findOrderById(id);
		for(OrderDetail ordDe : this.listOrdDeSql) {
			if(id.equals(ordDe.getOrder().getId())) {
				deleteOrderDetail(ordDe);
			}
		}
		deleteOrder(ord);
		return "redirect:/admin/orderlist/"+acc.getUsername()+".htm";
	}
	@RequestMapping(value="/admin/confirm/{id}")
	public String confirm(@PathVariable("id") String id) {
		Order ord = findOrderById(id);
		ord.setStatus("Đã gửi hàng");
		updateOrder(ord);
		email(ord);
		
		return "redirect:/admin/orderlist/"+acc.getUsername()+".htm";
	}
	
	//
	
	
	
	//
	public void deleteOrder(Order order) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(order);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();

		}
	}
	public Product findProductById(String code) {
		Product findProd = new Product();
		for(Product prod : this.listProdSql) {
			if(code.equals(prod.getCode())) {
				findProd = prod;
				break;
			}
		}
		return findProd;
	}
	
	
	public void deleteOrderDetail(OrderDetail ordDe) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(ordDe);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();

		}
	}
	
	public OrderDetail findOrderDetailById(String code) {
		OrderDetail find = new OrderDetail();
		for(OrderDetail o : this.listOrdDeSql) {
			if(code.equals(o.getOrder().getId())) {
				find = o;
				break;
			}
		}
		return find;
	}
	public Order findOrderById(String code) {
		Order find = new Order();
		for(Order o : this.listOrderSql) {
			if(code.equals(o.getId())) {
				find = o;
				break;
			}
		}
		return find;
	}
	public void setListAcc() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Account";
		Query query = session.createQuery(hql);
		this.listAccount = query.list();

	}
	
	public void setListProdSql() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);

		this.listProdSql = query.list();
	}
	
	public void setListOrdDeSql() {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderDetail";
		Query query = session.createQuery(hql);

		this.listOrdDeSql = query.list();
	}
	
	public void setListOrderSql() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order";
		Query query = session.createQuery(hql);

		this.listOrderSql = query.list();
	}
	public void updateOrder(Order ord) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(ord);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}
	public void email(Order ord) {
		try {
			String noiDung = "Bạn đã đặt hàng thành công! Đơn hàng của bạn đã được gửi với mã đặt hàng là:"+ord.getId()+"\nDanh sách đặt hàng:";
			for(OrderDetail ordDe : this.listOrdDeSql) {
				noiDung += "\n- " + ordDe.getProduct().getName() + ", Số lượng: " +ordDe.getQuantity()+", Giá tiền: "+ordDe.getAmount();
			}
			noiDung += "\nTổng tiền: "+ ord.getAmount() +"\nXin cảm ơn đã mua hàng tại Giày Bóng Đá Phủi!";
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom("dat3116981@gmail.com", "dat3116981@gmail.com");
			helper.setTo(ord.getCustomerEmail());
			helper.setReplyTo("dat3116981@gmail.com", "dat3116981@gmail.com");
			helper.setSubject("Thông tin đơn đặt hàng | Giày Bóng Đá Phủi");
			helper.setText(noiDung);
			
			mailer.send(mail);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
