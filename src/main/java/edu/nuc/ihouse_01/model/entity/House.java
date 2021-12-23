package edu.nuc.ihouse_01.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@TableName("house")
public class House implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer houseId;
    private Integer userId;
    private Integer houseMode;
    private Integer housePrice;
    private String houseTime;
    private String houseCity;
    private String houseDistrict;
    private String houseAddress;
    private Integer houseFloor;
    private String houseType;
    private String houseOrientation;
    private String houseFeatures;
    private LocalDate houseCreated;
    private String houseImage;
    private String houseImages;
    private Integer houseRented;
}
