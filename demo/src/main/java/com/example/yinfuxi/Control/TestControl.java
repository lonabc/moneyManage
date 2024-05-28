package com.example.yinfuxi.Control;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@RestController
@ServletComponentScan//Filter拦截器相关注释，开启Service组件支持
@CrossOrigin(origins = "*")//TODO 与本项目无关
public class TestControl {

    @PostMapping("/timeMy")
    public void testTime(@RequestParam("value1") String time) throws ParseException {
        System.out.println(time);
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate= LocalDate.parse(time,dateTimeFormatter);
        System.out.println(localDate);
    }
}
