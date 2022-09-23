package com.jrv.springbootandjavaconcepts.address_book;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/address-book/find-by-id")
    public AddressBookDTO findByAddressBookId(@RequestParam Integer id){
        Optional<AddressBookEntity> addressBookEntity = addressBookService.findByAddressBookId(id);
        return modelMapper.map(addressBookEntity, AddressBookDTO.class);
    }

    @PostMapping("/address-book/save")
    public AddressBookDTO addToAddressBook(@RequestBody AddressBookDTO addressBookDTO){
        AddressBookEntity addressBookEntityRequest = modelMapper.map(addressBookDTO, AddressBookEntity.class);
        Optional<AddressBookEntity> addressBookEntityResponse = addressBookService.addToAddressBook(addressBookEntityRequest);
        return modelMapper.map(addressBookEntityResponse, AddressBookDTO.class);
    }

    @PostMapping("/address-book/delete/{id}")
    public ResponseEntity<String> deleteByAddressBookId(@PathVariable("id") Integer addressBookId){
        return addressBookService.deleteByAddressBookId(addressBookId);
    }
}
