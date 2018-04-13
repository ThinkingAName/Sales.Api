package com.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserCalendar {

	String monthFist = null,monthLast = null;
	
		//得到一月的第一天
	public String getMonthFirst(String date){
		 //创建日期格式对象
		  SimpleDateFormat formatOld=new SimpleDateFormat("yyyy-MM");
		  SimpleDateFormat formatNew = new SimpleDateFormat("yyyy-MM-dd 00:00:00:000 ");
		  //获取当前系统时间日历时间，通过日历时间对，日历进行加减运算；
		  Calendar cal = Calendar.getInstance();
		  	try {
				cal.setTime(formatOld.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   cal.set(Calendar.DAY_OF_MONTH,1);
		   monthFist =formatNew.format(cal.getTime());
		   return monthFist;
		
		
	}
			//得到一月的最后一天
		public String getMonthLast(String date){
			 //创建日期格式对象
			  SimpleDateFormat formatOld=new SimpleDateFormat("yyyy-MM");
			  SimpleDateFormat formatNew = new SimpleDateFormat("yyyy-MM-dd 00:00:00:000 ");
			  //获取当前系统时间日历时间，通过日历时间对，日历进行加减运算；
			  Calendar cal = Calendar.getInstance();
			  	try {
					cal.setTime(formatOld.parse(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   cal.set(Calendar.DAY_OF_MONTH,1);
			   cal.add(Calendar.MONTH, 1);
			   monthLast =formatNew.format(cal.getTime());
			   return monthLast;	
		}
	 
	
}
