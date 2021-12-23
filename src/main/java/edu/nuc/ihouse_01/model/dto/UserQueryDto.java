package edu.nuc.ihouse_01.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "用户信息修改参数实体")
public class UserQueryDto {
    private Integer id;
    private String phone;
    private String name;
    private String password;
}
