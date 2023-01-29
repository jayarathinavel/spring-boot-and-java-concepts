package com.jrv.springbootandjavaconcepts.api.lend_tracker;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "lt_lend_tracker_lists")
@Getter
@Setter
public class LendTrackerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "lt_seq_lend_lists")
    @SequenceGenerator(name = "lt_seq_lend_lists", sequenceName = "lend_lists_sequence")
    @Column(name = "lend_lists_id")
    private int lendListsId;

    //TODO : Many to one Relation after creating user entity
    @Column(name = "user_id")
    private int userId = 0;

    @Column(name = "borrower_id")
    private int borrowerId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "lend_date")
    private Date lendDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "status_id")
    private int statusId;

    @Column(name = "category")
    private String category;

    @Column(name = "timestamp")
    private Timestamp timestamp;
}

@Entity
@Table(name = "lt_status")
@Getter
@Setter
class StatusEntity{
    @Id
    @Column(name = "status_id")
    private int statusId;

    @Column(name = "status")
    private String status;
}

@Entity
@Table(name = "lt_borrower_information")
@Getter
@Setter
class BorrowerInformation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "lt_seq_borrower_info")
    @SequenceGenerator(name = "lt_seq_borrower_info", sequenceName = "borrower_sequence")
    @Column(name = "borrower_id")
    private int borrowerId;

    @Column(name = "borrower_name")
    private String borrowerName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
}
