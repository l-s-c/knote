package cn.buu.note.service.post;

import java.util.List;

import cn.buu.note.entity.PostAgain;
import cn.buu.note.entity.PostDao;
import cn.buu.note.entity.PostResult;

public interface PostService {

	Long savePostToDb(PostDao post) throws Exception;

	void delPostFromDb(String id) throws Exception;

	void reply(PostAgain postAgain) throws Exception;

	List<PostResult> loadPost(Long id) throws Exception;

	List<PostAgain> loadReplyByPostId(Long postId) throws Exception;

	void delPostAgFromDb(String id) throws Exception;

}
