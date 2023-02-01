package com.jrv.springbootandjavaconcepts.api.lend_tracker;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/lend-tracker")
public class LendTrackerController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private LendTrackerService lendTrackerService;

    @GetMapping("/find-all-lists")
    public List<LendTrackerDTO> findAllLists(){
        List<LendTrackerEntity> lendTrackerEntities = lendTrackerService.findAllLists();
        lendTrackerEntities.sort(Comparator.comparingInt(LendTrackerEntity::getLendListsId));
        return lendTrackerEntities.stream().map(list -> modelMapper.map(list, LendTrackerDTO.class)).collect(Collectors.toList()) ;
    }

    @Transactional
    @PostMapping("/add-lend")
    public void addLend(@RequestBody LendTrackerDTO lendListsDTO){
        lendListsDTO.setStatusId(lendListsDTO.getStatusId() == 0 && lendListsDTO.getCreatedDate() == null ? 1 :
                lendListsDTO.getStatusId());
        lendListsDTO.setCreatedDate(lendListsDTO.getCreatedDate() == null ? Date.valueOf(LocalDate.now()) :
                lendListsDTO.getCreatedDate());
        lendListsDTO.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        LendTrackerEntity lendTrackerEntity = modelMapper.map(lendListsDTO, LendTrackerEntity.class);
        lendTrackerService.addLend(lendTrackerEntity);
    }

    @Transactional
    @PutMapping("/update-lend")
    public void updateLend(@RequestBody LendTrackerDTO lendListsDTO){
        lendListsDTO.setReturnDate(lendListsDTO.getStatusId() == 2 ? Date.valueOf(LocalDate.now()) : null);
        lendListsDTO.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        addLend(lendListsDTO);
    }

    @Transactional
    @DeleteMapping("/delete-lend")
    public void deleteLend(@RequestParam int lendId){
        lendTrackerService.deleteLend(lendId);
    }

    @GetMapping("/get-status")
    public List<StatusEntity> getStatus(){
        return lendTrackerService.getStatus();
    }

    @GetMapping("/get-borrower")
    public List<BorrowerInformation> getBorrower(){
        return lendTrackerService.getBorrowerInformation();
    }

    @Transactional
    @PostMapping("/add-borrower")
    public void addBorrower(@RequestBody BorrowerInformationDTO borrowerInformationDto){
        BorrowerInformation borrowerInformation = modelMapper.map(borrowerInformationDto, BorrowerInformation.class);
        lendTrackerService.addBorrower(borrowerInformation);
    }

    @Transactional
    @DeleteMapping("/delete-borrower")
    public void deleteBorrower(@RequestParam int borrowerId){
        lendTrackerService.deleteBorrower(borrowerId);
    }
}
