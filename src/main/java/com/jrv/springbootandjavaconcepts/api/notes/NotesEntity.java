package com.jrv.springbootandjavaconcepts.api.notes;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notes")
@Data
public class NotesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteId;

    @Column(columnDefinition = "TEXT")
    private String note;

    private String backgroundColor;

}
