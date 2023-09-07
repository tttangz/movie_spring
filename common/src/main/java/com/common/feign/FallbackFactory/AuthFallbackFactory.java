package com.common.feign.FallbackFactory;

import com.common.feign.AuthClient;
import com.common.user.UserInfo;
import com.ruoyi.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author ruoyi
 */
@Component
public class AuthFallbackFactory implements FallbackFactory<AuthClient> {
    private static final Logger log = LoggerFactory.getLogger(AuthFallbackFactory.class);

    @Override
    public AuthClient create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new AuthClient() {
            @Override
            public R<UserInfo> getUserInfo(String token, String refreshToken) {
                UserInfo userInfo = new UserInfo();
                userInfo.setErrorMsg(throwable.getMessage());
                return R.fail(userInfo);
            }
        };
    }
}


