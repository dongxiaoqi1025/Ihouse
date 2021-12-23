package edu.nuc.ihouse_01.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.nuc.ihouse_01.model.entity.House;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseDao extends BaseMapper<House> {
    IPage<House> selectHouseListByUserId(IPage<House> page, Integer userId);

    void setImage(Integer houseId, String imageUrl);

    void setImages(Integer houseId, String imagesStr);

    void updateRented(Integer houseId);
}
