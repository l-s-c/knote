package cn.buu.note.service.note;

import java.util.List;

import cn.buu.note.entity.NoteDao;

public interface NoteService {

	List<NoteDao> loadNote(Integer prefix, Integer suffix) throws Exception;

}
