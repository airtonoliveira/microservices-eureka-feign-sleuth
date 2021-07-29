package br.com.airton.supplier.service;

import br.com.airton.supplier.model.SupplierInfo;
import br.com.airton.supplier.repository.InfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    private static final Logger LOG = LoggerFactory.getLogger(InfoService.class);

    @Autowired
    private InfoRepository infoRepository;

    public SupplierInfo getStateInfo(String state) {
        LOG.info("Searching Supplier's Info from state: {}", state);
        return infoRepository.getInfoByState(state);
    }
}
