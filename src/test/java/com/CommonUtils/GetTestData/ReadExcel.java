package com.CommonUtils.GetTestData;

/*
 * 使用POI读取EXCEL文件
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Hanbin
 */
public class ReadExcel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        read("E:\testData");
    }

    public static ArrayList read(String fileName){
        ArrayList list = new ArrayList();
        String sql = "";
        try{
            File f = new File(fileName);
            FileInputStream fis = new FileInputStream(f);
            HSSFWorkbook wbs = new HSSFWorkbook(fis);
            HSSFSheet childSheet = wbs.getSheetAt(0);
            System.out.println("行数:" + childSheet.getLastRowNum());
            for(int i = 4;i<childSheet.getLastRowNum();i++){
                HSSFRow row = childSheet.getRow(i);
                System.out.println("列数:" + row.getPhysicalNumberOfCells());
                if(null != row){
                    for(int k=1;k<row.getPhysicalNumberOfCells();k++){
                        HSSFCell cell;
                        cell = row.getCell((short)k);
                       // System.out.print(getStringCellValue(cell) + "\t");
                        list.add(getStringCellValue(cell) + "\t");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private static String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }
}


