package edu.nuc.ihouse_01.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.nuc.ihouse_01.model.dto.AjaxResult;
import edu.nuc.ihouse_01.model.entity.House;
import edu.nuc.ihouse_01.model.entity.User;
import edu.nuc.ihouse_01.service.HouseService;
import edu.nuc.ihouse_01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/portal")
public class PortalController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;
    /**
     * 登录
     * @return
     */
    @RequestMapping("/loginPage")
    public String login(){
        return "login";
    }
    //主页
    @GetMapping("/index")
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();
        //首页轮播图
        List<House> indexlbList = houseService.list();
        session.setAttribute("indexlbList",indexlbList);
        return "index";
    }
    //


    /**
     * 根据id获取用户信息
     *
     * @param id 用户的id
     * @return 返回一个用户对象
     */
    @GetMapping("/getUser/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") Long id) {
        User userInfo = userService.getById(id);
        return userInfo;
    }

    /**
     * 获取全部用户
     *
     * @return 返回一个list
     */
    @GetMapping("/getList")
    @ResponseBody
    public List<User> getList() {
        List<User> userInfoList = userService.list();
        return userInfoList;
    }

    /**
     * 全部用户 分页查询
     *
     * @return
     */
    @GetMapping("/getListPage")
    @ResponseBody
    public IPage<User> getUserListPage(@RequestParam("currentPage") Integer currentPage, @RequestParam("PageSize") Integer pageSize) {
        IPage<User> page = new Page<>();
        page.setCurrent(currentPage);//当前页
        page.setSize(pageSize);//每页条数
        page = userService.page(page);
        return page;
    }

    /**
     * 根据指定的字段查询用户信息
     *
     * @return 返回某些用户对象
     */
    @GetMapping("/getListMap")
    @ResponseBody
    public List<User> getUserListMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 20);
        List<User> userList = userService.listByMap(map);
        return userList;
    }

    /**
     * 用户修改密码
     *
     * @param user       user对象包含了用户的全部信息
     * @param rePassword 新密码
     * @return
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public AjaxResult updateUserPassword(User user, String rePassword) {
        userService.updatePassword(user.getUserId(), user.getUserPassword(), rePassword);
        return AjaxResult.success();
    }

    /**
     * 新增用户信息 注册新用户
     *
     * @param user 一个完整的用户对象
     * @return void
     */
    @PostMapping("/saveUser")
    @ResponseBody
    public void saveInfo(User user) {
        userService.save(user);
    }

    /**
     * 更改用户信息
     *
     * @return void
     */
    @PutMapping("/updateUser")
    @ResponseBody
    public void updateInfo() {
        User userInfo = new User();
        userService.updateById(userInfo);
    }

    /**
     * saveOrUpdate
     *
     * @return void
     */
    @PostMapping("/saveOrUpdateInfo")
    @ResponseBody
    public void saveOrUpdateInfo() {
        User userInfo = new User();
        userService.saveOrUpdate(userInfo);
    }

    /**
     * 根据id删除用户信息
     *
     * @param id 用户id
     * @return void
     */
    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody
    public void deleteInfo(@PathVariable("id") Long id) {
        userService.removeById(id);
    }


    /**
     * MyBatisPlus根据条件查询 QueryWrapper条件构造器
     *
     * @param age
     * @return
     */
    @GetMapping("/getIfoQuery/{age}")
    @ResponseBody
    public Map<String, Object> getIfoQuery(@PathVariable("age") Integer age) {
        Map<String, Object> result = new HashMap<>();
        //查询年龄等于18岁的学生信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //使用lambda表达式
        queryWrapper.lambda().eq(User::getUserAge, age);
        List<User> userInfos = userService.list(queryWrapper);
        result.put("userInfos", userInfos);
        //查询成绩>60 且小于等于80
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.lambda().gt(User::getFraction,60);
//        queryWrapper1.lambda().le(User::getFraction,80);
        List<User> userInfos1 = userService.list(queryWrapper1);
        result.put("userInfos1", userInfos1);
        //模糊查询 查询skill字段中包含“画”字的信息  并根据年龄排序降序
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper2.lambda().like(User::getSkill,"画");
//        queryWrapper2.lambda().orderByDesc(User::getAge);
        List<User> userInfos2 = userService.list(queryWrapper2);
        result.put("userInfos2", userInfos2);
        //查询评价不为null  并且分页
        IPage<User> page = new Page<>();
        page.setCurrent(1);
        page.setSize(2);
        QueryWrapper<User> queryWrapper3 = new QueryWrapper<>();
//        queryWrapper3.lambda().isNotNull(User::getEvaluate);
        page = userService.page(page, queryWrapper3);
        result.put("userPage", page);
        return result;
    }

}
