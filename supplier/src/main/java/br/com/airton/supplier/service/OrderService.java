package br.com.airton.supplier.service;

import br.com.airton.supplier.dto.OrderItemDTO;
import br.com.airton.supplier.model.OrderMade;
import br.com.airton.supplier.model.OrderItem;
import br.com.airton.supplier.model.Product;
import br.com.airton.supplier.repository.OrderRepository;
import br.com.airton.supplier.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

	private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public OrderMade makeOrder(List<OrderItemDTO> itens) {
		
		if(itens == null) {
			return null;
		}
		
		List<OrderItem> pedidoItens = toOrderItem(itens);
		OrderMade order = new OrderMade(pedidoItens);
		order.setPreparationTime(itens.size());
		order = orderRepository.save(order);
		LOG.info("Order {} saved.", order.getId());
		return order;

	}
	
	public OrderMade getOrderById(Long id) {
		return this.orderRepository.findById(id).orElse(new OrderMade());
	}

	private List<OrderItem> toOrderItem(List<OrderItemDTO> items) {

		LOG.info("Converting OrderItemDTO to OrderItem...");
		List<Long> productsIds = items
				.stream()
				.map(item -> item.getId())
				.collect(Collectors.toList());
		
		List<Product> orderProducts = productRepository.findByIdIn(productsIds);
		
		List<OrderItem> orderItems = items
			.stream()
			.map(item -> {
				Product product = orderProducts
						.stream()
						.filter(p -> p.getId() == item.getId())
						.findFirst().get();

				OrderItem orderItem = new OrderItem();
				orderItem.setProduct(product);
				orderItem.setAmount(item.getAmount());
				return orderItem;
			})
			.collect(Collectors.toList());
		return orderItems;
	}
}
