package cn.buu.note.service.note;

import java.util.List;

import cn.buu.note.entity.NoteDao;
import cn.buu.note.entity.remindDao;

public interface NoteService {

	List<NoteDao> loadNote(Integer prefix, Integer suffix) throws Exception;

	List<NoteDao> loadMyNote() throws Exception;

	NoteDao loadNoteDesc(long id) throws Exception;

	void insertNote(String title, String text, int label) throws Exception;

	void insertReminder(remindDao remind) throws Exception;

	boolean analyzeBase64(String base64) throws Exception;

	remindDao loadReminderDesc(long id) throws Exception;

	void updateReminder(remindDao remind) throws Exception;

	void updateNote(NoteDao note) throws Exception;

}
