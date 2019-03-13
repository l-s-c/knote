package cn.buu.note.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.buu.note.entity.FriendDao;

public interface FriendDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FriendDao record);

    int insertSelective(FriendDao record);

    FriendDao selectByPrimaryKey(Long id);
    
    List<FriendDao> selectByMyPhone(Integer myPhone);
    int updateByPrimaryKeySelective(FriendDao record);

    int updateByPrimaryKey(FriendDao record);

	List<FriendDao> selectShowByMyPhone(Integer myPhone);

	boolean checkIfFriend(@Param("myPhone")Integer phone,@Param("frPhone") Integer ophone);
}