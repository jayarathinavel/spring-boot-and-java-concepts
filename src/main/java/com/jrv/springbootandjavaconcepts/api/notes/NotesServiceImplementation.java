package com.jrv.springbootandjavaconcepts.api.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotesServiceImplementation implements NotesService {

    @Autowired
    NotesRepository notesRepository;

    @Override
    public List<NotesEntity> fetchNotes() {
        return notesRepository.findAll().stream().sorted(Comparator.comparing(NotesEntity::getNoteId)).toList();
    }

    @Override
    public void updateNotes(List<NotesEntity> notes) {
        List<NotesEntity> notesInDatabase = fetchNotes();
        if(notesInDatabase.size() != notes.size()) {
            notesRepository.deleteAllById(findDeletedNoteId(notesInDatabase, notes));
        }
        notesRepository.saveAll(notes);
    }

    private List<Integer> findDeletedNoteId(List<NotesEntity> notesInDatabase, List<NotesEntity> notesFromClient) {
        Set<Integer> noteIdsFromDatabase = notesInDatabase.stream()
                .map(NotesEntity::getNoteId)
                .collect(Collectors.toSet());
        Set<Integer> noteIdsFromClients = notesFromClient.stream()
                .map(NotesEntity::getNoteId)
                .collect(Collectors.toSet());
        Set<Integer> missingNoteIds = new HashSet<>(noteIdsFromDatabase);
        missingNoteIds.removeAll(noteIdsFromClients);
        return missingNoteIds.stream().toList();
    }
}
