package cn.buu.note.dao;

import java.util.List;

import cn.buu.note.entity.PostDao;
import cn.buu.note.entity.PostResult;

public interface PostDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PostDao record);

    int insertSelective(PostDao record);

    PostDao selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PostDao record);

    int updateByPrimaryKey(PostDao record);

	Long selectMaxId();

	List<PostResult> selectPostByNoteId(Long noteId);
}