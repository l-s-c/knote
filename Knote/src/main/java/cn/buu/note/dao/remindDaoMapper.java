package cn.buu.note.dao;

import cn.buu.note.entity.remindDao;

public interface remindDaoMapper {
    int deleteByPrimaryKey(Long id);

	int insert(remindDao record);

	int insertSelective(remindDao record);

	remindDao selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(remindDao record);

	int updateByPrimaryKey(remindDao record);

}