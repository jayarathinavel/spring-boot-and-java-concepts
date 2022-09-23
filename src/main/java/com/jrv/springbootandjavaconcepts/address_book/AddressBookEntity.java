package com.jrv.springbootandjavaconcepts.address_book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "adb_address_book")
@Getter
@Setter
public class AddressBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "adb_seq")
    @SequenceGenerator(name = "adb_seq", sequenceName = "address_book_sequence")
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
    private List<ContactDetailsEntity> contactDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_book_id", referencedColumnName = "address_book_id")
    private List<AddressDetailsEntity> addressDetails;

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

}

@Entity
@Table(name = "adb_address_details")
@Getter
@Setter
class AddressDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "adb_address_seq")
    @SequenceGenerator(name = "adb_address_seq", sequenceName = "address_sequence")
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

}

@Entity
@Table(name = "adb_contact_details")
@Getter
@Setter
class ContactDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "adb_contacts_seq")
    @SequenceGenerator(name = "adb_contacts_seq", sequenceName = "contacts_sequence")
    @Column(name = "contact_details_id", nullable = false)
    private Integer contactDetailsId;

    @Column(name = "address_book_id")
    private Integer addressBookId;

    @Column(name = "value")
    private String value;

    @Column(name = "type")
    private String type;

    @Column(name = "is_main_contact")
    private boolean isMainContact = false;

    @Column(name = "label")
    private String label;

}
