package com.message.service;

import com.message.pojo.MessageEntity;
import com.message.pojo.MessageRefEntity;

import java.util.HashMap;
import java.util.List;

public interface MessageService {

    public String insertMessage(MessageEntity entity);

    public String insertRef(MessageRefEntity entity);

    public long searchUnreadCount(int userId);

    public long searchLastCount(int userId);

    public List<HashMap> searchMessageByPage(int userId, long start, int length) ;

    public HashMap searchMessageById(String id);

    public long updateUnreadMessage(String id) ;

    public long deleteMessageRefById(String id);

    public long deleteUserMessageRef(int userId);
}