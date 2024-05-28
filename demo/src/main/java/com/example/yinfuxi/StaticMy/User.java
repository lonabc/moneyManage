package com.example.yinfuxi.StaticMy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class User{
    private LocalDate dayTime;
    private int cost;
    private Object classify;
    private int id;
}
