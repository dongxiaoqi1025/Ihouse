package edu.nuc.ihouse_01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nuc.ihouse_01.model.entity.Rent;

import java.util.List;

public interface RentService extends IService<Rent> {
    List<Rent> getListByUserId(Integer userId);

    List<Rent> getListByUserIdAndValid(Integer userId);

    List<Rent> getListByHouseId(Integer houseId);

    List<Rent> getListByHouseIdAndValid(Integer houseId);
}
