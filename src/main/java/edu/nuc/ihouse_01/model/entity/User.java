package edu.nuc.ihouse_01.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private Integer userAge;
    private Integer userGender;
    private String userPhone;
    private String userPassword;
    private String userIdentity;
    private Integer userRole;
}