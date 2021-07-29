package br.com.airton.supplier.repository;

import br.com.airton.supplier.model.SupplierInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends CrudRepository<SupplierInfo, Long> {

    SupplierInfo getInfoByState(String state);

}
