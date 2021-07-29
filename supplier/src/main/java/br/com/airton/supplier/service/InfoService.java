package br.com.airton.supplier.service;

import br.com.airton.supplier.model.SupplierInfo;
import br.com.airton.supplier.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public SupplierInfo getStateInfo(String state) {
        return infoRepository.getInfoByState(state);
    }
}
