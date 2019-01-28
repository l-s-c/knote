package cn.buu.note.dao;

import cn.buu.note.entity.PostAgain;

public interface PostAgainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PostAgain record);

    int insertSelective(PostAgain record);

    PostAgain selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PostAgain record);

    int updateByPrimaryKey(PostAgain record);
    
    /**删除楼层中的子评论*/
    int deleteByPostId (Long postId);
}