package br.com.airton.supplier.service;

import br.com.airton.supplier.model.StateInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends CrudRepository<StateInfo, Long> {

    StateInfo findByState(String state);

}
