package edu.nuc.ihouse_01.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalModel {
    private Integer id;
    private Integer money;
    private LocalDate time;
    private Integer houseId;
    private String houseName;
    private Integer userId;
    private String userName;
    private String userPhone;
    private Integer hostId;
    private String hostName;
    private String hostPhone;

}
