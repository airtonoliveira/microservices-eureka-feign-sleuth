package br.com.airton.store.service;

import br.com.airton.store.dto.SupplierInfoDTO;
import br.com.airton.store.dto.PurchaseDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PurchaseService {

    public void makePurchase(PurchaseDTO purchase) {

        RestTemplate client = new RestTemplate();
        client.exchange(
                "http://localhost:8182/supplier/info/"+purchase.getAddress().getState(),
                HttpMethod.GET,
                null,
                SupplierInfoDTO.class);

    }
}
