package com.icheck.service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/2/11
 * Time: 1:47
 * To change this template use File | Settings | File Templates.
 */
@Service
public class QiniuService {

    //账号的ACCESS_KEY和SECRET_KEY
    private static String ACCESS_KEY = "AhxH-20-LQEoM8GMXz6dRpahGlVePJtOh80z4xHu";
    private static String SECRET_KEY = "hGmm9P9BMvsDzpFnkwEUKu-juLIccsVDKg7PaJbT";
    private static String zcpic = "oem68v3ya.bkt.clouddn.com";
    //密钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    String bucketname = "default";
    Zone z = Zone.autoZone();
    Configuration c = new Configuration(z);

    //创建上传对象
    UploadManager uploadManager = new UploadManager(c);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public void upload(File file,String key) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(file, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }


    }


