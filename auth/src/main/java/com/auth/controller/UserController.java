package com.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.auth.form.LoginForm;
import com.auth.form.RegisterForm;
import com.auth.service.Imp.UserServiceImp;
import com.auth.utils.JwtUtil;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Set;
@RestController
@RequestMapping("/user")
@Api("注册登录模块Web接口")
public class UserController {
    @Autowired
    private UserServiceImp userService;
    @Autowired
    private JwtUtil jwtUtil;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    @ApiOperation("登陆系统")
    public R login(@Valid @RequestBody LoginForm form, @RequestHeader("token") String token) {
        Integer id;
        String refreshToken = "";
        if (StrUtil.isNotEmpty(token)) {
            try {
                jwtUtil.verifierToken(token); //检查令牌是否本系统签发&&是否过期
                id = jwtUtil.getUserId(token);
            } catch (Exception e){
                id = userService.login(form.getCode());
                token = jwtUtil.createToken(id);
                refreshToken = jwtUtil.createRefreshToken(id);
            }
        } else {
            id = userService.login(form.getCode());
            token = jwtUtil.createToken(id);
            refreshToken = jwtUtil.createRefreshToken(id);
        }
        //TODO 如果有需要可以在这里设置用户信息的线程变量
//        HashMap map = userService.searchUserInfo(id);
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId(id);
//        userInfo.setUserName((String) map.get("name"));
//        threadLocalUser.setUser();


        Set<String> permsSet = userService.searchUserPermissions(id);
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("token", token);
        data.put("refreshToken", refreshToken);
        data.put("permission", permsSet);
        return R.ok(data, "登陆成功");
    }

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public R register(@Valid @RequestBody RegisterForm form) {
        int id = userService.registerUser(form.getRegisterCode(), form.getCode(), form.getNickname(), form.getPhoto());
        //TODO 如果有需要可以在这里设置用户信息的线程变量
//        HashMap map = userService.searchUserInfo(id);
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId(id);
//        userInfo.setUserName((String) map.get("name"));
//        threadLocalUser.setUser();
        String token = jwtUtil.createToken(id);
        String refreshToken = jwtUtil.createRefreshToken(id);
        Set<String> permsSet = userService.searchUserPermissions(id);
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("token", token);
        data.put("refreshToken", refreshToken);
        data.put("permission", permsSet);
        return R.ok(data, "用户注册成功");
    }

    @GetMapping("/test/getToken")
    @ApiOperation("登陆系统")
    public R testLogin() {
        Integer id = 10;
        String token = jwtUtil.createToken(id);
        String refreshToken = jwtUtil.createRefreshToken(id);
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("token", token);
        data.put("refreshToken", refreshToken);
        return R.ok(data);
    }
}
