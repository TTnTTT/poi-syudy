package com.example.poidemo.controller;

import com.example.poidemo.common.util.ExcelUtil;
import com.example.poidemo.entity.Creativity;
import com.example.poidemo.service.CreativityService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author night bird
 * @version 1.0
 * @date 2020/12/18 22:43
 */
@Controller
public class CreativityController {
    @Autowired
    CreativityService creativityService;

    @GetMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Creativity> creativities = creativityService.selectAllDetailCreativity();
        String[] title = {"创意编号","创意名称","创意描述","创始人","创建时间","图片","图片标题","图片描述"};
        HSSFWorkbook wb = new ExcelUtil().exportExcel(title,creativities);
        ServletOutputStream out = response.getOutputStream();
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
        response.setContentType("application/msexcel");

        wb.write(out);
        out.close();
    }
}
