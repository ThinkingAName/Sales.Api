package com.CommonUtils.GetTestData;



public class ExcelDataPath {
	public static String path = "";
	public static String sheet = "";
	public static String setExcelPath(String path1) {
		 path = path1;	
		 return path;
	}
	public static String getExcelPath() {
		return path;	
	}
	public static  String setExcelSheet(String sheet1) {
		sheet = sheet1;	
		return sheet;
		
	}
	public static String getExcelSheet() {
		return sheet;
			
	}
	//测试方法
//	public static void main(String[] args) {
//		ExcelDataPath excelDataPath = new ExcelDataPath();
//		System.out.println(excelDataPath.getExcelPath());
//		System.out.println(excelDataPath.getExcelSheet());
//	}
}
