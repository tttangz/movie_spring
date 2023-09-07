package com.auth.controller;

import com.auth.service.UserService;
import com.auth.utils.JwtUtil;
import com.common.user.UserInfo;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/innerVerify")
@Api("验证权限的模块Web接口")
public class VerifyController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    //设置只对内开放 TODO
    @PostMapping("/message")
    @RequiresPermissions(value = {"ROOT", "USER:ADD"}, logical = Logical.OR)
    @ApiOperation("验证权限，通过后返回用户信息")
    public R verifyMessage(@RequestHeader("token") String token, @RequestHeader("refreshToken") String refreshToken){
        HashMap map = userService.searchUserInfo(jwtUtil.getUserId(token));
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(jwtUtil.getUserId(token));
        userInfo.setUserName((String) map.get("name"));
        userInfo.setToken(token);
        userInfo.setRefreshToken(refreshToken);
        return R.ok(userInfo);
    }

//    @PostMapping("/video")
//    @RequiresPermissions(value = {"ROOT", "USER:ADD"}, logical = Logical.OR)
//    public R verifyVideo(){
//        HashMap userInfo = userService.searchUserInfo(jwtUtil.getUserId(token));
//        return R.ok(userInfo);
//    }
}
