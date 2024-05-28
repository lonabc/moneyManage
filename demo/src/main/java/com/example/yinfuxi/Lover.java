package com.example.yinfuxi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Lover implements Serializable {
   public  Integer age;
    public LocalDate birthday;
    public String name;
    public String school;
    private  String password;


    @Override
    public String toString() {
        return "Lover{" +
                "age=" + age +
                ", date=" + birthday +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
