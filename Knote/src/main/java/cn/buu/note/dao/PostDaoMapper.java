package cn.buu.note.dao;

import cn.buu.note.entity.PostDao;

public interface PostDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PostDao record);

    int insertSelective(PostDao record);

    PostDao selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PostDao record);

    int updateByPrimaryKey(PostDao record);

	Long selectMaxId();
}