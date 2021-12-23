package edu.nuc.ihouse_01.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName
public class Recommend {
    @TableId(type = IdType.AUTO)
    private Integer recommendId;
    private Integer houseId;
    private LocalDate recommendCreated;
    private LocalDate recommendDeadline;
}
