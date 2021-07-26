package br.com.airton.supplier.service;

import br.com.airton.supplier.model.StateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public StateInfo getStateInfo(String state) {
        return infoRepository.findByState(state);
    }
}
