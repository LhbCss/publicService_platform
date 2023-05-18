package com.lin.Content;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class domain {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日");

    /**
     * 以下四个常量用于完成微信平台 code2Session 登录验证
     */
    public static final String appid = "";
    public static final String appSecret = "";
    public static final String code2Session = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String grant_type = "authorization_code";

    public static final String token = "4536efc8fd2c01858476183a098b1c48";

    public static final String localImageCachePath = "C:\\Users\\84623\\IdeaProjects\\SSM_PublicServicePlatform\\out\\artifacts\\SSM_PublicServicePlatform_war_exploded\\activityImage";

    public static final String ServerURL = "https://publicservice.mynatapp.cc";
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            return toFile;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            OutputStream ous = new FileOutputStream(toFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = ins.read(buffer)) != -1){
                ous.write(buffer, 0, length);
            }
            ous.close();
            ins.close();
        }
        return toFile;
    }
}
