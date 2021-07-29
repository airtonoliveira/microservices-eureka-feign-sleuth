package br.com.airton.store.client;


import br.com.airton.store.dto.OrderInfoDTO;
import br.com.airton.store.dto.PurchaseItemDTO;
import br.com.airton.store.dto.SupplierInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * SupplierClinet utiliza o Feign que já está integrado com o RIBBON e o CLIENT-SIDE LOAD-BALANCING assim se conectando com EUREKA para obter os endereços dos microserviços.
 * parametro "supplier" indica o nome do microserviços
 */
@FeignClient("supplier")
public interface SupplierClient {

    @RequestMapping("/info/{state}")
    SupplierInfoDTO getInfoByState(@PathVariable String state);

    @PostMapping("/order")
    OrderInfoDTO makeOrder(List<PurchaseItemDTO> items);
}
