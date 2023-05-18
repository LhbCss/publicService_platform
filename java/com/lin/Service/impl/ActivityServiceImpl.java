package com.lin.Service.impl;
import com.lin.POJO.Activity;
import com.lin.POJO.User_Info;
import com.lin.POJO.briefInfo;
import com.lin.Service.ActivityService;
import com.lin.DAO.ActivityMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.Content.domain;
import com.lin.POJO.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public String imgUpload(MultipartFile imgFile){
        String retURL = null;
        // 1. 生成文件 UUID
        String UUID = java.util.UUID.randomUUID().toString();
        // 2. 得到上传文件后缀
        String originalName = imgFile.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalName);
        // 3. 新生成的文件名称
        String fileName = UUID + ext;
        // 4. 在本地 ImageCache 中生成文件
        File targetFile = new File(domain.localImageCachePath, fileName);
        try {
            FileUtils.writeByteArrayToFile(targetFile, imgFile.getBytes()); // 将 MultipartFile 写为 File，同时用异常处理包裹
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 5. 拼接返回的 URL 地址
        retURL = domain.ServerURL + "/activityImage/" + fileName;
        return retURL;
        /*
        CloseableHttpResponse response = null;
        CloseableHttpClient closeableHttpClient = null;
        String imgURL = null;
        try{
            File file = null;
            FileBody img = null;
            // 1. 拼接 URIstring
            String URIstring = "https://www.hualigs.cn/api/upload";
            // 2. 转换为 URI 对象
            URI uri = new URI(URIstring);
            // 3. 创建 HttpClient 对象
            closeableHttpClient = HttpClients.createDefault();
            // 4. 创建 POST 请求
            HttpPost httpPost = new HttpPost(uri);
            // 5. 将 MultipartFile 转为 File 再转为 FileBody , FileBody 对象是 HttpPost 携带 File 的数据类型
            if (imgFile != null) {
                img = new FileBody(domain.multipartFileToFile(imgFile));
            }
            // 6. 创建 MultipartEntityBuilder 对象
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
            // 7. 构建所携带的数据体，String 用 StringBody , File 用 FileBody
            StringBody stringBody = new StringBody("chaoneng", ContentType.TEXT_PLAIN);
            StringBody tokenStringBody = new StringBody("4536efc8fd2c01858476183a098b1c48", ContentType.TEXT_PLAIN);
            // 8. 将数据体加入 multipartEntityBuilder 实例
            multipartEntityBuilder.addPart("apiType", stringBody);
            multipartEntityBuilder.addPart("image", img);
            multipartEntityBuilder.addPart("token", tokenStringBody);
            // 9. 通过 MultipartEntityBuilder 对象，创建 HttpEntity。
            HttpEntity reqEntity = multipartEntityBuilder.build();
            httpPost.setEntity(reqEntity);
            // 10. 执行 POST 请求
            response = closeableHttpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String resultString = EntityUtils.toString(response.getEntity(), "UTF-8");  // 将 response 数据转为 String
                System.out.println(resultString);
                ObjectMapper objectMapper = new ObjectMapper();
                // 11. 利用 jackson 处理 JSON 数据核心对象（先获取整个返回JSON对象的JsonNode，再获取其中data值的JsonNode,在获取其中chaoneng值的JsonNode
                JsonNode resultJsonNode = objectMapper.readValue(resultString, JsonNode.class);
                JsonNode dataJsonNode = resultJsonNode.get("data");
                JsonNode urlJsonNode = dataJsonNode.get("url");
                JsonNode imgURLjsonNode = urlJsonNode.get("chaoneng");
                imgURL = imgURLjsonNode.toString();
                System.out.println(imgURL);
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            // 12. 关闭 response 和 closeableHttpClient 对象
            if (response != null) {
                response.close();
            }
            closeableHttpClient.close();
        }
        return imgURL;*/
    }

    @Override
    public int ActivityEdit(Activity activity) {
        // 1. 判断 a_id 值是否为0，若为0，表示需要插入数据
        if(activity.getA_id() == 0){
            return activityMapper.insertActivity(activity);
        }
        // a_id 不为 0，表示需要更新对应数据
        return activityMapper.updateActivityByActivity(activity);
    }

    @Override
    public Activity[] getAllActivity() {
        Activity[] activities = activityMapper.selectAllActivity();
        // 处理日期时间格式
        for(int i = 0;i < activities.length; i++){
            activities[i].setFormatDate(domain.DATE_FORMAT.format(activities[i].getA_date()));
        }
        return activities;
    }

    @Override
    public int deleteActivity(int a_id) {
        return activityMapper.deleteActivityByA_id(a_id);
    }
}
