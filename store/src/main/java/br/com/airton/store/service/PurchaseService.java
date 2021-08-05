package br.com.airton.store.service;

import br.com.airton.store.client.SupplierClient;
import br.com.airton.store.client.TransporterClient;
import br.com.airton.store.dto.*;
import br.com.airton.store.model.Purchase;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import br.com.airton.store.repository.PurchaseRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PurchaseService {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseService.class);

    @Autowired
    private SupplierClient supplierClient;

    @Autowired
    private TransporterClient transporterClient;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @HystrixCommand(
            threadPoolKey = "getByIdThreadPool"
    )
    public Purchase getById(Long id) {
        return purchaseRepository.findById(id).orElse(new Purchase());
    }

    @HystrixCommand(
            fallbackMethod = "makePurchaseFallback",
            threadPoolKey = "makePurchaseThreadPool"
            )
    public Purchase makePurchase(PurchaseDTO purchase) {

        SupplierInfoDTO info = supplierClient.getInfoByState(purchase.getAddress().getState());

        OrderInfoDTO order = supplierClient.makeOrder(purchase.getItems());
        LOG.info("Order {} sent to Supplier.", order.getId());

        DeliveryInfoDTO deliveryDTO = new DeliveryInfoDTO();
        deliveryDTO.setOrderId(order.getId());
        deliveryDTO.setPickupDate(LocalDate.now().plusDays(order.getPreparationTime()));
        deliveryDTO.setDestinationAddress(info.getAddress());
        deliveryDTO.setDestinationAddress(purchase.getAddress().toString());

        VoucherDTO voucher = transporterClient.deliveryReservation(deliveryDTO);

        Purchase purchaseDB = new Purchase();
        purchaseDB.setOrderId(order.getId());
        purchaseDB.setPreparationTime(order.getPreparationTime());
        purchaseDB.setDestinationAddress(purchase.getAddress().toString());
        purchaseDB.setPickupDate(voucher.getDeliveryForecast());
        purchaseDB.setVoucher(voucher.getNumber());

        purchaseRepository.save(purchaseDB);

        try {
            //Thread.sleep(2000);
        } catch (Exception e) {

        }

        return purchaseDB;

    }

    public Purchase makePurchaseFallback(PurchaseDTO purchaseDTO){
        System.out.println("FALLBACK...");
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
