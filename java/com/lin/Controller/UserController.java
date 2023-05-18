package com.lin.Controller;
import com.lin.POJO.Activity;
import com.lin.POJO.User_Info;
import com.lin.POJO.admin;
import com.lin.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import static com.lin.Content.domain.DATE_FORMAT;

@Controller

public class UserController {

    @Autowired
    UserService userService;

    /**
     * 由 wx.request 调用，接收用户 code，调用 UserService.login() 获取 session_key 和 openid;
     * @param code
     * @throws IOException
     */
    @RequestMapping("/userlogin")
    @ResponseBody
    public void UserLogin(@RequestParam("code") String code, HttpServletResponse response) throws IOException, URISyntaxException {


        // 1. 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /userlogin 被访问了！所拿到的 code :" + code);

        // 2. 将 code 传给 Service 完成业务
        String openid = userService.Login(code);
        System.out.println("用户的 openid: " + openid);
        response.getWriter().write(openid);
    }

    /**
     * 由 wx.request 调用，接收用户唯一标识 openid ，查询用户个人信息
     * @param openid
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User_Info getUser_Info(@RequestParam("openid")String openid){
        // 1. 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /getUserInfo 被访问了！所拿到的 openid :" + openid);

        User_Info u = userService.getUserInfo(openid);
        System.out.println(u);
        return u;
    }

    /**
     * 由 wx.request 调用，接收 JSON 自动装载为实体类 User_Info，并完成查询更新用户个人信息
     * @param userInfo
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public void updateUserInfo(@RequestBody User_Info userInfo){

        // 1. 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /updateUserInfo 被访问了！所拿到的 User_Info :" + userInfo);

        int i = userService.UpdateUserInfo(userInfo);
        if(i == 1){
            System.out.println("修改用户信息成功");
        }else {
            System.out.println("修改用户信息失败");
        }

    }

    /**
     * 后台管理系统登录界面的路由
     * @return
     */
    @RequestMapping("/login")
    public String admin(){
        return "login";
    }

    @RequestMapping("/adminLogin")
    @ResponseBody
    public void adminLogin(@RequestBody admin a, HttpServletResponse response, HttpServletRequest request) throws IOException {

        // 1. 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /adminLogin 被访问了！所拿到的 admin :" + a);

        // 2. 调用对应 Service 完成查询
        int i = userService.AdminLogin(a);

        // 3. 将登录结果通过 response 返回给视图层
        if(i == 1){
            // 登录成功，存 Session
            System.out.println("登录成功");
            request.getSession().setAttribute("admin", a);
            response.getWriter().write("登录成功");
        }else{
            // 登录失败
            System.out.println("登录失败");
            response.getWriter().write("登录失败");
        }
    }

    /**
     * 访问后台管理系统主界面，需要提前登录，访问时通过判断 Session 域中是否有 admin 值来判断是否登录过，未登录则调回至登录界面
     * @param request
     * @return
     */
    @RequestMapping("/toMain")
    private String toMain(HttpServletRequest request){
        // 判断 Session 域中是否有 admin 对象
        Object o = request.getSession().getAttribute("admin");
        if(o != null){
            // 有 Session
            return "main";
        }
        return "login";
    }
    @RequestMapping("/getAdminName")
    public void getAdminName(HttpServletRequest request, HttpServletResponse response) throws IOException {


        // 1. 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /getAdminName 被访问了！");

        // 2. 获取 request 所携带的 session
        HttpSession session = request.getSession();
        admin a = (admin)session.getAttribute("admin");
        System.out.println(a);

        // 3. 判断携带的 session 中是否有 admin 值
        if(a != null){
            response.getWriter().write(a.getName());
        }
    }
    @RequestMapping("/AdminLogout")
    public String AdminLogout(HttpServletRequest request){
        // 1. 删除域内所携带的 Session
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return "login";
    }

    @RequestMapping("/signUp")
    public void userSignUp(@RequestParam("openid")String openid,@RequestParam("a_id")int a_id, HttpServletResponse response) throws IOException {
        // 1. 调用 UserService 完成报名操作
        int ret = userService.UserSignUp(openid, a_id);
        // 2. 根据返回值判断报名结果
        if(ret == 1){
            // 报名成功，返回成功信息
            response.getWriter().write("sign up success");
        }else{
            response.getWriter().write("sign up failed");
        }
    }
    @RequestMapping("/getSignUpInformation")
    @ResponseBody
    public Activity[] getSignUpInformation(String openid){

        // 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /getSignUpInformation 被访问了！所拿到的 openid: " + openid);

        Activity[] activitys = userService.getPersonalSignUpInformation(openid);

        return activitys;
    }


    @RequestMapping("/userSignOutActivity")
    public void userSignOut(@RequestParam("openid")String openid, @RequestParam("a_id")int a_id, HttpServletResponse response) throws IOException {

        // 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /userSignOutActivity 被访问了！所拿到的 openid: " + openid + " , a_id: " + a_id);

        // 1. 调用 UserService 完成取消活动业务
        int ret = userService.userSignOutActivity(openid, a_id);

        // 2. 根据返回结果返回相应 response
        if(ret == 1){
            response.getWriter().write("sign out success");
        }else{
            response.getWriter().write("sign out failed");
        }
    }

    @RequestMapping("/getSignUpInfo")
    @ResponseBody
    public User_Info[] getActivitySignUpInfo(@RequestParam("a_id") int a_id){
        // 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /getSignUpInfo 被访问了！所拿到的 a_id: " + a_id);

        return userService.getAllSignUpInfo(a_id);
    }}
