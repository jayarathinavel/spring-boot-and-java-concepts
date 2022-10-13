package com.jrv.springbootandjavaconcepts.api.address_book;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class AddressBookDTO {
    private Integer addressBookId;
    private String firstName;
    private String lastName;
    private String nickName;
    private List<ContactDetailsDTO> contactDetails;
    private List<AddressDetailsDTO> addressDetails;
    private String organization;
    private Date dateOfBirth;
    private String relationship;
    private String webLink;
    private String label;
    private String notes;
}

@Getter
@Setter
class ContactDetailsDTO {
    private Integer addressBookId;
    private String value;
    private String type;
    private boolean isMainContact = false;
    private String label;
}

@Getter
@Setter
class AddressDetailsDTO {
    private Integer addressBookId;
    private String lineOne;
    private String lineTwo;
    private String city;
    private String zipcode;
    private String label;
}