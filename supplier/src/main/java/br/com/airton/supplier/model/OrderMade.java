package br.com.airton.supplier.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class OrderMade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer preparationTime;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "itemId")
	private List<OrderItem> items;
	
	public OrderMade(List<OrderItem> itens) {
		this.items = itens;
		this.status = OrderStatus.RECEIVED;
	}
	
	public OrderMade() {
	}

	public Long getId() {
		return id;
	}

	public Integer getPreparationTime() {
		return preparationTime;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setPreparationTime(Integer preparationTime) {
		this.preparationTime = preparationTime;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
