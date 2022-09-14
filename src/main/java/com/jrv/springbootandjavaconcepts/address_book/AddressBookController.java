package com.jrv.springbootandjavaconcepts.address_book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    @GetMapping("/address-book/find-by-id")
    public Optional<AddressBookEntity> findByAddressBookId(@RequestParam Integer id){
        return addressBookService.findByAddressBookId(id);
    }

    @PostMapping("/address-book/save")
    public Optional<AddressBookEntity> addToAddressBook(@RequestBody AddressBookEntity addressBookEntity){
        return addressBookService.addToAddressBook(addressBookEntity);
    }

    @PostMapping("/address-book/delete/{id}")
    public ResponseEntity<String> deleteByAddressBookId(@PathVariable("id") Integer addressBookId){
        return addressBookService.deleteByAddressBookId(addressBookId);
    }
}
