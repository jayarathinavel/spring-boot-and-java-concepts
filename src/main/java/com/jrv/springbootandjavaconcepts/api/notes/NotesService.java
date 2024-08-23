package com.jrv.springbootandjavaconcepts.api.notes;

import java.util.List;

public interface NotesService {
    List<NotesEntity> fetchNotes();

    void updateNotes(List<NotesEntity> notes);
}
