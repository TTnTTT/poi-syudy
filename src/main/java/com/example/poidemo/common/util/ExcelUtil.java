package com.example.poidemo.common.util;

import com.example.poidemo.entity.Creativity;
import com.example.poidemo.entity.CreativityPic;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.imageio.ImageIO;
import javax.servlet.ServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author night bird
 * @version 1.0
 * @date 2020/12/18 16:44
 */
public class ExcelUtil {

    public HSSFWorkbook exportExcel(String[] title, List<Creativity> data) throws IOException {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("Sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short)500);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //声明列对象
        HSSFCell cell = null;
        //创建标题
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, 6000);//设置单个单元格宽度
            cell=row.createCell(i);
            cell.setCellValue(title[i]);

            //设置字体
            HSSFFont font = wb.createFont();
            font.setFontName("黑体");
            font.setFontHeightInPoints((short) 15);
            style.setFont(font);

            cell.setCellStyle(style);
        }

        //计算行数到哪了
        Integer count=1;
        //创建内容
        for (int i = 0; i < data.size(); i++) {
            Creativity creativity = data.get(i);
            List<CreativityPic> creativityPics = creativity.getCreativityPics();

            //用于后面的合并行数
            Integer startCount = count;

            for (int j = 0; j < creativityPics.size(); j++) {
                CreativityPic pic = creativityPics.get(j);
                //创建一行  count自增1
                row=sheet.createRow(count);
                synchronized (count){
                    count++;
                }
                row.setHeight((short) 2000);
                //创意编号
                cell=row.createCell(0);cell.setCellValue(creativity.getId());cell.setCellStyle(style);
                //创意名称
                cell=row.createCell(1);cell.setCellValue(creativity.getName());cell.setCellStyle(style);
                //创意描述
                cell=row.createCell(2);cell.setCellValue(creativity.getDescription());cell.setCellStyle(style);
                //创始人
                cell=row.createCell(3);cell.setCellValue(creativity.getUserId());cell.setCellStyle(style);
                //创建时间
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(creativity.getCrtTime());
                cell=row.createCell(4);cell.setCellValue(time);cell.setCellStyle(style);
                //图片标题
                cell=row.createCell(6);cell.setCellValue(pic.getTitle());cell.setCellStyle(style);
                //图片描述
                cell=row.createCell(7);cell.setCellValue(pic.getDescription());cell.setCellStyle(style);
                //图片
                String picUrl = pic.getUrl();
                String suff = picUrl.substring(picUrl.lastIndexOf(".")+1).toLowerCase();

                // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
                ByteArrayOutputStream byteArrayOut = imgToByteOutStream(picUrl);

                //确定图片坐标  参数(left，top，right，bottom,起始列,起始行,终止列,终止行) 前四个参数是控制图片在单元格的位置
                HSSFClientAnchor anchor = new HSSFClientAnchor(200, 30, 600, 250,
                        (short) 5, count-1, (short) 5, count-1);
                HSSFPatriarch patriarch = sheet.createDrawingPatriarch();//用于创建图片
                //创建图片
                patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(),
                        suff.equals(Sys.PNG_PIC) ? HSSFWorkbook.PICTURE_TYPE_PNG : HSSFWorkbook.PICTURE_TYPE_JPEG));

            }
            //遍历完一个详情后需合并单元格  参数（起始行，终止行，起始列，终止列）
            CellRangeAddress cellRangeAddress = new CellRangeAddress(startCount, count-1, 0, 0);
            sheet.addMergedRegion(cellRangeAddress);
            cellRangeAddress = new CellRangeAddress(startCount, count-1, 1, 1);
            sheet.addMergedRegion(cellRangeAddress);
            cellRangeAddress = new CellRangeAddress(startCount, count-1, 2, 2);
            sheet.addMergedRegion(cellRangeAddress);
            cellRangeAddress = new CellRangeAddress(startCount, count-1, 3, 3);
            sheet.addMergedRegion(cellRangeAddress);
            cellRangeAddress = new CellRangeAddress(startCount, count-1, 4, 4);
            sheet.addMergedRegion(cellRangeAddress);
        }
        return wb;
    }

    private ByteArrayOutputStream imgToByteOutStream(String picUrl) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        String suff = picUrl.substring(picUrl.lastIndexOf(".")+1).toLowerCase();
        File picFile = new File(Sys.UPLOAD_DIR + picUrl);
        BufferedImage buff = null;
        if (!picFile.exists()){
            throw new IOException("文件不存在");
        }
        try {
            buff = ImageIO.read(picFile);
            if (suff.equals(Sys.PNG_PIC)) {
                ImageIO.write(buff, Sys.PNG_PIC, byteArrayOut);
            }else if (suff.equals(Sys.JPG_PIC)){
                ImageIO.write(buff, Sys.JPG_PIC, byteArrayOut);
            }else {
                throw new IOException("不是图片");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOut;

    }
}
