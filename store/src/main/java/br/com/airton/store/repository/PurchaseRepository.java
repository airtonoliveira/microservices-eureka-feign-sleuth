package br.com.airton.store.repository;

import br.com.airton.store.model.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {



}
