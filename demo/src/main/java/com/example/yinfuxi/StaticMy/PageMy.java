package com.example.yinfuxi.StaticMy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageMy implements Serializable {
    private Integer pageStart=1;
    private Integer pageSize=7;
}
