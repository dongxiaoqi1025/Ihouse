package edu.nuc.ihouse_01.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.nuc.ihouse_01.model.dto.UserModel;
import edu.nuc.ihouse_01.model.entity.House;
import edu.nuc.ihouse_01.model.entity.Rent;
import edu.nuc.ihouse_01.model.entity.User;
import edu.nuc.ihouse_01.service.HouseService;
import edu.nuc.ihouse_01.service.RentService;
import edu.nuc.ihouse_01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private RentService rentService;
    @PostMapping("/login")
    public String login(HttpServletRequest request, @RequestParam(value = "phone") String phone, @RequestParam(value = "password") String password) {
        HttpSession session = request.getSession();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserPhone, phone);
        queryWrapper.lambda().eq(User::getUserPassword, password);
        UserModel user = userService.login(queryWrapper);
        session.setAttribute("user",user);
        session.setAttribute("city","太原");
        return "index";
    }
    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/registerPage")
    public String registerPage(){
        return "register";
    }
    @GetMapping("/")
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("city","太原");
        return "index";
    }
    @GetMapping("/user/publishPage")
    public String publishPage(){
        return "publish";
    }

    @GetMapping("/user/contract")
    public String contract(HttpServletRequest request,Integer houseId){
        HttpSession session = request.getSession();
        House house = houseService.getById(houseId);
        User house_user1 = userService.getById(house.getUserId());
        UserModel house_user = new UserModel(house_user1,null);

        session.setAttribute("house",house);
        session.setAttribute("house_user",house_user);
        return "contract";
    }
    @GetMapping("/user/rentHouse")
    public String rentHouse(HttpServletRequest request,Integer houseId,Integer userId){
        HttpSession session = request.getSession();
        houseService.rented(houseId);
        Rent rent = new Rent();
        rent.setHouseId(houseId);
        rent.setUserId(userId);
        rent.setRentCreated(LocalDate.now());
        rent.setRentDeadline(LocalDate.now().plusMonths(1));
        rentService.save(rent);
        return "index";
    }
    @GetMapping("/user/rentup")
    @ResponseBody
    private String rentup(){
        return "center/rentup";
    }
}
