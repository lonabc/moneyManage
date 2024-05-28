package com.example.yinfuxi.StaticMy;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j

public class Emp {

    String name;

    String password;
    String email;
    String code;
    public Emp(String name)
    {
        this.name=name;
    }
}
