package com.haitao.www.myformer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期转换工具类
 */
public class DateFormat {

	private static final SimpleDateFormat FMT_YMD = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

	/**
	 * 获取现在时间：注意格式
	 * @return
	 */
	public static String getCurrentTimehms(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		String result = sdf.format(new Date());
		return result;
	}


	public static String changeToFormatEnd(String dateString){
		if (dateString.equals("")) {
			return null;
		}
		String year = dateString.substring(0,4);
		String mouth = dateString.substring(4,6);
		String day = dateString.substring(6,8);
		String result = year+"-"+mouth+"-"+day+" "+ "23:59:59";
		return result;

	}

	/**
	 * 时间格式转换，注意格式
	 * @param dateString
	 * @return
	 */
	public static String changeToFormat_info(String dateString){
		if (dateString.equals("")) {
			return null;
		}
		String year = dateString.substring(0,4);
		String mouth = dateString.substring(4,6);
		String day = dateString.substring(6,8);
		String result = year+"-"+mouth+"-"+day;
		return result;

	}
	/**
	 * 时间格式转换，注意格式
	 * @return
	 */
	public static String getCurrentTime_info(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		String result = sdf.format(new Date());
		return result;
	}
	/**
	 * 将20130512格式转化为带“-”的格式
	 * @param dateString
	 * @return
	 */
	public static String changeToFormat(String dateString){
		if (dateString.equals("")) {
			return null;
		}
		String year = dateString.substring(0,4);
		String mouth = dateString.substring(4,6);
		String day = dateString.substring(6,8);
		String result = year+"-"+mouth+"-"+day+" "+ "00:00:00";
		return result;

	}
	
