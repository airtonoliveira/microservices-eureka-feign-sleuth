package br.com.airton.supplier.service;

import java.util.List;

import br.com.airton.supplier.model.Product;
import br.com.airton.supplier.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository produtoRepository;
	
	public List<Product> getProductsByState(String state) {
		return produtoRepository.findByState(state);
	}

	
}
