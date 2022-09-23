package com.jrv.springbootandjavaconcepts.address_book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "adb_contact_details")
@Getter
@Setter
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adb_contacts_seq")
    @SequenceGenerator(initialValue = 1, name = "adb_contacts_seq", sequenceName = "contacts_sequence")
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