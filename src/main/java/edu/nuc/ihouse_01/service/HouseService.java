package edu.nuc.ihouse_01.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.nuc.ihouse_01.model.dto.HouseQueryDto;
import edu.nuc.ihouse_01.model.entity.House;

import java.util.List;

public interface HouseService extends IService<House> {
    void setImage(Integer houseId, String imageUrl);

    void setImages(Integer houseId, String imagesStr);

    IPage<House> getPageByHouseQuery(IPage<House> page, HouseQueryDto houseQueryDto);

    void rented(Integer houseId);

    IPage<House> getPageByUserId(IPage<House> page, Integer userId);

    List<House> getListByUserId(Integer userId);
}
