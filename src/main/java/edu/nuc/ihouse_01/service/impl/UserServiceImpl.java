package edu.nuc.ihouse_01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.nuc.ihouse_01.dao.UserDao;
import edu.nuc.ihouse_01.model.dto.UserModel;
import edu.nuc.ihouse_01.model.entity.User;
import edu.nuc.ihouse_01.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service

public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Override

    public User getById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Override

    public List<User> list() {
        return baseMapper.selectList(null);
    }

    @Override

    public boolean removeById(Serializable id) {
        return baseMapper.deleteById(id)>0?true:false;
    }

    @Override

    public boolean saveOrUpdate(User entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    public void updatePassword(Integer id, String password, String rePassword) {

    }

    @Override
    public UserModel login(QueryWrapper<User> queryWrapper) {
        User user = baseMapper.selectOne(queryWrapper);
        UserModel userModel = new UserModel();
        userModel.setId(user.getUserId());
        userModel.setRid(user.getUserRole());
        userModel.setName(user.getUserName());
        userModel.setPhone(user.getUserPhone());
        userModel.setGender(user.getUserGender());
        userModel.setToken("8888888888");
        return userModel;
    }
}
