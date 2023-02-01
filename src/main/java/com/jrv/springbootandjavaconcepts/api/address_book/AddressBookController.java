package com.jrv.springbootandjavaconcepts.api.address_book;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/address-book")
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/find-by-id")
    public AddressBookDTO findByAddressBookId(@RequestParam Integer id){
        Optional<AddressBookEntity> addressBookEntity = addressBookService.findByAddressBookId(id);
        return modelMapper.map(addressBookEntity, AddressBookDTO.class);
    }

    @GetMapping("/find-all")
    public List<AddressBookDTO> findAllAddressBookLists(){
        List<AddressBookEntity> addressBookEntity = addressBookService.findAll();
        return addressBookEntity.stream().map(list -> modelMapper.map(list, AddressBookDTO.class)).collect(Collectors.toList()) ;
    }

    @PostMapping("/save")
    public AddressBookDTO addToAddressBook(@RequestBody AddressBookDTO addressBookDTO){
        AddressBookEntity addressBookEntityRequest = modelMapper.map(addressBookDTO, AddressBookEntity.class);
        Optional<AddressBookEntity> addressBookEntityResponse = addressBookService.addToAddressBook(addressBookEntityRequest);
        return modelMapper.map(addressBookEntityResponse, AddressBookDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteByAddressBookId(@PathVariable("id") Integer addressBookId){
        return addressBookService.deleteByAddressBookId(addressBookId);
    }
}
