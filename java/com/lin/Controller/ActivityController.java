package com.lin.Controller;
import com.lin.POJO.Activity;
import com.lin.POJO.User;
import com.lin.POJO.briefInfo;
import com.lin.Service.ActivityService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import static com.lin.Content.domain.DATE_FORMAT;

@Controller
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @RequestMapping("/imgUpload")
    public void imgUpload(@RequestParam("file") MultipartFile imgFile, HttpServletResponse response) throws Exception {
        // 1. 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /imgUpload 被访问了！所拿到的 File :" + imgFile.getOriginalFilename());
        // 2. 调用 ActivityService 完成上传功能
        String retURL = activityService.imgUpload(imgFile);
        // 3. 判断上传结果
        if(retURL == null){
            // 上传失败
            response.getWriter().write("图片上传失败");
        }else{
            // 上传成功，返回图片 URL 地址
            response.getWriter().write(retURL);
        }
    }

    /**
     * 修改数据库中活动信息：若数据库中有该 a_id 的值，则修改对应活动字段信息；若数据库中无该 a_id 值，则插入数据
     * @param activity
     * @param response
     * @throws IOException
     */
    @RequestMapping("/EditActivity")
    public void ActivityUpload(@RequestBody Activity activity, HttpServletResponse response) throws IOException {
        // 1. 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /EditActivity 被访问了！所拿到的 activity :" + activity);
        // 2. 调用 ActivityService 完成提交数据库请求
        int ret = activityService.ActivityEdit(activity);
        System.out.println(ret);
        response.setContentType("text/html;charset=UTF-8"); // 解决 response 中文乱码问题
        if(ret == 1){
            response.getWriter().write("操作成功");

        }else{
            response.getWriter().write("操作失败");
        }
    }
    @RequestMapping("/getAllBrief")
    @ResponseBody
    public Activity[] getAllBriefInfo(){
        Activity[] activities = activityService.getAllActivity();
        return activities;
    }

    @RequestMapping("/deleteActivity")
    void delete(@RequestParam("a_id")int a_id, HttpServletResponse response) throws IOException {
        // 1. 打印日志（开发阶段保留）
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date) + ": /deleteActivity 被访问了！所拿到的 a_id: " + a_id);

        int i = activityService.deleteActivity(a_id);
        if(i == 1){
            // 删除成功
            response.getWriter().write("delete successed");
        }else{
            // 删除失败
            response.getWriter().write("delete failed");
        }
    }


}
