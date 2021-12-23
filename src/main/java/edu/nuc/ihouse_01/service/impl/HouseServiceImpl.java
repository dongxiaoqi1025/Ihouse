package edu.nuc.ihouse_01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.nuc.ihouse_01.dao.HouseDao;
import edu.nuc.ihouse_01.model.dto.HouseQueryDto;
import edu.nuc.ihouse_01.model.entity.House;
import edu.nuc.ihouse_01.service.HouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class HouseServiceImpl extends ServiceImpl<HouseDao, House> implements HouseService {
    @Override
    public void setImage(Integer houseId, String imageUrl) {
        baseMapper.setImage(houseId, imageUrl);
    }

    @Override
    public void setImages(Integer houseId, String imagesStr) {
        baseMapper.setImages(houseId, imagesStr);
    }

    @Override
    public IPage<House> getPageByHouseQuery(IPage<House> page, HouseQueryDto houseQueryDto) {
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(House::getHouseRented, 0);
        queryWrapper.lambda().eq(House::getHouseCity, houseQueryDto.getCity());

        String district = houseQueryDto.getDistrict();
        if (district != null && !district.equals("")) {
            queryWrapper.lambda().like(House::getHouseDistrict, district);
        }

        Integer price = houseQueryDto.getPrice();
        if (price != null) {
            switch (price) {
                case 1000:
                    queryWrapper.lambda().le(House::getHousePrice, price);
                    break;
                case 3500:
                    queryWrapper.lambda().gt(House::getHousePrice, 3000);
                    queryWrapper.lambda().le(House::getHousePrice, 5000);
                    break;
                case 5000:
                    queryWrapper.lambda().gt(House::getHousePrice, 5000);
                    break;
                default:
                    queryWrapper.lambda().gt(House::getHousePrice, price - 500);
                    queryWrapper.lambda().le(House::getHousePrice, price);
                    break;
            }
        }

        String type = houseQueryDto.getType();
        if (type != null) {
            queryWrapper.lambda().eq(House::getHouseType, houseQueryDto.getType());
        }

        String orientation = houseQueryDto.getOrientation();
        if (orientation != null) {
            queryWrapper.lambda().eq(House::getHouseOrientation, houseQueryDto.getOrientation());
        }

        String feature = houseQueryDto.getFeature();
        if (feature != null) {
            queryWrapper.lambda().like(House::getHouseFeatures, houseQueryDto.getFeature());
        }

        String time = houseQueryDto.getTime();
        if (time != null) {
            queryWrapper.lambda().like(House::getHouseTime, houseQueryDto.getTime());
        }

        Integer floor = houseQueryDto.getFloor();
        if (floor != null) {
            if (floor == 30) {
                queryWrapper.lambda().gt(House::getHouseFloor, floor);
            } else {
                queryWrapper.lambda().gt(House::getHouseFloor, floor - 10);
                queryWrapper.lambda().le(House::getHouseFloor, floor);
            }
        }

        Integer mode = houseQueryDto.getMode();
        if (mode != null) {
            queryWrapper.lambda().eq(House::getHouseMode,mode);
        }
        page = baseMapper.selectPage(page, queryWrapper);
        return page;
    }

    @Override
    public void rented(Integer houseId) {
        baseMapper.updateRented(houseId);
    }

    @Override
    public IPage<House> getPageByUserId(IPage<House> page, Integer userId) {
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(House::getUserId, userId);
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<House> getListByUserId(Integer userId) {
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(House::getUserId, userId);
        return baseMapper.selectList(queryWrapper);
    }
}
