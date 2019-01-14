package cn.buu.note.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;		//Mybatis
	
import cn.buu.note.entity.NoteDao;

public interface NoteDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoteDao record);

    int insertSelective(NoteDao record);

    NoteDao selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NoteDao record);

    int updateByPrimaryKey(NoteDao record);
    /**
     *  按限制数量查询
     * */
	List<NoteDao> selectByLimit(@Param("prefix") Integer prefix,
								@Param("suffix") Integer suffix);
}