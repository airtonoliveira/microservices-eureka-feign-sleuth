package br.com.airton.store.service;

import br.com.airton.store.client.SupplierClient;
import br.com.airton.store.dto.OrderInfoDTO;
import br.com.airton.store.dto.SupplierInfoDTO;
import br.com.airton.store.dto.PurchaseDTO;
import br.com.airton.store.model.Purchase;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PurchaseService {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseService.class);

    @Autowired
    private SupplierClient supplierClient;

    public Purchase makePurchase(PurchaseDTO purchase) {
        SupplierInfoDTO info = supplierClient.getInfoByState(purchase.getAddress().getState());

        OrderInfoDTO order = supplierClient.makeOrder(purchase.getItems());

        LOG.info("makePurchase - return value: "+info.getAddress());

        Purchase purchaseDB = new Purchase();
        purchaseDB.setOrderId(order.getId());
        purchaseDB.setPreparationTime(order.getPreparationTime());
        purchaseDB.setDestinationAddress(purchase.getAddress().toString());

        return purchaseDB;

    }

    /* air01 - IMPLEMENTAÇÃO USANDO REST TEMPLATE COM RIBBON E LOADBALANCER

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient eurekaClient;

    public void makePurchase(PurchaseDTO purchase) {

        ResponseEntity<SupplierInfoDTO> exchange = restTemplate.exchange(
                "http://supplier/info/"+purchase.getAddress().getState(),
                HttpMethod.GET,
                null,
                SupplierInfoDTO.class);

        LOG.info("Supplier services available:");
        eurekaClient.getInstances("supplier").stream()
                .forEach(
                supplier -> {
                    LOG.info(String.valueOf(supplier.getUri()));
                }
        );

        LOG.info("makePurchase - return value: "+exchange.getBody().getAddress());
    }*/
}
