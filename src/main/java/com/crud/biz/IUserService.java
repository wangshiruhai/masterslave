package com.crud.biz;

import com.crud.entity.User;
import java.util.List;

/**
 * @author tommy
 * @date 11/6/21
 */
public interface IUserService {

    List<User> findUserList();
}
