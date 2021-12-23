package edu.nuc.ihouse_01.controller;

import com.google.gson.Gson;
import edu.nuc.ihouse_01.service.HouseService;
import edu.nuc.ihouse_01.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private HouseService houseService;

    @PostMapping("/upload")
    @ResponseBody
    public Map uploadPicture(Integer houseId, @RequestParam("file") MultipartFile file) {
        Map map = pictureService.uploadPicture(file);
        String imagesStr = houseService.getById(houseId).getHouseImages();
        String imageUrl = (String) map.get("url");
        if (imagesStr == null || imagesStr.equals("") || imagesStr.equals("[]")) {
            houseService.setImage(houseId, imageUrl);
            imagesStr = "[\"" + imageUrl + "\"]";
        } else {
            Gson gson = new Gson();
            String[] images = gson.fromJson(imagesStr, String[].class);
            List<String> imageList = new ArrayList();
            for (String s : images) {
                imageList.add(s);
            }
            imageList.add(imageUrl);
            imagesStr = gson.toJson(imageList);
        }
        houseService.setImages(houseId, imagesStr);
        return map;
    }


    @PostMapping("/user/uploadHouseImage")
    public String uploadHouseImage(Integer houseId, @RequestParam("file") MultipartFile file) {
        Map map = pictureService.uploadPicture(file);
        String imagesStr = houseService.getById(houseId).getHouseImages();
        String imageUrl = (String) map.get("url");
        if (imagesStr == null || imagesStr.equals("") || imagesStr.equals("[]")) {
            houseService.setImage(houseId, imageUrl);
            imagesStr = "[\"" + imageUrl + "\"]";
        } else {
            Gson gson = new Gson();
            String[] images = gson.fromJson(imagesStr, String[].class);
            List<String> imageList = new ArrayList();
            for (String s : images) {
                imageList.add(s);
            }
            imageList.add(imageUrl);
            imagesStr = gson.toJson(imageList);
        }
        houseService.setImages(houseId, imagesStr);
        return "redirect:/house/housePage/"+houseId;
    }
}
