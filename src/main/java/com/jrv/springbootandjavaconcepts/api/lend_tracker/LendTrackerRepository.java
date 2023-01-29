package com.jrv.springbootandjavaconcepts.api.lend_tracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LendTrackerRepository extends JpaRepository<LendTrackerEntity, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE lt_lend_tracker_lists SET status_id = ?2 WHERE lend_lists_id = ?1")
    void changeStatus(int lendId, int statusId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE lt_lend_tracker_lists SET borrower_id = 0 WHERE borrower_id = ?1")
    void changeBorrowersForExistingLends(int borrowerId);
}

@Repository
interface BorrowerInformationRepository extends JpaRepository<BorrowerInformation, Integer>{
}

@Repository
interface StatusRepository extends JpaRepository<StatusEntity, Integer>{
}