package web.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Products")
public class Product {
	@Id
	@Column(name="Code")
	private String code;
	
	@Column(name="Create_Date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createdate;
	
	@Column(name="Image")
	private String image;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Price")
	private float price;
	
	@Column(name="Product_Type")
	private String type;
	
	@OneToMany(mappedBy="product", fetch=FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreateDate(Date createdate) {
		this.createdate = createdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
