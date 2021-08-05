package br.com.alura.microservice.transportador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.transportador.dto.DeliveryDTO;
import br.com.alura.microservice.transportador.dto.VoucherDTO;
import br.com.alura.microservice.transportador.model.Delivery;
import br.com.alura.microservice.transportador.repository.DeliveryRepository;

@Service
public class DeliveryService {
	
	@Autowired
	private DeliveryRepository repository;

	public VoucherDTO deliveryReservation(DeliveryDTO pedidoDTO) {
		
		Delivery delivery = new Delivery();
		delivery.setPickupDate(pedidoDTO.getPickupDate());
		delivery.setDeliveryForecast(pedidoDTO.getPickupDate().plusDays(1l));
		delivery.setDestinationAddress(pedidoDTO.getDestinationAddress());
		delivery.setSourceAddress(pedidoDTO.getSourceAddress());
		delivery.setOrderID(pedidoDTO.getOrderId());
		
		repository.save(delivery);
		
		VoucherDTO voucher = new VoucherDTO();
		voucher.setNumber(delivery.getId());
		voucher.setDeliveryForecast(delivery.getDeliveryForecast());
		return voucher;
	}

}
