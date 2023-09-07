package com.common.feign;

import com.common.feign.FallbackFactory.AuthFallbackFactory;
import com.common.user.UserInfo;
import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value ="myauth", fallbackFactory = AuthFallbackFactory.class)
public interface AuthClient {
    @PostMapping (value = "/innerVerify/message")
    R<UserInfo> getUserInfo(@RequestHeader(name = "token",required = true) String token, @RequestHeader(name = "refreshToken",required = true) String refreshToken);
}