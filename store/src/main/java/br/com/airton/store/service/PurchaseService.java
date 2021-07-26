package br.com.airton.store.service;

import br.com.airton.store.dto.SupplierInfoDTO;
import br.com.airton.store.dto.PurchaseDTO;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class PurchaseService {

    public void makePurchase(PurchaseDTO purchase) {

        RestTemplate client = new RestTemplate();
        client.exchange(
                "http://localhost:8082/supplier/info/"+purchase.getAddress().getState(),
                HttpMethod.GET,
                null,
                SupplierInfoDTO.class);

    }
}
