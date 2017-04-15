package com.detection.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.detection.model.owner.CrOwnerUnit;
import com.detection.model.owner.OwnerUnitRepository;
import com.detection.model.report.entities.CrCheckRecord;
import com.detection.model.report.entities.CrCheckReport;
import com.detection.services.OwnerUnitService;
import com.detection.util.FormCheck;

/**
 * @fileName OwnerUnitServiceImpl.java
 * @author csk
 * @createTime 2017年3月3日 下午5:09:22
 * @version 1.0
 * @function
 */
@Service
public class OwnerUnitServiceImpl implements OwnerUnitService {
    
    @Autowired
    private OwnerUnitRepository ownerUnitRepo;

    @Override
    public String getDataList() throws IOException {
        // TODO Auto-generated method stub
        List<CrOwnerUnit>dataList =  ownerUnitRepo.findAll();
        File outFile = new File("temp.xls");
        System.out.println(outFile.getAbsolutePath());
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("业主单位列表");
        sheet.setColumnWidth(0, 10 * 256);//设置第1列的宽度是2个字符宽度
        sheet.setColumnWidth(1, 50 * 256);//设置第2列的宽度是35个字符宽度
        sheet.setColumnWidth(2, 40 * 256);//设置第3列的宽度是10个字符宽度
        sheet.setColumnWidth(3, 20 * 256);//设置第4列的宽度是10个字符宽度
        int rownum = 0;
        HSSFRow row= sheet.createRow(rownum);
        HSSFCell cell = row.createCell(0);
        HSSFCellStyle st=wb.createCellStyle();
        st.setAlignment(HorizontalAlignment.CENTER);
        row.getCell(0).setCellValue("Id");
        cell.setCellStyle(st);
        cell = row.createCell(1);
        cell.setCellStyle(st);
        row.getCell(1).setCellValue("邮箱地址");
        cell = row.createCell(2);
        cell.setCellStyle(st);
        row.getCell(2).setCellValue("业主名称");
        cell = row.createCell(3);
        cell.setCellStyle(st);
        row.getCell(3).setCellValue("注册时间");
        
        
        HSSFCellStyle stc=wb.createCellStyle();
        stc.setAlignment(HorizontalAlignment.CENTER);
        
        Iterator<CrOwnerUnit> it = dataList.iterator();
        while(it.hasNext()){
            CrOwnerUnit owner = it.next();
            row = sheet.createRow(++rownum);
            cell = row.createCell(0);
            cell.setCellStyle(stc);
            row.getCell(0).setCellValue(owner.getId());
            cell = row.createCell(1);
            cell.setCellStyle(stc);
            row.getCell(1).setCellValue(owner.getEmail());
            cell = row.createCell(2);
            cell.setCellStyle(stc);
            row.getCell(2).setCellValue(owner.getOwnerName());
            cell = row.createCell(3);
            Date date = new Date(owner.getTimeStamp());
            HSSFCellStyle style=wb.createCellStyle();
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            style.setAlignment(HorizontalAlignment.CENTER);
            cell.setCellStyle(style);
            row.getCell(3).setCellValue(date);
        }
        
        wb.write(outFile);

        return outFile.getAbsolutePath();
    }

    @Override
    public JSONObject getJSONDataList() {
        List<CrOwnerUnit>dataList =  ownerUnitRepo.findAll();
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("data", dataList);
        // TODO Auto-generated method stub
        return result;
    }

}
