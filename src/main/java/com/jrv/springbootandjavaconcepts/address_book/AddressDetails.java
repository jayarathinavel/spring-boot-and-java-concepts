package com.jrv.springbootandjavaconcepts.address_book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "adb_address_details")
@Getter
@Setter
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

}