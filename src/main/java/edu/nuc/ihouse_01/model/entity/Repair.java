package edu.nuc.ihouse_01.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName
public class Repair {
    @TableId(type = IdType.AUTO)
    private Integer repairId;
    private Integer houseId;
    private Integer userId;
    private LocalDate repairCreated;
    private String repairContent;
    private Integer repairSolve;
}
