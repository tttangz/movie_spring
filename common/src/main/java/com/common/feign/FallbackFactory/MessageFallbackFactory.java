package com.common.feign.FallbackFactory;

import com.common.feign.MessageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 消息服务降级处理
 *
 * @author ruoyi
 */
@Component
public class MessageFallbackFactory implements FallbackFactory<MessageClient> {
    private static final Logger log = LoggerFactory.getLogger(MessageFallbackFactory.class);

    @Override
    public MessageClient create(Throwable throwable) {
        log.error("消息服务调用失败:{}", throwable.getMessage());
        return new MessageClient() {
            @Override
            public void sendRegistMessage(String topic){
            }

            @Override
            public void recvRegistMessage(String topic) {
            }
        };
    }
}