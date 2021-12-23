package edu.nuc.ihouse_01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.nuc.ihouse_01.model.dto.HouseModel;
import edu.nuc.ihouse_01.model.dto.HouseQueryDto;
import edu.nuc.ihouse_01.model.entity.House;
import edu.nuc.ihouse_01.model.entity.User;
import edu.nuc.ihouse_01.service.HouseService;
import edu.nuc.ihouse_01.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
@Api(tags = "房源管理接口")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;
    //搜索参数匹配项
    private Map<String, Object> searchMap = new HashMap<>();

    {
        searchMap.put("a0", null);
        searchMap.put("a1", "杏花岭区");
        searchMap.put("a2", "迎泽区");
        searchMap.put("a3", "万柏林区");
        searchMap.put("a4", "小店区");
        searchMap.put("a5", "尖草坪区");
        searchMap.put("a6", "晋源区");
        searchMap.put("a7", "清徐县");
        searchMap.put("a8", "阳曲县");
        searchMap.put("a9", "娄烦县");
        searchMap.put("a10", "古交县");

        searchMap.put("b0", null);
        searchMap.put("b1", 1000);
        searchMap.put("b2", 1500);
        searchMap.put("b3", 2000);
        searchMap.put("b4", 2500);
        searchMap.put("b5", 3000);
        searchMap.put("b6", 3500);
        searchMap.put("b7", 5000);

        searchMap.put("c0", null);
        searchMap.put("c1", "一居室");
        searchMap.put("c2", "二居室");
        searchMap.put("c3", "三居室");
        searchMap.put("c4", "四居室");

        searchMap.put("d0", null);
        searchMap.put("d1", "东");
        searchMap.put("d2", "南");
        searchMap.put("d3", "西");
        searchMap.put("d4", "北");

        searchMap.put("e0", null);
        searchMap.put("e1", "精装修");
        searchMap.put("e2", "集中供暖");
        searchMap.put("e3", "押一付一");
        searchMap.put("e4", "新上");
        searchMap.put("e5", "有电梯");
        searchMap.put("e6", "近地铁");
        searchMap.put("e7", "随时入住");

        searchMap.put("f0", null);
        searchMap.put("f1", "月租");
        searchMap.put("f2", "年租");
        searchMap.put("f3", "一个月起租");
        searchMap.put("f4", "1-3个月");
        searchMap.put("f5", "4-6个月");

        searchMap.put("g0", null);
        searchMap.put("g1", 30);
        searchMap.put("g2", 20);
        searchMap.put("g3", 10);

        searchMap.put("m0", null);
        searchMap.put("m1", 1);
        searchMap.put("m2", 2);
    }

    /**
     * 根据id获取用户信息
     *
     * @param id 房源的id
     * @return 返回一个房源对象
     */
    @GetMapping("/getHouse/{id}")
    @ResponseBody
    public House getHouse(@PathVariable("id") Integer id) {
        House houseInfo = houseService.getById(id);
        return houseInfo;
    }


    /**
     * 获取全部房源
     *
     * @return 返回一个list
     */
    @GetMapping("/getList")
    @ResponseBody
    public List<House> getList() {
        List<House> houseInfoList = houseService.list();
        return houseInfoList;
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> list() {
        String[] strings = {"租住保障", "公寓", "精装", "集中供暖", "随时看房"};
        Map<String, Object> map = new HashMap<>();
        map.put("str", "[\"租住保障\",\"公寓\",\"精装\",\"集中供暖\",\"随时看房\"]");
        map.put("str2", strings);


        return map;
    }

    /**
     * 全部房源 分页查询
     *
     * @param currentPage 分页显示当前页
     * @param pageSize    分页显示每页条数
     * @return
     */
    @GetMapping("/getListPage")
    @ResponseBody
    public IPage<House> getHouseListPage(Integer currentPage, Integer pageSize) {
        IPage<House> page = new Page<>();
        page.setCurrent(currentPage);//当前页
        page.setSize(pageSize);//每页条数
        page = houseService.page(page);
        return page;
    }

    /**
     * 根据指定的字段查询房源信息
     *
     * @return 返回房源对象列表
     */
    @GetMapping("/getListMap")
    @ResponseBody
    public List<House> getListMap(@RequestBody @Valid HouseQueryDto houseQueryDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("city", houseQueryDto.getCity());
        map.put("price", houseQueryDto.getPrice());
        List<House> houseList = houseService.listByMap(map);
        return houseList;
    }

    /**
     * 新增房源信息
     *
     * @return void
     */
    @PostMapping("/saveHouse")
    public String saveInfo( House house,Model model) {
        System.out.println(house);
        houseService.save(house);
        model.addAttribute("houseId",house.getHouseId());
        return "upload";
    }

    /**
     * 更改房源信息
     *
     * @return void
     */
    @PutMapping("/updateInfo")
    @ResponseBody
    public void updateInfo() {
        House house = new House();
        houseService.updateById(house);
    }

    /**
     * saveOrUpdate
     *
     * @return void
     */
    @PostMapping("/saveOrUpdateInfo")
    @ResponseBody
    public void saveOrUpdateInfo() {
        House house = new House();
        houseService.saveOrUpdate(house);
    }

    /**
     * 根据id删除房源信息
     *
     * @param id 房源id
     * @return void
     */
    @DeleteMapping("/deleteInfo/{id}")
    @ResponseBody
    public void deleteInfo(@PathVariable("id") Long id) {
        houseService.removeById(id);
    }

    @PostMapping("/search")
    @ApiOperation(value = "获取房源管理信息", notes = "房源信息列表接口")
    public String searchHouse(HttpServletRequest request, HouseQueryDto houseQuery) {
        HttpSession session = request.getSession();
        //分页
        IPage<House> page = new Page<>();
        page.setCurrent(houseQuery.getCurrentPage());//当前页
        page.setSize(houseQuery.getPageSize());//每页条数
        //查询参数
        houseQuery.setCity((String) session.getAttribute("city"));
        System.out.println(houseQuery);

        //执行
        page = houseService.getPageByHouseQuery(page, houseQuery);
        List<House> houses = page.getRecords();
        List<HouseModel> houseList = getHouseModels(houses);

        session.setAttribute("houseList", houseList);
        session.setAttribute("houseQuery", houseQuery);
        session.setAttribute("city", houseQuery.getCity());
        session.setAttribute("total", page.getTotal());
        session.setAttribute("current", page.getCurrent());
        return "search";
    }

    @GetMapping("/search/{str}")
    @ApiOperation(value = "获取房源管理信息", notes = "房源信息列表接口")
    public String searchHouseByStr(HttpServletRequest request, @PathVariable("str") String str) {
        //维持状态
        HttpSession session = request.getSession();
        HouseQueryDto houseQuery = (HouseQueryDto) session.getAttribute("houseQuery");
        if (houseQuery == null) {
            houseQuery = new HouseQueryDto();
        }
        houseQuery.setCity((String) session.getAttribute("city"));
        houseQuery.setCurrentPage(1);
        houseQuery.setPageSize(20);
        //解析参数
        String[] strs = str.split("-");
        Map<String, String> strMap = new HashMap<>();
        for (String s : strs) {
            strMap.put(s,s);
            switch (s.substring(0, 1)) {
                case "a":
                    houseQuery.setDistrict((String) searchMap.get(s));
                    break;
                case "b":
                    houseQuery.setPrice((Integer) searchMap.get(s));
                    break;
                case "c":
                    houseQuery.setType((String) searchMap.get(s));
                    break;
                case "d":
                    houseQuery.setOrientation((String) searchMap.get(s));
                    break;
                case "e":
                    houseQuery.setFeature((String) searchMap.get(s));
                    break;
                case "f":
                    houseQuery.setTime((String) searchMap.get(s));
                    break;
                case "g":
                    houseQuery.setFloor((Integer) searchMap.get(s));
                    break;
                case "m":
                    houseQuery.setMode((Integer) searchMap.get(s));
                    break;
                default:
                    break;
            }


        }

        //分页
        IPage<House> page = new Page<>();
        page.setCurrent(houseQuery.getCurrentPage());//当前页
        page.setSize(houseQuery.getPageSize());//每页条数
        //查询参数

        //执行
        page = houseService.getPageByHouseQuery(page, houseQuery);
        List<House> houses = page.getRecords();
        List<HouseModel> houseList = getHouseModels(houses);
        session.setAttribute("houseList", houseList);
        session.setAttribute("houseQuery", houseQuery);
        session.setAttribute("city", session.getAttribute("city"));
        session.setAttribute("total", page.getTotal());
        session.setAttribute("current", page.getCurrent());
        session.setAttribute("searchStr", strMap);
        return "search";
    }

    @GetMapping("/contract")
    public String contract(Integer userId,Integer houseId){

        return "contract";
    }

    @GetMapping("/housePage/{id}")
    public String housePage(Model model, @PathVariable Integer id) {
        HouseModel house = new HouseModel(houseService.getById(id));
        User host = userService.getById(house.getUserId());
        model.addAttribute("house", house);
        model.addAttribute("host", host);
        return "house";
    }

    private List<HouseModel> getHouseModels(List<House> houses) {
        List<HouseModel> models = new ArrayList<>();
        for (House house : houses) {
            models.add(new HouseModel(house));
        }
        return models;
    }
}
