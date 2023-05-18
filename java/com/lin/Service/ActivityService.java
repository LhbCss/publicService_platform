package com.lin.Service;

import com.lin.POJO.Activity;
import com.lin.POJO.User_Info;
import com.lin.POJO.briefInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public interface ActivityService {

    String imgUpload(MultipartFile File) throws Exception;

    int ActivityEdit(Activity activity);

    Activity[] getAllActivity();

    int deleteActivity(int a_id);


}
