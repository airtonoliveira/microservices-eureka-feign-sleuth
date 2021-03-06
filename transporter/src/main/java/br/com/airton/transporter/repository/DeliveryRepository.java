package br.com.airton.transporter.repository;

import br.com.airton.transporter.model.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long>{

}
