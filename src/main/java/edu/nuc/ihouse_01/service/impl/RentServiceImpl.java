package edu.nuc.ihouse_01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.nuc.ihouse_01.dao.RentDao;
import edu.nuc.ihouse_01.model.entity.Rent;
import edu.nuc.ihouse_01.service.RentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl extends ServiceImpl<RentDao, Rent> implements RentService {
    @Override
    public List<Rent> getListByUserId(Integer userId) {
        QueryWrapper<Rent> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Rent::getUserId,userId);
        queryWrapper.lambda().eq(Rent::getRentValid,1);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Rent> getListByUserIdAndValid(Integer userId) {
        QueryWrapper<Rent> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Rent::getUserId,userId);
        queryWrapper.lambda().eq(Rent::getRentValid,0);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Rent> getListByHouseId(Integer houseId) {
        QueryWrapper<Rent> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Rent::getHouseId,houseId);
        queryWrapper.lambda().eq(Rent::getRentValid,1);
        return baseMapper.selectList(queryWrapper);
    }

    public List<Rent> getListByHouseIdAndValid(Integer houseId) {
        QueryWrapper<Rent> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Rent::getHouseId,houseId);
        queryWrapper.lambda().eq(Rent::getRentValid,0);
        return baseMapper.selectList(queryWrapper);
    }
}
