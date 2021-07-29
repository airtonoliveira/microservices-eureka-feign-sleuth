package br.com.airton.supplier.controller;

import java.util.List;

import br.com.airton.supplier.dto.OrderItemDTO;
import br.com.airton.supplier.model.OrderMade;
import br.com.airton.supplier.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.POST)
	public OrderMade makeOrder(@RequestBody List<OrderItemDTO> products) {
		return orderService.makeOrder(products);
	}
	
	@RequestMapping("/{id}")
	public OrderMade getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}
}
