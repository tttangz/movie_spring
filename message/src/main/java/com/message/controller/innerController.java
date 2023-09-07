package com.message.controller;

import com.message.pojo.MessageEntity;
import com.message.task.MessageTask;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/innerMessage")
@Api("内部消息模块网络接口")
public class innerController {
    @Autowired
    private MessageTask messageTask;

    @PostMapping("/sendRegistMessage")
    @ApiOperation("发送注册消息")
    public R sendRegistMessage(@RequestParam(value = "topic") String topic) {
        String msg = "你的个人资料已经被成功修改";
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(0);  //系统自动发出
        entity.setSenderPhoto("../../static/system.jpg");
        entity.setSenderName("系统消息");
        entity.setMsg(msg);
        entity.setSendTime(new Date());
        messageTask.sendAsync(topic+"", entity);
        return R.ok();
    }

    @PostMapping("/recvLoginMessage")
    @ApiOperation("登录接收消息")
    public R recvLoginMessage(@RequestParam(value = "topic") String topic) {
        int i = messageTask.receive(topic+"");
        return R.ok();
    }
}
