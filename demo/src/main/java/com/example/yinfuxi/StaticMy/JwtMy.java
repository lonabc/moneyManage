package com.example.yinfuxi.StaticMy;

import com.example.yinfuxi.Expection.Myerror;
import com.example.yinfuxi.Mapper.Mapper;
import com.example.yinfuxi.configer.BaseContextMy;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtMy {

    private static String signKey="yinchuanguo";
    private static Key signKey1;

    private  Key generateSigningKey() {
        byte[] apiKeySecretBytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(apiKeySecretBytes);
        signKey1= new SecretKeySpec(apiKeySecretBytes, "None");
        return signKey1;
    }

    private static long timeValue=43200000;//有效时长12小时
    public  String  testGenJwt(Emp e,Integer id) //jwt令牌的生成
    {

        generateSigningKey();//生成一个随机的签名秘钥
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",id);
        claims.put("name",e.getName());
        claims.put("password",e.getPassword());
        String jwt= Jwts.builder().signWith(SignatureAlgorithm.HS256,signKey)//根据用户自定义字符串和签名算法生成jwt
                .setClaims(claims)//自定义内容（载合）
                .setExpiration(new Date(System.currentTimeMillis()+timeValue))//设置有效时间,当前设置时间1h
                .compact();
        System.out.println(jwt+"这是登录功能1.1");
        return jwt;
    }
    public String testEmailJwt(Emp emp) //jwt令牌生成
    {

        Map<String,Object> claims=new HashMap<>();
        claims.put("email",emp.name);
        claims.put("id",0);
        String jwt= String.valueOf(Jwts.builder().signWith(SignatureAlgorithm.HS256, signKey)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+timeValue))
                .compact()
        );
        System.out.println("邮箱登录功能测试中"+jwt);
        return  jwt;
    }


    public static Claims findJwt(String jwt)//令牌的校验，也是解析
    {
        Claims claims=null;
        try {
            claims = Jwts.parser().setSigningKey(signKey)
                    .parseClaimsJws(jwt)
                    .getBody();
            BaseContextMy.set((Integer) claims.get("id"));

           // System.out.println("从jwt令牌中获取信息"+claims.get("id"));
        }catch(RuntimeException r){
            System.out.println("令牌解析异常,请重新登录");
            throw new Myerror("warning");//自定义异常
        }finally {
            System.out.println("令牌解析完毕");
        }


        return claims;
    }
}
