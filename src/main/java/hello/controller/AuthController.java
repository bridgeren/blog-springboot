package hello.controller;


import hello.entity.LoginResult;
import hello.entity.Result;
import hello.entity.User;
import hello.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

/**
 * creat by nickless
 *
 * @Date 2019/10/21 9:46
 */
@Controller
public class AuthController {
    private UserService userService;

    private AuthenticationManager authenticationManager;

    @Inject
    public AuthController(AuthenticationManager authenticationManager, UserService userService) {

        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }


    @RequestMapping("/auth")
    @ResponseBody
    public Object auth() {
       // String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.getUserByUsername(authentication==null?null:authentication.getName());
        if (loggedInUser == null) {
            return LoginResult.failure("用户未登陆");

        } else {
            return LoginResult.success("", loggedInUser,true);
        }
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public Result login(@RequestBody Map<String, String> usernameAndPasswordJson) {

        String username = usernameAndPasswordJson.get("username");
        String passwprd = usernameAndPasswordJson.get("password");
        UserDetails userDetails = null;
        try {
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return LoginResult.failure("用户不存在");

        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, passwprd, userDetails.getAuthorities());

        try {
            authenticationManager.authenticate(token);
            //把用户信息保存在一个地方 Cookie
            SecurityContextHolder.getContext().setAuthentication(token);

            return LoginResult.success("登陆成功", userService.getUserByUsername(username));


        } catch (BadCredentialsException e) {
            return LoginResult.failure("密码不准确");


        }

    }

    /*
     * @Author nickless
     * @Description  z注册
     * @Date 2019/10/26
     * @Param
     * @return result
     **/
    @PostMapping("/auth/register")
    @ResponseBody
    public Result register(@RequestBody Map<String, String> usernameAndPasswordJson) {
        String username = usernameAndPasswordJson.get("username");
        String password = usernameAndPasswordJson.get("password");
        if (password == null || username == null) {
            return LoginResult.failure("用户名或密码不能为空");

        }
        if (username.length() < 1 || username.length() > 15) {
            return LoginResult.failure("invalid username");

        }
        if (password.length() < 6 || password.length() > 16) {
            return LoginResult.failure("invalid password");

        }
        // 并发安全性 设置数据库字段 唯一性
        try {
            userService.save(username, password);
        } catch (DuplicateKeyException e) {
            return LoginResult.failure("用户名已存在");

        }
        return LoginResult.success("注册成功", userService.getUserByUsername(username));


       /* User user = userService.getUserByUsername(username);
        if (user == null) {
            return new Result("ok", "注册成功", userService.getUserByUsername(username));
        } else {
            return new Result("fail", "用户名已存在");
        }*/

    }

    @GetMapping("/auth/logout")
    @ResponseBody
    public Result logout() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userService.getUserByUsername(userName);
        if (loggedInUser == null) {
            return LoginResult.failure("用户未登陆");

        } else {
            SecurityContextHolder.clearContext();

            return new LoginResult("ok", "注销成功",null,false);
        }

    }


}