package my.com.etoqa.maybank.pojo;

import java.sql.Timestamp;

public class ProductDto {

	private Long productId;

	private String productName;

	private String productQuantity;
	
	private String productTitle;

	private Long productPrice;
	
	private Boolean status; 
	
	private Timestamp createdDate;

	private Timestamp modifiedDate;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", productName=" + productName + ", productQuantity="
				+ productQuantity + ", productTitle=" + productTitle + ", productPrice=" + productPrice + ", status="
				+ status + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}

	
}
