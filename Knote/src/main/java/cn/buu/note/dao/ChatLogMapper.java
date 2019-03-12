package cn.buu.note.dao;

import cn.buu.note.entity.ChatLog;

public interface ChatLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChatLog record);

    int insertSelective(ChatLog record);

    ChatLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChatLog record);

    int updateByPrimaryKey(ChatLog record);
}