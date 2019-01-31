package cn.buu.note.dao;

import org.apache.ibatis.annotations.Param;

import cn.buu.note.entity.LikeDao;

public interface LikeDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LikeDao record);

    int insertSelective(LikeDao record);

    LikeDao selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LikeDao record);

    int updateByPrimaryKey(LikeDao record);

	int deleteByPhoneAndNoteId(@Param("phone") Integer phone, @Param("noteId") Long noteId);

	LikeDao selectIfLike(@Param("phone") Integer phone, @Param("noteId") Long noteId);
}