	/**
	 * 将20130512格式转化为带“-”的格式
	 * @param dateString
	 * @return
	 */
	public static String changeToFormats(String dateString){
		if (dateString.equals("")) {
			return null;
		}
		String year = dateString.substring(0,4);
		String mouth = dateString.substring(4,6);
		String day = dateString.substring(6,8);
		String result = year+"-"+mouth+"-"+day;
		return result;

	}
	/**
	 * 天津购电记录时间选择器只选年月
	 * 将201305格式转化为带“-”的格式
	 * @param dateString
	 * @return
	 */
	public static String changeToFormatYM(String dateString){
		if (dateString.equals("")) {
			return null;
		}
		String year = dateString.substring(0,4);
		String mouth = dateString.substring(4,6);
		String result = year+"-"+mouth+"-"+ "00:00";
		return result;

	}
	/**
	 * 获取现在时间：注意格式
	 * @return
	 */
	public static String getCurrentTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		String result = sdf.format(new Date());
		return result;
	}
	/**
	 * 获取现在时间：注意格式
	 * @return
	 */
	public static String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		String result = sdf.format(new Date());
		return result;

	}

	/**
	 * 获得三个月以前的时间
	 * @return
	 */
	/*
	public static String getThreeMouthAgoTime(){

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		int month = calendar.get(Calendar.MONTH)+1;
		if (month-3<1) {
			month = month-3+12;
			year-=1;
		}else {
			month-=3;
		}
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (month==2) {//二月
			if (year%400==0||(year%100!=0&&year%4==0)) {//闰年
				if (day>28) {
					day =28;
				}
			}else {//不是闰年
				if (day>28) {
					day=27;
				}
			}
		}
		if (month==4||month==6||month==9||month==11) {
			if (day>30) {
				day=30;
			}
		}

		String yearString = year+"";
		String monthString="";
		String dayString ="";
		if (month<10) {
			monthString= "0"+month;
		}else {
			monthString = month+"";
		}
		if (day<10) {
			dayString = "0"+day;
		}else {
			dayString = day+"";
		}
		String result = yearString+monthString+dayString;
		return result;

	}
	*/
	/**
	 * 20140813，将交费购电记录默认时间设置为6个月
	 * 获得六个月以前的时间（方法名没有变）
	 * @return 6个月前的时间
	 */
	public static String getThreeMouthAgoTime(){

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		int month = calendar.get(Calendar.MONTH)+1;
		if (month-6<1) {
			month = month-6+12;
			year-=1;
		}else {
			month-=6;
		}
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (month==2) {//二月
			if (year%400==0||(year%100!=0&&year%4==0)) {//闰年
				if (day>28) {
					day =28;
				}
			}else {//不是闰年
				if (day>28) {
					day=27;
				}
			}
		}
		if (month==4||month==6||month==9||month==11) {
			if (day>30) {
				day=30;
			}
		}

		String yearString = year+"";
		String monthString="";
		String dayString ="";
		if (month<10) {
			monthString= "0"+month;
		}else {
			monthString = month+"";
		}
		if (day<10) {
			dayString = "0"+day;
		}else {
			dayString = day+"";
		}
		String result = yearString+monthString+dayString;
		return result;

	}
	
	
	/**
	 * 20140813，将交费购电记录默认时间设置为1个月
	 * 获得六个月以前的时间（方法名没有变）
	 * @return 6个月前的时间
	 */
	public static String getOneMouthAgoTime(){

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		int month = calendar.get(Calendar.MONTH)+1;
		if (month-1<1) {
			month = month-1+12;
			year-=1;
		}else {
			month-=1;
		}
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (month==2) {//二月
			if (year%400==0||(year%100!=0&&year%4==0)) {//闰年
				if (day>28) {
					day =28;
				}
			}else {//不是闰年
				if (day>28) {
					day=27;
				}
			}
		}
		if (month==4||month==6||month==9||month==11) {
			if (day>30) {
				day=30;
			}
		}

		String yearString = year+"";
		String monthString="";
		String dayString ="";
		if (month<10) {
			monthString= "0"+month;
		}else {
			monthString = month+"";
		}
		if (day<10) {
			dayString = "0"+day;
		}else {
			dayString = day+"";
		}
		String result = yearString+monthString+dayString;
		return result;

	}
	/**
	 * 获得三个月以前的时间
	 * @return
	 */
	public static String getThreeMouthAgoTimebusiness(){

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		int month = calendar.get(Calendar.MONTH)+1;
		if (month-3<1) {
			month = month-3+12;
			year-=1;
		}else {
			month-=3;
		}
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String yearString = year+"-";
		String monthString="-";
		String dayString ="";
		if (month<10) {
			monthString= "0"+month+"-";
		}else {
			monthString = month+"-";
		}
		if (day<10) {
			dayString = "0"+day;
		}else {
			dayString = day+"";
		}
		String result = yearString+monthString+dayString;
		return result;

	}


	public static void main(String[] args) {



	}

	/**
	 * 获取现在时间：注意格式
	 * @return
	 */
	public static Date str2date(String dataStr){
		Date result = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		try {
			result = sdf.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 获得两个月以后的时间
	 * @return
	 */
	public static String getTwoMouthTime(){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 2);
		result = FMT_YMD.format(calendar.getTime());

		return result;

	}

	/**
	 * 获取当前日期 n个月差值日期
	 * @param n n>0 为当前日期之后n月  n<0 为当前日期之前n月
	 * @return YYYYMMDD
	 */
	public static String getNMonthDate(int n){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, n);
		result = FMT_YMD.format(calendar.getTime());

		return result;

	}

	/**
	 * 把yyyymmdd转成yyyy-MM-dd格式
	 *
	 * @return yyyy-MM-dd
	 */
	public static String formatDateYYYYMMDDtoYYYY_MM_DD(String str){
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 =new SimpleDateFormat("yyyy-MM-dd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sfstr;
	}

	/**
	 * 把yyyy-MM-dd转成yyyymmdd格式
	 *
	 * @return yyyymmdd
	 */
	public static String formatDateYYYY_MM_DDtoYYYYMMDD(String str){
		SimpleDateFormat sf1 =new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");

		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sfstr;
	}


	/**
	 * 获得一周以后的时间
	 * @return
	 */
	public static String getWeekTime(int num){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, num*7);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}
	/**
	 * 获得下一年1月1日的时间
	 * @return
	 */
	public static String getNewYearTime(int num){
		String result = null;
		result=(num+1)+"-01-01";
		return result;

	}


	/**
	 * 获取现在时间：注意格式 获取明天的时间
	 * @return
	 */
	public static String getCurrentDate1(){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}
	/**
	 * 获取现在时间：注意格式 获取近三天的时间
	 * @return
	 */
	public static String getCurrentDate2(){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.DAY_OF_YEAR, 2);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}

	/**
	 * 获取现在时间：注意格式 获取近四天的时间
	 * @return
	 */
	public static String getCurrentDate3(){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.DAY_OF_YEAR, 3);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}
	/**
	 * 获取现在时间：注意格式 获取近五天的时间
	 * @return
	 */
	public static String getCurrentDate4(){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.DAY_OF_YEAR, 4);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}
	/**
	 * 获取现在时间：注意格式 获取近6天的时间
	 * @return
	 */
	public static String getCurrentDate5(){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.DAY_OF_YEAR, 5);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}
	/**
	 * 获取现在时间：注意格式 获取近7天的时间
	 * @return
	 */
	public static String getCurrentDate6(){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.DAY_OF_YEAR, 6);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}
	/**
	 * 获取现在时间：获取之前的某一年的时间
	 * @return
	 */
	public static String getLastYear(int num){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.YEAR, -num);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}
	/**
	 * 获取现在时间：获取之后某年的时间
	 * @return
	 */
	public static String getNextYear(int num){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.YEAR, num);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}
	/**
	 * 获得几天以后的时间
	 *
	 * @return
	 */
	public static String getOneMouthTime(int i) {

		String result = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, i);
		result = FMT_YMD.format(calendar.getTime());
		return result;

	}
}
