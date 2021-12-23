package com.crud.biz;

import com.crud.entity.User;
import com.crud.entity.vo.UserReq;
import java.util.List;

/**
 * @author tommy
 * @date 11/6/21
 */
public interface IUserService {

    List<User> findUserList();

    boolean saveUser(UserReq user);
}
