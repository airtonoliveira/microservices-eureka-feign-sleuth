package br.com.airton.supplier.controller;

import br.com.airton.supplier.model.StateInfo;
import br.com.airton.supplier.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @RequestMapping("/{state}")
    private StateInfo getStateInfo(String state){
        return infoService.getStateInfo(state);
    }

}
