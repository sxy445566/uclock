package com.sxy.uclock.tools;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.text.TextUtils;
/********************************************** 
* @类名: DateTools 
*
* @描述:  时间工具类，用于获取各种时间字符串
*
* @作者： SXY
* 
* @创建日期： 2015-6-8
* 
* @版本： V1.0 
*
* @修订历史：
* 
***********************************************/
@SuppressLint("SimpleDateFormat")
public class DateTools {
	/**
	 * 将时间戳转为字符串 ，格式：yyyy-MM-dd HH:mm
	 */
	public static String getStrTime_ymd_hm(String cc_time) {
		String re_StrTime = "";
		if(TextUtils.isEmpty(cc_time) || "null".equals(cc_time)){
			return re_StrTime;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;

	}
	/**
	 * 将日期戳转为字符串 ，格式：yyyy-MM-dd 
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStrTime_ymd_hma(String cc_time) {
		String re_StrTime = "";
		if(TextUtils.isEmpty(cc_time) || "null".equals(cc_time)){
			return re_StrTime;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;

	}
	/**
	 * 将时间戳转为字符串 ，格式：yyyy-MM-dd HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStrTime_ymd_hms(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;

	}

	/**
	 * 将日期戳转为字符串 ，格式：yyyy.MM.dd
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStrTime_ymd(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 将年份转为字符串 ，格式：yyyy
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStrTime_y(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 将月日转为字符串 ，格式：MM-dd
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStrTime_md(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 将时分转为字符串 ，格式：HH:mm
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStrTime_hm(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 将时间转为字符串 ，格式：HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStrTime_hms(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}
	
	/**
	 * 将时间戳转为字符串 ，格式：MM-dd HH:mm:ss
	 */
	public static String getNewsDetailsDate(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 将字符串转为时间戳
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getTime() {
		String re_time = null;
		long currentTime = System.currentTimeMillis();
		Date d;
		d = new Date(currentTime);
		long l = d.getTime();
		String str = String.valueOf(l);
		re_time = str.substring(0, 10);
		return re_time;
	}
	
	/**
	 * 将时间戳转为字符串 ，格式：yyyy.MM.dd  星期几
	 */
	public static String getSection(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  EEEE");
//		对于创建SimpleDateFormat传入的参数：EEEE代表星期，如“星期四”；MMMM代表中文月份，如“十一月”；MM代表月份，如“11”；
//		yyyy代表年份，如“2010”；dd代表天，如“25”
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}
	/**
	 * 把得到的系统时间转为10位的时间戳
	 */
	@SuppressLint("SimpleDateFormat")
	public static long getTimestamp(Long theTime){
		   Timestamp ts = new Timestamp(theTime);
	         
	        return ((ts.getTime())/1000);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String getInterval(String createtime) { //传入的时间格式必须类似于2012-8-21 17:53:20这样的格式
		String interval = null;
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date d1 = (Date) sd.parse(createtime, pos);
		
		//用现在距离1970年的时间间隔new Date().getTime()减去以前的时间距离1970年的时间间隔d1.getTime()得出的就是以前的时间与现在时间的时间间隔
		long time = new Date().getTime() - d1.getTime();// 得出的时间间隔是毫秒
		
		if(time/1000 < 10 && time/1000 >= 0) {
		//如果时间间隔小于10秒则显示“刚刚”time/10得出的时间间隔的单位是秒
			interval ="刚刚";
			
		} else if(time/3600000 < 24 && time/3600000 >= 0) {
		//如果时间间隔小于24小时则显示多少小时前
			int h = (int) (time/3600000);//得出的时间间隔的单位是小时
			interval = h + "小时前";
			
		} else if(time/60000 < 60 && time/60000 > 0) {
		//如果时间间隔小于60分钟则显示多少分钟前
			int m = (int) ((time%3600000)/60000);//得出的时间间隔的单位是分钟
			interval = m + "分钟前";
			
		} else if(time/1000 < 60 && time/1000 > 0) {
		//如果时间间隔小于60秒则显示多少秒前
			int se = (int) ((time%60000)/1000);
			interval = se + "秒前";
			
		}else {
			//大于24小时，则显示正常的时间，但是不显示秒
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			ParsePosition pos2 = new ParsePosition(0);
			Date d2 = (Date) sdf.parse(createtime, pos2);

			interval = sdf.format(d2);
			//TODO 暂时修改为24小时前
			interval = "24小时前";
		}
		return interval;
	}
}
