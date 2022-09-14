package com.jrv.springbootandjavaconcepts.address_book;

import javax.persistence.*;

@Entity
@Table(name = "adb_address_details")
public class AddressDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adb_address_seq")
    @SequenceGenerator(initialValue = 1, name = "adb_address_seq", sequenceName = "address_sequence")
    @Column(name = "address_details_id", nullable = false)
    private Integer addressDetailsId;

    @Column(name = "address_book_id")
    private Integer addressBookId;

    @Column(name = "line_1")
    private String lineOne;

    @Column(name = "line_2")
    private String lineTwo;

    @Column(name = "city")
    private String city;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "label")
    private String label;

    public Integer getAddressDetailsId() {
        return addressDetailsId;
    }

    public void setAddressDetailsId(Integer addressDetailsId) {
        this.addressDetailsId = addressDetailsId;
    }

    public Integer getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(Integer addressBookId) {
        this.addressBookId = addressBookId;
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}