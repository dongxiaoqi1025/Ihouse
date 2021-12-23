package edu.nuc.ihouse_01.model.dto;

import edu.nuc.ihouse_01.model.entity.User;
import lombok.Data;

@Data
public class UserModel {
    private Integer id;
    private Integer rid;
    private String name;
    private String phone;
    private Integer gender;
    private String token;

    public UserModel(){}

    public UserModel(User user,String token){
        this.id = user.getUserId();
        this.id = user.getUserRole();
        this.name = user.getUserName();
        this.phone = user.getUserPhone();
        this.token = token;
    }
}
