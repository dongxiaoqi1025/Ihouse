package edu.nuc.ihouse_01.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.nuc.ihouse_01.model.dto.RentModel;
import edu.nuc.ihouse_01.model.dto.RentalModel;
import edu.nuc.ihouse_01.model.dto.RenterModel;
import edu.nuc.ihouse_01.model.dto.UserModel;
import edu.nuc.ihouse_01.model.entity.House;
import edu.nuc.ihouse_01.model.entity.Rent;
import edu.nuc.ihouse_01.model.entity.Rental;
import edu.nuc.ihouse_01.model.entity.User;
import edu.nuc.ihouse_01.service.HouseService;
import edu.nuc.ihouse_01.service.RentService;
import edu.nuc.ihouse_01.service.RentalService;
import edu.nuc.ihouse_01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RentService rentService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private RentalService rentalService;


    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.setAttribute("user", null);
        return "redirect:/";
    }

    @GetMapping("/center")
    public String center() {
        return "center";
    }

    private List<RentModel> rent2RentModel(List<Rent> rentList){
        List<RentModel> rents = new ArrayList<>();
        for (Rent rent : rentList) {
            House house = houseService.getById(rent.getHouseId());
            RentModel rentModel = new RentModel();
            rentModel.setId(rent.getRentId());
            rentModel.setUserId(rent.getUserId());
            rentModel.setHostId(house.getUserId());
            rentModel.setHouseId(house.getHouseId());
            rentModel.setHouseName(house.getHouseAddress());
            rentModel.setPrice(house.getHousePrice());
            rentModel.setCreated(rent.getRentCreated());
            rentModel.setDeadline(rent.getRentDeadline());
            rentModel.setValid(rent.getRentValid());
            rents.add(rentModel);
        }
        return rents;
    }
    @GetMapping("/center/renting")
    public String renting(HttpSession session, Model model) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Integer userId = userModel.getId();
        List<RentModel> rents = null;
        List<Rent> rentList = rentService.getListByUserId(userId);
        if (rentList.size() != 0) {
            rents = rent2RentModel(rentList);
        }
        model.addAttribute("rents", rents);
        return "center/renting";
    }
    @GetMapping("/center/rented")
    public String rented(HttpSession session, Model model) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Integer userId = userModel.getId();
        List<RentModel> rents = null;
        List<Rent> rentList = rentService.getListByUserIdAndValid(userId);
        if (rentList.size() != 0) {
            rents = rent2RentModel(rentList);
        }
        model.addAttribute("rents", rents);
        return "center/rented";
    }
    @GetMapping("/center/myHouse")
    public String myHouse(HttpSession session, Model model) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Integer userId = userModel.getId();

        IPage<House> page = new Page<>();
        page.setCurrent(1);//当前页
        page.setSize(10);//每页条数
        //查询参数

        //执行
        page = houseService.getPageByUserId(page, userId);
        List<House> houses = page.getRecords();

        model.addAttribute("houses", houses);
        return "center/myHouse";
    }
    @GetMapping("/center/myRenter")
    public String myRenter(HttpSession session, Model model) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Integer userId = userModel.getId();
        List<RenterModel> renters = new ArrayList<>();
        List<House> houseList = houseService.getListByUserId(userId);
        for (House house : houseList) {
            List<Rent> rentList = rentService.getListByHouseId(house.getHouseId());
            for (Rent rent : rentList) {
                User user = userService.getById(rent.getUserId());
                RenterModel renter = new RenterModel();
                renter.setId(rent.getRentId());
                renter.setPrice(house.getHousePrice());
                renter.setCreated(rent.getRentCreated());
                renter.setDeadline(rent.getRentDeadline());
                renter.setValid(rent.getRentValid());
                renter.setUserId(user.getUserId());
                renter.setUserName(user.getUserName());
                renter.setUserPhone(user.getUserPhone());
                renter.setHouseId(house.getHouseId());
                renter.setHouseName(house.getHouseAddress());
                renters.add(renter);
            }
        }
        model.addAttribute("renters", renters);
        return "center/myRenter";
    }
    @GetMapping("/center/myRenterOrder")
    public String myRenterOrder(HttpSession session, Model model) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Integer userId = userModel.getId();
        List<RenterModel> renters = new ArrayList<>();
        List<House> houseList = houseService.getListByUserId(userId);
        for (House house : houseList) {
            List<Rent> rentList = rentService.getListByHouseIdAndValid(house.getHouseId());
            for (Rent rent : rentList) {
                User user = userService.getById(rent.getUserId());
                RenterModel renter = new RenterModel();
                renter.setId(rent.getRentId());
                renter.setPrice(house.getHousePrice());
                renter.setCreated(rent.getRentCreated());
                renter.setDeadline(rent.getRentDeadline());
                renter.setValid(rent.getRentValid());
                renter.setUserId(user.getUserId());
                renter.setUserName(user.getUserName());
                renter.setUserPhone(user.getUserPhone());
                renter.setHouseId(house.getHouseId());
                renter.setHouseName(house.getHouseAddress());
                renters.add(renter);
            }
        }
        model.addAttribute("renters", renters);
        return "center/myRenterOrder";
    }
    @GetMapping("/center/rentup")
    public String rentup(HttpSession session, Model model){
        UserModel userModel = (UserModel) session.getAttribute("user");
        Integer userId = userModel.getId();

        IPage<Rental> page = new Page<>();
        page.setCurrent(1);//当前页
        page.setSize(10);//每页条数
        //查询参数
        QueryWrapper<Rental> rentalQueryWrapper = new QueryWrapper<>();
        rentalQueryWrapper.lambda().eq(Rental::getHostId,userId);
        //执行
        page = rentalService.page(page, rentalQueryWrapper);
        List<RentalModel> rentals = new ArrayList<>();
        List<Rental> rentalList = page.getRecords();
        for (Rental rental : rentalList ) {
            RentalModel rentalModel = new RentalModel();
            rentalModel.setId(rental.getRentalId());
            rentalModel.setMoney(rental.getRentalMoney());
            rentalModel.setTime(rental.getRentalTime());
            User user = userService.getById(rental.getHostId());
            rentalModel.setHostId(user.getUserId());
            rentalModel.setHostName(user.getUserName());
            rentalModel.setHostPhone(user.getUserPhone());
            House house = houseService.getById(rental.getHouseId());
            rentalModel.setHouseId(house.getHouseId());
            rentalModel.setHouseName(house.getHouseAddress());
            rentals.add(rentalModel);
        }

        model.addAttribute("rentals", rentals);
        return "center/rentup";
    }
    @PostMapping("/repassword")
    public String password(Integer id,String password,String rePassword){

        return "";
    }
    @GetMapping("/center/rentback")
    public String rentback(HttpSession session, Model model){
        UserModel userModel = (UserModel) session.getAttribute("user");
        Integer userId = userModel.getId();

        IPage<Rental> page = new Page<>();
        page.setCurrent(1);//当前页
        page.setSize(10);//每页条数
        //查询参数
        QueryWrapper<Rental> rentalQueryWrapper = new QueryWrapper<>();
        rentalQueryWrapper.lambda().eq(Rental::getHostId,userId);
        //执行
        page = rentalService.page(page, rentalQueryWrapper);
        List<RentalModel> rentals = new ArrayList<>();
        List<Rental> rentalList = page.getRecords();
        for (Rental rental : rentalList ) {
            RentalModel rentalModel = new RentalModel();
            rentalModel.setId(rental.getRentalId());
            rentalModel.setMoney(rental.getRentalMoney());
            rentalModel.setTime(rental.getRentalTime());
            User user = userService.getById(rental.getUserId());
            rentalModel.setUserId(user.getUserId());
            rentalModel.setUserName(user.getUserName());
            rentalModel.setUserPhone(user.getUserPhone());
            House house = houseService.getById(rental.getHouseId());
            rentalModel.setHouseId(house.getHouseId());
            rentalModel.setHouseName(house.getHouseAddress());
            rentals.add(rentalModel);
        }

        model.addAttribute("rentals", rentals);

        return "center/rentback";
    }

    @GetMapping("/center/info")
    public String info(){
        return "center/info";
    }
    @GetMapping("/center/identity")
    public String identity(){
        return "center/identity";
    }

}
