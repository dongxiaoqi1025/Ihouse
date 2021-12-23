package edu.nuc.ihouse_01.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("rental")
public class Rental {
    @TableId(type = IdType.AUTO)
    private Integer rentalId;
    private Integer userId;
    private Integer houseId;
    private Integer hostId;
    private Integer rentalMoney;
    private LocalDate rentalTime;
}
