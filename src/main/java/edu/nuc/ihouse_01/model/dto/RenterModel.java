package edu.nuc.ihouse_01.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RenterModel {
    private Integer id;
    private LocalDate created;
    private LocalDate deadline;
    private Integer price;
    private Integer valid;
    private Integer houseId;
    private String houseName;
    private Integer userId;
    private String userName;
    private String userPhone;

}
