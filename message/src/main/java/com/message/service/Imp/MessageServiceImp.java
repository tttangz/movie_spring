package com.message.service.Imp;

import com.message.service.MessageService;
import com.message.dao.MessageDao;
import com.message.dao.MessageRefDao;
import com.message.pojo.MessageEntity;
import com.message.pojo.MessageRefEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
class MessageServiceImp implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private MessageRefDao messageRefDao;

    @Override
    public String insertMessage(MessageEntity entity) {
        String id = messageDao.insert(entity);
        return id;
    }

    @Override
    public String insertRef(MessageRefEntity entity) {
        String id = messageRefDao.insert(entity);
        return id;
    }

    @Override
    public long searchUnreadCount(int userId) {
        long count = messageRefDao.searchUnreadCount(userId);
        return count;
    }

    @Override
    public long searchLastCount(int userId) {
        long count = messageRefDao.searchLastCount(userId);
        return count;
    }

    @Override
    public List<HashMap> searchMessageByPage(int userId, long start, int length) {
        List<HashMap> list = messageDao.searchMessageByPage(userId, start, length);
        return list;
    }

    @Override
    public HashMap searchMessageById(String id) {
        HashMap map = messageDao.searchMessageById(id);
        return map;
    }

    @Override
    public long updateUnreadMessage(String id) {
        long rows = messageRefDao.updateUnreadMessage(id);
        return rows;
    }

    @Override
    public long deleteMessageRefById(String id) {
        long rows = messageRefDao.deleteMessageRefById(id);
        return rows;
    }

    @Override
    public long deleteUserMessageRef(int userId) {
        long rows=messageRefDao.deleteUserMessageRef(userId);
        return rows;
    }
}

