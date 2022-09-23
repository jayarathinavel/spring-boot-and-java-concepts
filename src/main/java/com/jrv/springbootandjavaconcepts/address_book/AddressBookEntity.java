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

}
