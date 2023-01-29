package com.jrv.springbootandjavaconcepts.api.lend_tracker;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface LendTrackerService {

    List<LendTrackerEntity> findAllLists();

    void addLend(@RequestBody LendTrackerEntity lendTrackerEntity);

    List<StatusEntity> getStatus();

    List<BorrowerInformation> getBorrowerInformation();

    void addBorrower(BorrowerInformation borrowerInformationEntity);

    void deleteLend(int lendId);

    void changeStatus(int lendId, int statusId);

    void deleteBorrower(int borrowerId);
}
