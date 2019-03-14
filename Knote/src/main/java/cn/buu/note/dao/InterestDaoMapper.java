package cn.buu.note.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.buu.note.entity.InterestDao;

public interface InterestDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InterestDao record);

    int insertSelective(InterestDao record);

    InterestDao selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InterestDao record);

    int updateByPrimaryKey(InterestDao record);

	List<Map<String,Object>> selectWhoLikeMe(Integer phone);

	int updataStateById(@Param("id") Long id, @Param("state") int state);
}