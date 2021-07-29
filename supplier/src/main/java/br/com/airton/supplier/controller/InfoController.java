package br.com.airton.supplier.controller;

import br.com.airton.supplier.model.SupplierInfo;
import br.com.airton.supplier.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @RequestMapping("/{state}")
    private @ResponseBody
    SupplierInfo getStateInfo(@PathVariable String state){
        return infoService.getStateInfo(state);
    }

}
