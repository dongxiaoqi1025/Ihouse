package edu.nuc.ihouse_01;


import edu.nuc.ihouse_01.model.entity.House;
import edu.nuc.ihouse_01.service.HouseService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;


@SpringBootTest
class Ihouse01ApplicationTests {

    @Autowired
    private HouseService houseService;
    @Test
    void contextLoads() {
        House house = houseService.getById(1);
        System.out.println(house);
    }

    // 测试 ftp 上传图片功能
    @Test
    public void testFtpClient() throws Exception {
        // 1. 创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        // 2. 创建 ftp 连接
        ftpClient.connect("182.92.196.229", 21);
        // 3. 登录 ftp 服务器
        ftpClient.login("ftpuser", "123456");
        // 4. 读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("E:\\JAVA\\IDEA\\ihouse_01\\src\\main\\resources\\static\\images\\1.jpg"));
        // 5. 设置上传的路径
        ftpClient.changeWorkingDirectory("/usr/local/nginx/html/images");
        // 6. 修改上传文件的格式为二进制
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        // 7. 服务器存储文件，第一个参数是存储在服务器的文件名，第二个参数是文件流
        ftpClient.storeFile("hello.jpg", inputStream);
        // 8. 关闭连接
        ftpClient.logout();

    }
}
