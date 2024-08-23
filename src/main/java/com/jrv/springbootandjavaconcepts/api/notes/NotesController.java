package com.jrv.springbootandjavaconcepts.api.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes/")
@CrossOrigin
public class NotesController {

    @Autowired
    NotesService notesService;

    @GetMapping("all")
    public List<NotesEntity> getAllNotes() {
        return notesService.fetchNotes();
    }

    @PostMapping("update")
    public void updateNotes(@RequestBody List<NotesEntity> notes){
        notesService.updateNotes(notes);
    }
}
