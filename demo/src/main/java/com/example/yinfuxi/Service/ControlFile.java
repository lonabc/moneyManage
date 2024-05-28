package com.example.yinfuxi.Service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ControlFile {
    private String endpoint= "https://oss-cn-beijing.aliyuncs.com";
    private String accessKeyId="LTAI5tRSLqnyjqAprNDSvhb5";
    private String accessKeySecret="6YzjKltxi8IxdNjwVsoawZjOYCsmjC";
    private String bucketName="121138hmleadnews";
    public String upload1(@RequestParam("file") MultipartFile file) throws IOException {
        //设置文件名，保证其不重复
        if(file.isEmpty()){
            log.info("error");
        }else {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String fileName = "test/"+UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            log.info(fileName);
            OSS ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
            ossClient.putObject(bucketName,fileName,inputStream);

            //文件访问路径
            String url=endpoint.split("//")[0]+"//"+bucketName+"."+endpoint.split("//")[1]+"/"+fileName;
            ossClient.shutdown();
            System.out.println(url);
        }
        return "";
    }
}
