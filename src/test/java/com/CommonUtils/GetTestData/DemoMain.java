package com.CommonUtils.GetTestData;

	//日期计算
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 //数字分位符和小数位数操作
 import java.math.BigDecimal;
 import java.text.DecimalFormat;
 import java.text.NumberFormat;
 
 public class DemoMain {
   private static String firstDay;
   private static String lastDay;
   static String date = "2018-01";
     public static void main(String[] args) {
         SimpleDateFormat formatOld = new SimpleDateFormat("yyyy-MM"); 
         SimpleDateFormat formatNew = new SimpleDateFormat("yyyy-MM-dd 00:00:00:000 "); 
         
         //获取前月的第一天
         Calendar   cal_1=Calendar.getInstance();//获取当前日期 
         try {
        	 cal_1.setTime(formatOld.parse(date));
        	 firstDay=formatOld.format(cal_1.getTime());
        	 System.out.println("-----0------firstDay:"+firstDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         //cal_1.add(Calendar.DATE, +1);
         cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
         firstDay = formatNew.format(cal_1.getTime());
         System.out.println("-----1------firstDay:"+firstDay);
         //获取前月的最后一天
         //Calendar cale = Calendar.getInstance();   
         //cal_1.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
         cal_1.add(Calendar.MONTH, 1);
         lastDay = formatNew.format(cal_1.getTime());
         System.out.println("-----2------lastDay:"+lastDay);
           
           
         //获取当前月第一天：
         Calendar c = Calendar.getInstance();    
         c.add(Calendar.MONTH, 0);
         c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
         String first = formatOld.format(c.getTime());
         System.out.println("===============first:"+first);
           
         //获取当前月最后一天
         Calendar ca = Calendar.getInstance();    
         ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
         String last = formatOld.format(ca.getTime());
         System.out.println("===============last:"+last);
          
     }
     /** 
    * 字符串的日期格式的计算 
    */ 
   public static int daysBetween(String smdate,String bdate) throws ParseException{ 
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
           Calendar cal = Calendar.getInstance(); 
           cal.setTime(sdf.parse(smdate)); 
           long time1 = cal.getTimeInMillis();           
           cal.setTime(sdf.parse(bdate)); 
           long time2 = cal.getTimeInMillis(); 
           long between_days=(time2-time1)/(1000*3600*24); 
           return Integer.parseInt(String.valueOf(between_days)); 
   }
   
  

   /**
    * java之保留几位小数的几种方式及添加千位分隔符
    * <p>ClassName: TestDecimal</p>
    * <p>Description: 保留几位小数</p>
    * <p>Author: Administrator</p>
    * <p>Date: 2016年4月26日</p>
    */
  
       public static  void main2(String[] args) {
           //方式一  小数位数不足4位者有几位就是几位，多于4位者仅留4位
           double dd1 = 911.911;
           double dd2 = 911.911911;
           DecimalFormat df = new DecimalFormat("#.0000");
           dd1 = Double.parseDouble(df.format(dd1));
           dd2 = Double.parseDouble(df.format(dd2));
           System.out.println(dd1);
           System.out.println(dd2);
           System.out.println("////////");
           //方式二  小数位数不足4位者用0补全，多于4位者仅留4位
           double ds1 = 911.911;
           double ds2 = 911.911911;
           String result1 = String.format("%.4f",ds1);
           String result2 = String.format("%.4f",ds2);
           System.out.println(result1);
           System.out.println(result2);
           System.out.println("////////");
           //方式三  小数位数不足4位者有几位就是几位，多于4位者仅留4位，并四舍五入
           double db1 = 911.911;
           double db2 = 911.91186;
           BigDecimal bd1 = new BigDecimal(db1);
           BigDecimal bd2 = new BigDecimal(db2);
           db1 = bd1.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
           db2 = bd2.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
           System.out.println(db1);
           System.out.println(db2);
           System.out.println("////////");
           //方式四  小数位数不足4位者有几位就是几位，多于4位者仅留4位
           double dn1 =911.911;
           double dn2 =911.911911;
           NumberFormat nf = NumberFormat.getNumberInstance();
           nf.setMaximumFractionDigits(4);
           String str1 = nf.format(dn1);
           String str2 = nf.format(dn2);
           System.out.println(str1);
           System.out.println(str2);
           System.out.println("////////");
           //添加千位分隔符
           double n = 1000.3;
           DecimalFormat df2 = new DecimalFormat("#,###.00");
           String m = df2.format(n);
           System.out.print(m);
       }
   
   
    
 }