package com.jrv.springbootandjavaconcepts.address_book;

import javax.persistence.*;

@Entity
@Table(name = "adb_contact_details")
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_details_id", nullable = false)
    private Integer contactDetailsId;

    @ManyToOne
    @JoinColumn(name = "address_book_id")
    private AddressBookEntity addressBook;

    @Column(name = "value")
    private String value;

    @Column(name = "type")
    private String type;

    @Column(name = "is_main_contact")
    private boolean isMainContact;

    @Column(name = "label")
    private String label;

    public Integer getContactDetailsId() {
        return contactDetailsId;
    }

    public void setContactDetailsId(Integer contactDetailsId) {
        this.contactDetailsId = contactDetailsId;
    }

    public AddressBookEntity getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBookEntity addressBook) {
        this.addressBook = addressBook;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMainContact() {
        return isMainContact;
    }

    public void setMainContact(boolean mainContact) {
        isMainContact = mainContact;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}