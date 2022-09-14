package com.jrv.springbootandjavaconcepts.address_book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressBookService {
    @Autowired
    AddressBookRepository addressBookRepository;

    public Optional<AddressBookEntity> findByAddressBookId(Integer addressBookId){
        return addressBookRepository.findById(addressBookId);
    }

    @Transactional
    public Optional<AddressBookEntity> addToAddressBook(AddressBookEntity addressBookEntity){
        addressBookRepository.save(addressBookEntity);
        return addressBookRepository.findById(addressBookEntity.getAddressBookId());
    }

    public ResponseEntity<String> deleteByAddressBookId(Integer addressBookId){
        addressBookRepository.deleteById(addressBookId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
