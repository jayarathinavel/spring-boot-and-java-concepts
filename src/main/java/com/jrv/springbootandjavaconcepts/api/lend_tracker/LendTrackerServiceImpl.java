package com.jrv.springbootandjavaconcepts.api.lend_tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LendTrackerServiceImpl implements LendTrackerService {

    @Autowired
    LendTrackerRepository lendTrackerRepository;

    @Autowired
    BorrowerInformationRepository borrowerInformationRepository;

    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<LendTrackerEntity> findAllLists(){
        return lendTrackerRepository.findAll();
    }

    @Override
    public void addLend(@RequestBody LendTrackerEntity lendTrackerEntity){
        lendTrackerRepository.save(lendTrackerEntity);
    }

    @Override
    public List<StatusEntity> getStatus(){
       return statusRepository.findAll();
    }

    @Override
    public List<BorrowerInformation> getBorrowerInformation(){
        return borrowerInformationRepository.findAll();
    }

    @Override
    public void addBorrower(@RequestBody BorrowerInformation borrowerInformationEntity){
        borrowerInformationRepository.save(borrowerInformationEntity);
    }

    @Override
    public void deleteLend(int lendId) {
        lendTrackerRepository.deleteById(lendId);
    }

    @Override
    public void changeStatus(int lendId, int statusId) {
        lendTrackerRepository.changeStatus(lendId, statusId);
    }

    @Override
    public void deleteBorrower(int borrowerId) {
        lendTrackerRepository.changeBorrowersForExistingLends(borrowerId);
        borrowerInformationRepository.deleteById(borrowerId);
    }
}
