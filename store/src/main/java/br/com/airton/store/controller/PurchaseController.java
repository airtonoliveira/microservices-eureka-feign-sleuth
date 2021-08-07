package br.com.airton.store.controller;

import br.com.airton.store.dto.PurchaseDTO;
import br.com.airton.store.model.Purchase;
import br.com.airton.store.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public Purchase purchase(@RequestBody PurchaseDTO purchase){
        LOG.info("POST Purchase process...");
        return purchaseService.makePurchase(purchase);

    }

    @GetMapping
    public Purchase getById(@PathVariable("id") Long id){
        LOG.info("GET Purchase process...");
        return purchaseService.getById(id);

    }

}
