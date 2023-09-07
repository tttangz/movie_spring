package com.common.feign;

import com.common.feign.FallbackFactory.MessageFallbackFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value ="message", fallbackFactory = MessageFallbackFactory.class)
public interface MessageClient {

    @PostMapping (value = "/innerMessage/recvLoginMessage")
    void recvRegistMessage(@RequestParam(name = "topic",required = true) String topic);

    @PostMapping (value = "/innerMessage/sendRegistMessage")
    void sendRegistMessage(@RequestParam(name = "topic",required = true) String topic);

}