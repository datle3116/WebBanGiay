package web.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="Order_Date")
	private Date orderDate;
	
	@Column(name="Order_Num")
	private int orderNum;
	
	@Column(name="Amount")
	private double amount;

	@NotBlank(message = "Không được để trống Họ và Tên!")
	@Column(name="Customer_Name")
	private String customerName;
	
	@NotBlank(message = "Không được để trống Địa Chỉ!")
	@Column(name="Customer_Address")
	private String customerAddress;
	
	@NotBlank(message = "Không được để trống Email!")
	@Column(name="Customer_Email")
	private String customerEmail;
	
	@NotBlank(message = "Không được để trống Số Điện Thoại!")
	@Column(name="Customer_Phone")
	private String customerPhone;
	
	@Column(name="Status")
	private String status;

	@OneToMany(mappedBy="order",fetch=FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}
