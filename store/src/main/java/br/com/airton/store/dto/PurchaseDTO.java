package br.com.airton.store.dto;

import java.util.List;

public class PurchaseDTO {
    private List<PurchaseItemDTO> items;
    private AddressDTO address;

    public List<PurchaseItemDTO> getItems() {
        return items;
    }

    public AddressDTO getAddress() {
        return address;
    }
}
