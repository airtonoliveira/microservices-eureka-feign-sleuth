package br.com.airton.store.dto;

import java.util.List;

public class PurchaseDTO {
    private Long id;
    private List<PurchaseItemDTO> items;
    private AddressDTO address;

    public List<PurchaseItemDTO> getItems() {
        return items;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(List<PurchaseItemDTO> items) {
        this.items = items;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
