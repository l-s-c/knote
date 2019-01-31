package cn.buu.note.service.like;

public interface LikeService {

	int likeNote(Long noteId) throws Exception;

	void cancelLike(Long noteId) throws Exception;

	boolean getLike(Long noteId) throws Exception;

}
