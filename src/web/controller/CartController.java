package web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.entity.CartProduct;
import web.entity.Order;
import web.entity.OrderDetail;
import web.entity.Product;
import web.util.MyGenerator;

@Transactional
@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	SessionFactory factory;

	public List<Product> listProd = new ArrayList<>();
	public List<CartProduct> listCartProd = new ArrayList<>();
	Order cartOrder = new Order();

	@RequestMapping()
	public String cart(ModelMap model) {
		this.cartOrder.setAmount(tongTienCart());
		model.addAttribute("cartOrder", cartOrder);
		model.addAttribute("listCartProd", listCartProd);
		return "client/cart";
	}

	@RequestMapping(value = "/{code}")
	public String cart(ModelMap model, @PathVariable("code") String code) {
		Product addProd = findProdCode(code);

		// Kiem tra hang trong Cart
		CartProduct prods = new CartProduct();
		for (CartProduct prod : listCartProd) {
			if (addProd.getCode().equals(prod.getCode())) {
				prod.setQuantity(prod.getQuantity() + 1);
				prod.setAmount(prod.getAmount() + prod.getPrice());
				return "redirect:/cart.htm";
			}
		}
		prods.setCode(addProd.getCode());
		prods.setName(addProd.getName());
		prods.setQuantity(1);
		prods.setAmount(addProd.getPrice());
		prods.setType(addProd.getType());
		prods.setImage(addProd.getImage());
		prods.setPrice(addProd.getPrice());
		listCartProd.add(prods);

		return "redirect:/cart.htm";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(ModelMap model, @PathVariable("code") String code) {

		CartProduct delProd = null;
		for (CartProduct product : listCartProd) {
			if (code.equals(product.getCode())) {
				delProd = product;
				break;
			}
		}
		listCartProd.remove(delProd);
		return "redirect:/cart.htm";
	}

	@RequestMapping(value = "/edit/{code}", method = RequestMethod.GET)
	public String edit(ModelMap model, @PathVariable("code") String code) {
		CartProduct ediProd = null;
		for (CartProduct product : listCartProd) {
			if (code.equals(product.getCode())) {
				ediProd = product;
				break;
			}
		}
		model.addAttribute("ediProd", ediProd);
		return "client/edit";
	}

	@RequestMapping(value = "/edit/{code}", method = RequestMethod.POST)
	public String edit(ModelMap model, @PathVariable("code") String code,
			@ModelAttribute("ediProd") CartProduct ediProd, BindingResult errors) {
		Product findProd = findProdCode(code);
		CartProduct setProd = new CartProduct();
		for (CartProduct product : listCartProd) {
			if (code.equals(product.getCode())) {
				setProd = product;
				break;
			}
		}
		if (ediProd.getQuantity() <= 0) {
			errors.rejectValue("quantity", "ediProd","Không được để số lượng dưới 0");
		}
		if(errors.hasErrors()) {
			ediProd.setImage(setProd.getImage());
			model.addAttribute("ediProd", ediProd);
			return "client/edit";
		}
		setProd.setQuantity(ediProd.getQuantity());
		setProd.setAmount(findProd.getPrice() * ediProd.getQuantity());
		return "redirect:/cart.htm";

	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(ModelMap model) {
		model.addAttribute("cartOrder", new Order());
		return "client/order";
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(ModelMap model, @ModelAttribute("cartOrder") Order cartOrder, BindingResult errors) {

		if (cartOrder.getCustomerName().trim().length() == 0) {
			errors.rejectValue("customerName", "cartOrder", "Vui lòng điền Họ và tên");
		}
		if (cartOrder.getCustomerAddress().trim().length() == 0) {
			errors.rejectValue("customerAddress", "cartOrder", "Vui lòng điền địa chỉ nhận hàng");
		}
		if (cartOrder.getCustomerEmail().trim().length() == 0) {
			errors.rejectValue("customerEmail", "cartOrder", "Vui lòng điền Email");
		}
		if (cartOrder.getCustomerPhone().trim().length() == 0) {
			errors.rejectValue("customerPhone", "cartOrder", "Vui lòng điền Số điện thoại");
		}
		if (errors.hasErrors()) {
			model.addAttribute("cartOrder", cartOrder);
			return "client/editOrd";
		}
		this.cartOrder = cartOrder;
		return "redirect:/cart.htm";
	}

	@RequestMapping(value = "/editOrd", method = RequestMethod.GET)
	public String editOrd(ModelMap model) {
		model.addAttribute("cartOrder", this.cartOrder);
		return "client/editOrd";
	}

	@RequestMapping(value = "/editOrd", method = RequestMethod.POST)
	public String editOrd(ModelMap model, @ModelAttribute("cartOrder") Order cartOrder, BindingResult errors) {
		if (cartOrder.getCustomerName().trim().length() == 0) {
			errors.rejectValue("customerName", "cartOrder", "Vui lòng điền Họ và tên");
		}
		if (cartOrder.getCustomerAddress().trim().length() == 0) {
			errors.rejectValue("customerAddress", "cartOrder", "Vui lòng điền địa chỉ nhận hàng");
		}
		if (cartOrder.getCustomerEmail().trim().length() == 0) {
			errors.rejectValue("customerEmail", "cartOrder", "Vui lòng điền Email");
		}
		if (cartOrder.getCustomerPhone().trim().length() == 0) {
			errors.rejectValue("customerPhone", "cartOrder", "Vui lòng điền Số điện thoại");
		}
		if (errors.hasErrors()) {
			model.addAttribute("cartOrder", cartOrder);
			return "client/editOrd";
		}
		this.cartOrder = cartOrder;
		return "redirect:/cart.htm";
	}

	@RequestMapping(value = "/deleteOrd")
	public String deleteOrd() {
		this.cartOrder = new Order();
		return "redirect:/cart.htm";
	}

	@RequestMapping(value = "/success")
	public String success(ModelMap model) {
		Order order = new Order();
		OrderDetail orderDetail = new OrderDetail();
		if (findOrdCode() == null) {
			order.setId("ORD001");
		} else {
			order.setId(MyGenerator.generateOrderId(findOrdCode().getId()));
		}
		order.setOrderNum(this.listCartProd.size());
		order.setAmount(cartOrder.getAmount());
		order.setCustomerName(cartOrder.getCustomerName());
		order.setCustomerEmail(cartOrder.getCustomerEmail());
		order.setCustomerPhone(cartOrder.getCustomerPhone());
		order.setCustomerAddress(cartOrder.getCustomerAddress());
		order.setOrderDate(new Date());
		order.setStatus("Chưa gửi hàng");
		saveSqlOrd(order);

		for (CartProduct cartProd : listCartProd) {
			if (findOrdDeCode() == null) {
				orderDetail.setId("ORDDE001");
			} else {
				orderDetail.setId(MyGenerator.generateOrderDetailId(findOrdDeCode().getId()));
			}
			orderDetail.setAmount(cartProd.getAmount());
			orderDetail.setPrice(cartProd.getPrice());
			orderDetail.setQuantity(cartProd.getQuantity());
			orderDetail.setProduct(findProdCode(cartProd.getCode()));
			orderDetail.setOrder(order);

			saveSqlOrdD(orderDetail);
		}

		listProd = new ArrayList<>();
		listCartProd = new ArrayList<>();
		cartOrder = new Order();
		return "redirect:/cart.htm";
	}

	public Order findOrdCode() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order";
		Query query = session.createQuery(hql);
		Order findProd = new Order();

		List<Order> listOrder = query.list();

		if (listOrder.size() > 0) {
			findProd = listOrder.get(listOrder.size() - 1);
		} else {
			findProd = null;
		}
		// Kiem Tra co hang co trong CSDL?
		return findProd;
	}

	public OrderDetail findOrdDeCode() {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderDetail";
		Query query = session.createQuery(hql);
		OrderDetail findOrdD = new OrderDetail();

		List<OrderDetail> listOrderDetail = query.list();
		if (listOrderDetail.size() > 0) {
			findOrdD = listOrderDetail.get(listOrderDetail.size() - 1);
		} else {
			findOrdD = null;
		}

		// Kiem Tra co hang co trong CSDL?

		return findOrdD;
	}

	public void saveSqlOrdD(OrderDetail orderDetail) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(orderDetail);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();

		}
	}

	public void saveSqlOrd(Order order) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(order);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}

	public float tongTienCart() {
		float tongTien = 0;
		for (CartProduct prod : listCartProd) {
			tongTien += prod.getAmount();
		}
		return tongTien;
	}

	public Product findProdCode(String code) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		Product findProd = new Product();

		List<Product> listProduct = query.list();

		// Kiem Tra co hang co trong CSDL?
		for (Product product : listProduct) {
			if (code.equals(product.getCode())) {
				findProd = product;
				break;
			}
		}
		return findProd;
	}
}
