package web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Order_Details")
public class OrderDetail {
	@Id
	@Column(name="ID")
	private String id;
	
	@ManyToOne()
    @JoinColumn(name = "ORDER_ID")
    private Order order;
 
	@ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
	
	@Column(name="Quantity")
    private int quantity;
    
	@Column(name="Price")
	private double price;
    
	@Column(name="Amount")
	private double amount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quanity) {
		this.quantity = quanity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	
	
}
