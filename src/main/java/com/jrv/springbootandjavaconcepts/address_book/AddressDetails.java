package com.jrv.springbootandjavaconcepts.address_book;

import javax.persistence.*;

@Entity
@Table(name = "adb_address_details")
public class AddressDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_details_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "address_book_id")
    private AddressBookEntity addressBook;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AddressBookEntity getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBookEntity addressBook) {
        this.addressBook = addressBook;
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