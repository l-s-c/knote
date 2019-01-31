package cn.buu.note.service.like;

import java.util.List;

import cn.buu.note.entity.NoteDao;

public interface LikeService {

	int likeNote(Long noteId) throws Exception;

	void cancelLike(Long noteId) throws Exception;

	boolean getLike(Long noteId) throws Exception;

	List<NoteDao> loadLikeNote() throws Exception;

}
