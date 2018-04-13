package com.CommonUtils.GetTestData;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class WriteToExcel {
    /** Excel 文件要存放的位置，假定在D盘下 */
    public static String outputFile = "D:\\test1.xls";

    public static void main(String argv[]) {
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值
            // 如要新建一名为"效益指标"的工作表，其语句为：
            // HSSFSheet sheet = workbook.createSheet("效益指标");
            HSSFSheet sheet = workbook.createSheet("通过POI添加的工作表");
            // 在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow((short) 0);
            // 在索引0的位置创建单元格（左上端）
            HSSFCell cell = row.createCell((short) 0);
            // 定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 设置该单元格字体格式
            HSSFFont font = workbook.createFont();// 新建字体格式
            font.setColor(HSSFFont.COLOR_RED);// 设置字体颜色为红色，注：HSSFFont中只有红色，其他颜色请用HSSFColor，下文中有举例
           // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置字体格式为粗体
            //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setBold(true);
           
            font.setFontName("楷体");// 设置字体格式为楷体
            HSSFCellStyle cellStyle = workbook.createCellStyle();// 新建单元格样式
            cellStyle.setFont(font);// 将字体样式应用于单元格样式
            cell.setCellStyle(cellStyle);// 将单元格样式应用于单元格
            // 在单元格中输入一些内容
            cell.setCellValue("增加值1");

            // 设置列宽
            sheet.setColumnWidth(1, 3766); // 第一个参数代表列id(从0开始),第2个参数代表宽度值 参考
                                            // ："2012-08-10"的宽度为2500
            // 在索引1的位置创建行（最顶端的第二行）
            HSSFRow row2 = sheet.createRow((short) 1);
            // 在索引1的位置创建单元格（左上端第二行第二格）
            HSSFCell cell2 = row2.createCell((short) 1);
            // 定义单元格为整数类型
            cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            // 设置该单元格字体格式(更多设置样式的方法请自行百度，参考地址：http://www.cnblogs.com/zhenmingliu/archive/2012/04/25/2469396.html)
            HSSFFont font2 = workbook.createFont();// 新建字体格式
            font2.setColor(HSSFColor.BLUE.index);// 设置字体颜色为蓝色，格式HSSFColor.XXX(颜色英文).index(转short类型)
           // font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 设置字体格式为粗体
            font2.setBold(true);
            font2.setItalic(true);// 设置字体格式为斜体 true为使用斜体，默认flase
            font2.setFontHeightInPoints((short) 16);// 设置字体大小
            font2.setFontName("华文彩云");// 设置字体格式为华文彩云
            HSSFCellStyle cellStyle2 = workbook.createCellStyle();// 新建单元格样式
           // cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置单元格居中属性
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);
            cellStyle2.setFont(font2);// 将字体样式应用于单元格样式
            cell2.setCellStyle(cellStyle2);// 将单元格样式应用于单元格
            // 在单元格中输入一些内容
            cell2.setCellValue("增加值2");

            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream(outputFile);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
            System.out.println("文件生成成功");
        } catch (Exception e) {
            System.out.println("文件生成失败： " + e);
        }
    }
    
    
    
    
    
    
}