package br.com.airton.supplier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer amount;
	
	@ManyToOne @JoinColumn(name = "productId")
	private Product product;

	//@ManyToOne @JoinColumn(name = "orderId")
	//private OrderMade order;

	public Long getId() {
		return id;
	}

	public Integer getAmount() {
		return amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
