package cn.buu.note.dao;

import java.util.List;

import cn.buu.note.entity.VersionDao;

public interface VersionDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VersionDao record);

    int insertSelective(VersionDao record);

    VersionDao selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VersionDao record);

    int updateByPrimaryKey(VersionDao record);

	VersionDao getNewVersion();

	List<VersionDao> selectAllVersion();
}