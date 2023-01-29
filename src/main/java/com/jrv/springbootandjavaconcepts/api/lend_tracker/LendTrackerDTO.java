package com.jrv.springbootandjavaconcepts.api.lend_tracker;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class LendTrackerDTO {
        private int lendListsId;
        private int userId = 0;
        private int borrowerId;
        private String itemName;
        private String itemDescription;
        private Date lendDate;
        private Date dueDate;
        private Date returnDate;
        private Date createdDate;
        private int statusId;
        private String category;
        private Timestamp timestamp;
}

@Getter
@Setter
class StatusDTO{
    private int statusId;
    private String status;
}

@Getter
@Setter
class BorrowerInformationDTO{
    private int borrowerId;
    private String borrowerName;
    private String phoneNumber;
    private String email;
}
