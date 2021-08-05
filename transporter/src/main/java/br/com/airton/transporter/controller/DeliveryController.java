package br.com.airton.transporter.controller;

import br.com.airton.transporter.dto.DeliveryDTO;
import br.com.airton.transporter.dto.VoucherDTO;
import br.com.airton.transporter.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	private DeliveryService entregaService;

	@RequestMapping(method = RequestMethod.POST)
	public VoucherDTO deliveryReservation(@RequestBody DeliveryDTO pedidoDTO) {
		return entregaService.deliveryReservation(pedidoDTO);
	}
}
