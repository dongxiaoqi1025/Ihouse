package edu.nuc.ihouse_01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.nuc.ihouse_01.model.dto.UserModel;
import edu.nuc.ihouse_01.model.entity.User;


public interface UserService extends IService<User> {
    void updatePassword(Integer id,String password,String rePassword);
    UserModel login(QueryWrapper<User> queryWrapper);
}
