package br.com.airton.store.dto;

public class AddressDTO {
    private String avenue;
    private String number;
    private String state;

    public String getAvenue() {
        return avenue;
    }

    public String getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return String.format("Avenue: %s, Number: %s, State: %s.", getAvenue(), getNumber(), getState());
    }
}
