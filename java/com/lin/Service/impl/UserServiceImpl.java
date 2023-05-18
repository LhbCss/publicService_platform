package com.lin.Service.impl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.DAO.ActivityMapper;
import com.lin.DAO.UserActivityMapper;
import com.lin.POJO.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import com.lin.Content.domain;
import com.lin.DAO.UserMapper;
import com.lin.Service.UserService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserActivityMapper userActivityMapper;

    @Autowired
    ActivityMapper activityMapper;

    @Override
    public String Login(String code) throws URISyntaxException, IOException {
        String openid = null;
        // 1. 拼接 URIstring
        String URIstring = domain.code2Session + "?appid=" + domain.appid + "&secret=" + domain.appSecret + "&js_code=" + code + "&grant_type=" + domain.grant_type;
        URI uri = new URI(URIstring);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
        User u = null;
        if (response.getStatusLine().getStatusCode() == 200) {
            String resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            u = objectMapper.readValue(resultString, User.class);
            openid = u.getOpenid();
        }
        User ret = userMapper.SelectByOpenid(openid);
        if(ret == null){
            userMapper.InsertByOpenid(openid);
        }
        if (response != null) {
            response.close();
        }
        closeableHttpClient.close();
        return openid;
    }

    @Override
    public User_Info getUserInfo(String openid) {
        return userMapper.SelectUserInfoByOpenid(openid);
    }

    @Override
    public int UpdateUserInfo(User_Info userInfo) {

        int i;

        // 1. 判断数据库中是否存在该用户的个人信息
        User_Info u = userMapper.SelectUserInfoByOpenid(userInfo.getOpenid());
        if(u == null){
            // 数据库中不存在该用户个人信息，说明该用户是第一次填写个人信息，向 tb_user_info 表插入新值
            i = userMapper.InsertUserInfo(userInfo);
        }else{
            // 数据库中存在该用户个人信息，对数据表项进行 UPDATE
            i = userMapper.updateUserInfo(userInfo);
        }
        return i;
    }

    @Override
    public int AdminLogin(admin a) {
        // 调用 DAO 层完成查询
        admin admin = userMapper.selectAdmin(a);
        if(admin != null){
            // 存在账号信息，登录成功
            return 1;
        }
        else{
            // 登录失败
            return -1;
        }
    }
    @Override
    public int UserSignUp(String openid, int a_id) {
        // 1. 首先判断是否有重复报名
        UserActivity userActivity = userActivityMapper.selectByOpenidAndA_id(openid, a_id);
        if(userActivity == null){// 表明用户未报名过，为其报名
            int ret = userActivityMapper.insertinto(openid, a_id);
            if(ret == 1){
                return 1;// 报名成功
            }else{
                return 0;
            }
        }
        return 0;// 用户重复报名
    }

    @Override
    public Activity[] getPersonalSignUpInformation(String openid) {
        // 1. 第一步，根据个人 openid 查询关系表中该用户所报名的所有活动的 a_id
        int[] a_ids = userActivityMapper.selectAllByOpenid(openid);
        Activity[] activities = new Activity[a_ids.length];
        // 2. 第二步，根据每一个 a_id 查询 Activity 详细数据
        for(int i = 0;i < a_ids.length;i++){
            activities[i] = activityMapper.selectActivityByA_id(a_ids[i]);
            activities[i].setFormatDate(domain.DATE_FORMAT.format(activities[i].getA_date()));
        }
        return activities;
    }

    @Override
    public int userSignOutActivity(String openid, int a_id) {
        return userActivityMapper.delete(openid, a_id);
    }

    @Override
    public User_Info[] getAllSignUpInfo(int a_id) {
        // 1. 先查到报名该 a_id 的所有用户 openid
        String[] openids = userActivityMapper.selectAllOpenByA_id(a_id);
        int length = openids.length;
        User_Info[] user_infos = new User_Info[length];
        // 2. 遍历每个 openid::openids[i]，获得对应的 User_Info
        for(int i = 0;i < length;i++){
            user_infos[i] = userMapper.SelectUserInfoByOpenid(openids[i]);
        }
        return user_infos;
    }
}
