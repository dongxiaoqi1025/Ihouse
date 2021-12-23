package edu.nuc.ihouse_01.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.nuc.ihouse_01.model.dto.AjaxResult;
import edu.nuc.ihouse_01.model.entity.House;
import edu.nuc.ihouse_01.model.entity.Rent;
import edu.nuc.ihouse_01.model.entity.Rental;
import edu.nuc.ihouse_01.model.entity.User;
import edu.nuc.ihouse_01.service.HouseService;
import edu.nuc.ihouse_01.service.RentService;
import edu.nuc.ihouse_01.service.RentalService;
import edu.nuc.ihouse_01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private RentService rentService;
    @Autowired
    private RentalService rentalService;

    @GetMapping("/users")
    public AjaxResult getUserListPage(String query,Integer pagenum,Integer pagesize){
        IPage<User> page = new Page<>();
        page.setCurrent(pagenum);
        page.setSize(pagesize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        page = userService.page(page, queryWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("users",page.getRecords());
        map.put("totalpage",page.getPages());
        map.put("pagenum",page.getCurrent());
        AjaxResult<Map> result = AjaxResult.success(map);
        return result;
    }
    @GetMapping("/houses")
    public AjaxResult getHouseListPage(String query,Integer pagenum,Integer pagesize){
        IPage<House> page = new Page<>();
        page.setCurrent(pagenum);
        page.setSize(pagesize);
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        page = houseService.page(page, queryWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("houses",page.getRecords());
        map.put("totalpage",page.getPages());
        map.put("pagenum",page.getCurrent());
        AjaxResult<Map> result = AjaxResult.success(map);
        return result;
    }
    @GetMapping("/rents")
    public AjaxResult getRentListPage(String query,Integer pagenum,Integer pagesize){
        IPage<Rent> page = new Page<>();
        page.setCurrent(pagenum);
        page.setSize(pagesize);
        QueryWrapper<Rent> queryWrapper = new QueryWrapper<>();
        page = rentService.page(page, queryWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("rents",page.getRecords());
        map.put("totalpage",page.getPages());
        map.put("pagenum",page.getCurrent());
        AjaxResult<Map> result = AjaxResult.success(map);
        return result;
    }
    @GetMapping("/rentals")
    public AjaxResult getRentalListPage(String query,Integer pagenum,Integer pagesize){
        IPage<Rental> page = new Page<>();
        page.setCurrent(pagenum);
        page.setSize(pagesize);
        QueryWrapper<Rental> queryWrapper = new QueryWrapper<>();
        page = rentalService.page(page, queryWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("rentals",page.getRecords());
        map.put("totalpage",page.getPages());
        map.put("pagenum",page.getCurrent());
        AjaxResult<Map> result = AjaxResult.success(map);
        return result;
    }
}
