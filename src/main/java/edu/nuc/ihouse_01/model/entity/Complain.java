package edu.nuc.ihouse_01.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName
public class Complain {
    @TableId(type = IdType.AUTO)
    private Integer complainId;
    private Integer houseId;
    private Integer userId;
    private LocalDate complainCreated;
    private String complainContent;
    private Integer complainSolve;
}
