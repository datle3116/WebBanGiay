package web.entity;

import javax.validation.constraints.DecimalMin;


public class CartProduct {
	private String code;//PRODUCT_ID
	private String name;//PRODUCT_NAME
	private float price;//ORDER_DETAIL_PRICE

	@DecimalMin(value="0",message = "Số lượng phải lớn hơn 0")
	private int quantity;//ORDER_DETAIL_QUANTITY
	private String type;//PRODUCT_TYPE
	private String image;//PRODUCT_IMAGE
	private float amount;//ORDER_DETAIL_AMOUNT
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
