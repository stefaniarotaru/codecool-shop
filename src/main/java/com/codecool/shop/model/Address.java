package com.codecool.shop.model;

public class Address {
    private String country;
    private String city;
    private String zipcode;
    private String addressLine;

    public Address(String country, String city, String zipcode, String addressLine) {
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.addressLine = addressLine;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    @Override
    public String toString() {
        return String.format(
                "country: %1$s," +
                "city: %2$s, " +
                "zipcode: %3$s, " +
                "addressLine: %4$s",
                this.country,
                this.city,
                this.zipcode,
                this.addressLine);
    }
}
