package com.lin.Service;

import com.lin.POJO.Activity;
import com.lin.POJO.User;
import com.lin.POJO.User_Info;
import com.lin.POJO.admin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public interface UserService {

    String Login(String code) throws URISyntaxException, IOException;

    User_Info getUserInfo(String openid);

    int UpdateUserInfo(User_Info userInfo);

    int AdminLogin(admin a);

    int UserSignUp(String openid, int a_id);

    Activity[] getPersonalSignUpInformation(String openid);

    int userSignOutActivity(String openid, int a_id);

    User_Info[] getAllSignUpInfo(int a_id);
}
