package edu.nuc.ihouse_01.model.dto;

import com.google.gson.Gson;
import edu.nuc.ihouse_01.model.entity.House;
import lombok.Data;

import java.time.LocalDate;
@Data
public class HouseModel {
    private Integer houseId;
    private Integer userId;
    private String mode;
    private Integer price;
    private String time;
    private String city;
    private String district;
    private String address;
    private Integer floor;
    private String type;
    private String orientation;
    private String[] features;
    private LocalDate created;
    private String image;
    private String[] images;
    private Integer rented;

    public HouseModel(House house) {
        this.houseId = house.getHouseId();
        this.userId = house.getUserId();
        this.mode = house.getHouseMode() == 1 ? "整租" : "合租";
        this.price = house.getHousePrice();
        this.time = house.getHouseTime();
        this.city = house.getHouseCity();
        this.district = house.getHouseDistrict();
        this.address = house.getHouseAddress();
        this.floor = house.getHouseFloor();
        this.type = house.getHouseType();
        this.orientation = house.getHouseOrientation();
        Gson gson = new Gson();
        this.features =   gson.fromJson(house.getHouseFeatures(),String[].class);
        this.created = house.getHouseCreated();
        this.image = house.getHouseImage();
        this.images = gson.fromJson(house.getHouseImages(),String[].class);
        this.rented = house.getHouseRented();
    }
}
