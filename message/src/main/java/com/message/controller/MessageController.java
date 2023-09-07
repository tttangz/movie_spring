package com.message.controller;

import com.message.form.DeleteMessageRefByIdForm;
import com.message.form.SearchMessageByPageForm;
import com.message.form.UpdateUnreadMessageForm;
import com.message.service.MessageService;
import com.message.task.MessageTask;
import com.common.threads.ThreadLocalUser;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/message")
@Api("消息模块网络接口")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ThreadLocalUser threadLocalUser;

    @Autowired
    private MessageTask messageTask;

     public R searchMessageByPage(@Valid @RequestBody SearchMessageByPageForm form) {
        int page = form.getPage();
        int length = form.getLength();
        long start = (page - 1) * length;
        int userId = threadLocalUser.getUser().getUserId();
        List<HashMap> list = messageService.searchMessageByPage(userId, start, length);
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("result", list);
        return R.ok(data);
    }
    @PostMapping("/updateUnreadMessage")
    @ApiOperation("未读消息更新成已读消息")
    public R updateUnreadMessage(@Valid @RequestBody UpdateUnreadMessageForm form) {
        long rows = messageService.updateUnreadMessage(form.getId());
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("result", rows == 1 ? true : false);
        return R.ok(data);
    }

    @PostMapping("/deleteMessageRefById")
    @ApiOperation("删除消息")
    public R deleteMessageRefById(@Valid @RequestBody DeleteMessageRefByIdForm form){
        long rows=messageService.deleteMessageRefById(form.getId());
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("result", rows == 1 ? true : false);
        return R.ok(data);
    }

    @GetMapping("/refreshMessage")
    @ApiOperation("刷新用户的消息")
    public R refreshMessage() {
        int userId = threadLocalUser.getUser().getUserId();
        //异步接收消息
        messageTask.receiveAysnc(userId + "");
        //查询接收了多少条消息
        long lastRows=messageService.searchLastCount(userId);
        //查询未读数据
        long unreadRows = messageService.searchUnreadCount(userId);
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("lastRows", lastRows);
        data.put("unreadRows", unreadRows);
        return R.ok(data);
    }

    @GetMapping("/test")
    public R test() {
        return R.ok();
    }
}
