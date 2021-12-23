package com.crud.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crud.annotation.DataSourceSwitcher;
import com.crud.biz.IUserService;
import com.crud.context.DataSourceEnum;
import com.crud.entity.User;
import com.crud.entity.vo.UserReq;
import com.crud.mapper.UserMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tommy
 * @date 11/6/21
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    @DataSourceSwitcher(DataSourceEnum.SLAVE)
    @Override
    public List<User> findUserList() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        return userMapper.selectList(queryWrapper);
    }

    @DataSourceSwitcher(DataSourceEnum.MASTER)
    @Override
    public boolean saveUser(UserReq user) {
        List<User> userList = findUserList();
        System.out.println(userList.size());
        User dbUser = new User();
        BeanUtils.copyProperties(user,dbUser);
        boolean result = userMapper.insert(dbUser) > 0;
        return result;
    }
}
