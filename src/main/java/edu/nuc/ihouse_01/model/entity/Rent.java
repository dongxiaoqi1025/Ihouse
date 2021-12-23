package edu.nuc.ihouse_01.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("rent")
public class Rent {
    @TableId(type = IdType.AUTO)
    private Integer rentId;
    private Integer userId;
    private Integer houseId;
    private LocalDate rentCreated;
    private Integer rentValid;
    private LocalDate rentDeadline;
}
