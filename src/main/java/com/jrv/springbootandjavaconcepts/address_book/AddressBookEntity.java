package com.jrv.springbootandjavaconcepts.address_book;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "adb_address_book")
public class AddressBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adb_seq")
    @SequenceGenerator(initialValue = 1, name = "adb_seq", sequenceName = "address_book_sequence")
    @Column(name = "address_book_id")
    private int addressBookId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "nickname")
    private String nickName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_book_id", referencedColumnName = "address_book_id")
    private List<ContactDetails> contactDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_book_id", referencedColumnName = "address_book_id")
    private List<AddressDetails> addressDetails;

    @Column(name = "organization")
    private String organization;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "web_link")
    private String webLink;

    @Column(name = "label")
    private String label;

    @Column(name = "notes")
    private String notes;

    public int getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(int addressBookId) {
        this.addressBookId = addressBookId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<ContactDetails> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(List<ContactDetails> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public List<AddressDetails> getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(List<AddressDetails> addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
