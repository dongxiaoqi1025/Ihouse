package edu.nuc.ihouse_01.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentModel {
    private Integer id;
    private Integer userId;
    private Integer houseId;
    private String houseName;
    private Integer price;
    private Integer hostId;
    private LocalDate created;
    private LocalDate deadline;
    private Integer valid;

}
