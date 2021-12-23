package edu.nuc.ihouse_01.model.dto;

import com.google.gson.Gson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ApiModel("house查询额请求参数")
public class HouseQueryDto implements Serializable {
    @ApiModelProperty(value = "出租方式")
    private Integer mode;
    @ApiModelProperty(value = "月租租金")
    private Integer price;
    @ApiModelProperty(value = "租期")
    private String time;
    @ApiModelProperty(value = "所在城市")
    private String city;
    @ApiModelProperty(value = "所在地区")
    private String district;
    @ApiModelProperty(value = "具体地址")
    private String address;
    @ApiModelProperty(value = "楼层")
    private Integer floor;
    @ApiModelProperty(value = "户型")
    private String type;
    @ApiModelProperty(value = "房间朝向")
    private String orientation;
    @ApiModelProperty(value = "房间特色")
    private String feature;
    @ApiModelProperty(value = "创建日期")
    private LocalDate created;
    @ApiModelProperty(value = "当前页")
    private Integer currentPage;
    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @Override
    public String toString() {
        Gson gson = new Gson();

        return gson.toJson(this);
    }
}
