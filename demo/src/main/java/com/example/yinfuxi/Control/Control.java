package com.example.yinfuxi.Control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.yinfuxi.Lover;
import com.example.yinfuxi.Mapper.Mapper;
import com.example.yinfuxi.Service.ControlFile;
import com.example.yinfuxi.Service.Result;
import com.example.yinfuxi.Service.ServiceMy;
import com.example.yinfuxi.Service.UserService.UserRegister;
import com.example.yinfuxi.StaticMy.Emp;
import com.example.yinfuxi.StaticMy.JwtMy;
import com.example.yinfuxi.StaticMy.PageMy;
import com.example.yinfuxi.StaticMy.ToolsConst;
import com.example.yinfuxi.UserTools.Tools;
import com.example.yinfuxi.configer.BaseContextMy;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


@Slf4j
@RestController
@ServletComponentScan//Filter拦截器相关注释，开启Service组件支持
@CrossOrigin(origins = "*")
public class Control {
    @Autowired
    Mapper mapper;
    @Autowired
    @Lazy
    ServiceMy serviceMy;
    @Autowired
    ControlFile controlFiles;
    @Autowired
    UserRegister userRegister;
    @Value("${server.port}")
    String port;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JavaMailSender javaMailSender;

 // @Autowired
   // RedisTemplate redisTemplate;

  private static long timeValue=43200000;//有效时长12小时

   @RequestMapping("/list")
   @Cacheable(cacheNames = "userCache",key="#name")
    public Result selectMy(@Param("name")  String name) throws JsonProcessingException {
      Lover lover= serviceMy.serviceMy(name);
       System.out.println(lover+"?");
       boolean check=true;
       if(lover==null) check=false;
       Result result=new Result(check,"",lover);
      return result ;
   }


   @Transactional
   @RequestMapping("/test") //已经停用
    public Result selectAll()
   {
       log.info("数据查询中");
       boolean test=true;
       List<Lover> list =serviceMy.selectAll();
       if(list==null){test=false;}
       Result result=new Result(test,"",list);
       return result;
   }
   @Transactional
   @RequestMapping("/pageHelperA")
   public Result selectPage(String page)
   {
       Result<Integer> resultsum=new Result<>();
       PageMy pagemy=JSON.parseObject(page,PageMy.class);
       Integer pageStart=pagemy.getPageStart();
       Integer pageSize=pagemy.getPageSize();
       System.out.println(pageSize+"?"+pageStart);
       resultsum.setTestData(serviceMy.sumService());
       resultsum.setData(serviceMy.selectTest(pageStart,pageSize));
       return resultsum;
   }

   @RequestMapping("/delete") //异常处理测试类
   @CacheEvict(cacheNames = "userCache",key="#name")
    public void deleteTest(@RequestParam String name) throws UnsupportedEncodingException {
       log.info("数据删除成功");
       name = URLDecoder.decode(name,"UTF-8");
       log.info(name);
       serviceMy.deleteTest(name);
   }

   @RequestMapping("/selectSome")
   @CrossOrigin(value="http://localhost:8081",maxAge = 1800,allowedHeaders = "*")
    public Result selectSome(@RequestBody Lover lover)
   {
       System.out.println(lover);
       Integer age= lover.age;
       System.out.println(age);
       System.out.println(age);
       return null ;
   }
   @RequestMapping("/insert")
    public void insertMy(@RequestParam("form") String form)//接收前端对象参数方法2
   {
       Lover lover=JSON.parseObject(form,Lover.class);//将字符串转换成为后端对象
       System.out.println(lover.toString());
       serviceMy.insertForm(lover);
       log.info("true");
   }
   @PostMapping("/upload")//上传文件，到本地
    public Result upload(String username,Integer age,@RequestParam("image") MultipartFile file) throws IOException {
       String originalFilename= file.getOriginalFilename();//获取原始文件名
       int index=originalFilename.lastIndexOf(".");
       originalFilename=originalFilename.substring(index);//获取文件后缀名称
       String newFileName= UUID.randomUUID().toString()+originalFilename;
       log.info("success"+username+" "+age+" "+newFileName);
       file.transferTo(new File("E:/法环mod/"+newFileName));
       return null;
   }
   @PostMapping("/updata")
   public void updataMy(@RequestParam("form") String lover)
   {
       Lover lover1= JSONObject.parseObject(lover,Lover.class);
       serviceMy.update(lover1);
       System.out.println(lover1);
   }
   @PostMapping("/controlFile") //测试文件向前端返回url，上传文件到oss
    public String controlFile(@RequestParam("file") MultipartFile file) throws IOException {
        String url= controlFiles.upload1(file);
           if(file.isEmpty()) log.info("error");
           else log.info("success");
        return "";
   }

   @PostMapping("/loginV2")
   public String login2(@RequestParam("loginForm") String string)//登录功能能2.0
   {
       Emp e=JSONObject.parseObject(string,Emp.class);
       Emp emp=serviceMy.loginTest(e);
       if(emp==null)
       {
           log.info("未注册用户，请先完成注册");
       }else {
           int idR = mapper.selectId(e.getName(), e.getPassword());
           JwtMy jwtMy = new JwtMy();
           return jwtMy.testGenJwt(e, idR);
       }
       return null;
   }
   @PostMapping("/loginCode") //TODO 邮箱登录
   public String loginEmail(@RequestParam("loginEmail") String logimEmail)
   {
       System.out.println(logimEmail);
       String jwt="";
       Emp emp=JSONObject.parseObject(logimEmail,Emp.class);
       System.out.println(emp);
       if(emp.getCode().equals(BaseContextMy.get2()));
       {
            JwtMy jwtMy=new JwtMy();
            jwt= jwtMy.testEmailJwt(emp);
       }
       return jwt;
   }

   @PostMapping("/register")
    public void register(@RequestBody Lover emp)
   {
       System.out.println(emp);
       userRegister.insertRegister(emp);
   }
   @GetMapping ("/testCode/{emails}") //TODO 生成前端六位邮件编码，Get类型
   @Operation(summary ="发送邮件验证码" )
    public void testCode(@PathVariable(value = "emails") String email)
   {
       System.out.println(email);
       SimpleMailMessage s =Tools.generateRandomCode(6,redisTemplate,email);
       javaMailSender.send(s);
   }
}
