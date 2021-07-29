package br.com.airton.supplier.repository;

import br.com.airton.supplier.model.OrderMade;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderMade, Long>{

}
