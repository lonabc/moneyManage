package com.example.yinfuxi.websocket;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//TODO 与本项目无关
//使用Apache POI操作excel文件
public class POITest {

    //通过POI创建Excel文件并且写入文件内容
    public static void write()
    {
        //在内存中创建一个Excel文件
        XSSFWorkbook excel=new XSSFWorkbook();
        //在Excel文件中创建一个Sheet页
        XSSFSheet sheet=excel.createSheet("info");
        //在Sheet中创建行对象，rownum编号从0开始
        XSSFRow row=sheet.createRow(1);//这里创建的是第二行
        //创建单元格，并写入内容
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("年龄");

    }
    public static void main(String[] args)
    {
        write();
    }
}
