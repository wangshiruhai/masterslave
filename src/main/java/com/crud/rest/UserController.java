package com.crud.rest;

import com.crud.biz.IUserService;
import com.crud.entity.User;
import com.crud.entity.vo.UserReq;
import com.crud.utils.ApiResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tommy
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired IUserService userService;

    @GetMapping("/find")
    public ApiResult<List<User>> findUser() {
        return ApiResult.success(userService.findUserList());
    }

    @PostMapping("/save")
    public ApiResult<List<User>> saveUser(@RequestBody UserReq user) {
        return ApiResult.success(userService.saveUser(user));
    }

}
