package cn.buu.note.service.post;

import cn.buu.note.entity.PostDao;

public interface PostService {

	Long savePostToDb(PostDao post) throws Exception;

	void delPostFromDb(String id) throws Exception;

}
