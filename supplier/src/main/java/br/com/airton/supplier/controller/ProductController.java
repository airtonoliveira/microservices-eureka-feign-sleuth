package br.com.airton.supplier.controller;

import java.util.List;

import br.com.airton.supplier.model.Product;
import br.com.airton.supplier.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/{state}")
	public List<Product> getProductsByState(@PathVariable("state") String state) {
		return productService.getProductsByState(state);
	}
}
