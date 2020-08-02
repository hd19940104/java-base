package com.zixue.util.date;

import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
/** 
 * @author 一只会飞的小猴子
 * 日期转换工具类 
 */  
public class DateUtil {  
  
    /** 
     * 将日期格式转换成yyyy-MM-dd的字符串格式 
     * 返回值如：2010-10-06 
     * @param time 要转换的日期 
     * @return 
     */  
    public static  String dateToString(Date time)  {  
          
        SimpleDateFormat formatter = new  SimpleDateFormat ("yyyy-MM-dd"); //定义将日期格式要换成的格式  
        String stringTime  =  formatter.format(time);  
      
        return  stringTime;  
          
      }  
    /** 
     * 将日期格式转换成yyyyMMdd的字符串格式 
     * 返回值如：2010-10-06 
     * @param time 要转换的日期 
     * @return 
     */  
    public static  String dateTimeToString(Date time)  {  
          
        SimpleDateFormat formatter = new  SimpleDateFormat ("yyyyMMdd"); //定义将日期格式要换成的格式  
        String stringTime  =  formatter.format(time);  
      
        return  stringTime;  
          
      }  
      
       
    /** 
     * 将日期格式转换成yyyy-MM-dd的字符串格式 
     * 返回值如：2010-10-06 
     * @param time 要转换的日期 
     * @return 
     */  
    public static  Date dateToDate(Date time)  {  
          
        SimpleDateFormat formatter = new  SimpleDateFormat ("yyyy-MM-dd"); //定义将日期格式要换成的格式  
        String stringTime  =  formatter.format(time);  
     Date date = null;  
    try {  
        date = formatter.parse(stringTime);  
    } catch (ParseException e) {  
        e.printStackTrace();  
    }  
        return  date;  
          
    }  
      
    /** 
     * 得到当前时间，以字符串表示 
     * @return 
     */  
    public static String getDate(){  
        Date date = new Date();  
        return DateUtil.dateToString(date);  
    }  
    /** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }   
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"")));  
    }  
    /** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static String timeStamp(){  
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);  
        return t;  
    }  
}  
