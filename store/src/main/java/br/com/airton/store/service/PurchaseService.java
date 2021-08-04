package br.com.airton.store.service;

import br.com.airton.store.client.SupplierClient;
import br.com.airton.store.dto.OrderInfoDTO;
import br.com.airton.store.dto.SupplierInfoDTO;
import br.com.airton.store.dto.PurchaseDTO;
import br.com.airton.store.model.Purchase;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseService.class);

    @Autowired
    private SupplierClient supplierClient;

    @HystrixCommand(fallbackMethod = "makePurchaseFallback")
    public Purchase makePurchase(PurchaseDTO purchase) {

        SupplierInfoDTO info = supplierClient.getInfoByState(purchase.getAddress().getState());

        OrderInfoDTO order = supplierClient.makeOrder(purchase.getItems());
        LOG.info("Order {} sent to Supplier.", order.getId());

        Purchase purchaseDB = new Purchase();
        purchaseDB.setOrderId(order.getId());
        purchaseDB.setPreparationTime(order.getPreparationTime());
        purchaseDB.setDestinationAddress(purchase.getAddress().toString());

        try {
            //Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Fallback...");
        }

        return purchaseDB;

    }

    public Purchase makePurchaseFallback(PurchaseDTO purchaseDTO){
        Purchase purchaseFallback = new Purchase();
        purchaseFallback.setDestinationAddress(purchaseDTO.getAddress().toString());
        return purchaseFallback;
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
