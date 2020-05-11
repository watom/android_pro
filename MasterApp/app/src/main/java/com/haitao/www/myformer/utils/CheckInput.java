package com.haitao.www.myformer.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:输入检查工具类
 */
public class CheckInput {

	//注册、修改密码(新密码)、找回密码 (校验规则）
	public static String mRegisterPatternReg= "[a-zA-Z0-9~!@#$%^&*_-]*";
	//登录密码 (校验规则）
	public static String mRLoginPatternReg= "[a-zA-Z0-9~!@#$%^&*_?-]*";

	/**
	 * 用户帐号
	 * @return
	 */
	public static String checkUserCode(String userCode){
		String compare = "[a-zA-Z0-9_]*";
		String compare1 = "[0-9]*";
		
		if (userCode.equals("")) {
			return "请输入正确的注册帐号";
		}else if (userCode.matches(compare1)) {
			return "请输入正确的注册帐号";
		}else if (userCode.length()<6||userCode.length()>20) {
			return "请输入正确的注册帐号";
		}else if (!userCode.matches(compare)) {
			return "请输入正确的注册帐号";
		}
		return null;
		
	}
	/**
	 * 用户帐号(帐号信息维护，个人帐号)
	 * @return
	 */
	public static String checkUserCode_improve(String userCode){
		String compare = "[a-zA-Z0-9_]*";
		String compare1 = "[0-9]*";
		
		if (userCode.matches(compare1)) {
			return "请输入正确的注册帐号";
		}else if (userCode.length()<6||userCode.length()>20) {
			return "请输入正确的注册帐号";
		}else if (!userCode.matches(compare)) {
			return "请输入正确的注册帐号";
		}
		return null;
		
	}
	
	/**
	 * 用户帐号
	 * @param account
	 * @return
	 */
//	public static String checkAccount(String account){
//		String compare = "[a-zA-Z0-9_]*";
//		if (account.equals("")) {
//			return "帐号不能为空";
//		}else if (account.length()<6||account.length()>12) {
//			return "帐号为6-12位字符";
//		}else if (!account.matches(compare)) {
//			return "帐号不能包含特殊字符";
//		}
//		return null;
//		
//	}
	
	/**
	 * 用户帐号（找回密码）
	 * @param account
	 * @return
	 */
	public static String checkAccount(String account){
		String compare = "[a-zA-Z0-9_]*";
		if (account.equals("")) {
			return "请输入正确的用户名";
		}else if (account.length()<6||account.length()>20) {
			return "请输入正确的用户名";
		}else if (!account.matches(compare)) {
			return "请输入正确的用户名";
		}
		return null;
		
	}
	
	/**
	 * 用户帐号(从江苏登录帐号验证开始延伸，所有网省的帐号都为6-50，不能输入“$”和“|”符号)
	 * 
	 * @param account
	 * @return
	 */
	public static String checkAccount_js(String account){
		if (account.equals("")) {
			return "请输入正确的用户名/手机号";
		} else if (account.length() < 6 || account.length() > 50) {
			return "请输入正确的用户名/手机号";
		} else if (account.contains("|") || account.contains("$")) {
			return "请输入正确的用户名/手机号";
		}
		return null;
	}
	/**
	 * 用户注册帐号
	 * @param account
	 * @return
	 * 2015年3月23日，将“请输入正确的注册帐号”改为“用户名6-20位字母或字母数字组合”，与ios一致
	 * bug库代码：新0000022
	 */
	public static String checkRegisterAccount(String account){
		String compare = "[a-zA-Z0-9_]*";
		if (account.equals("")) {
			return "用户名请输入6-20位字母或字母数字组合";
		}else if (account.length()<6||account.length()>20) {
			return "用户名请输入6-20位字母或字母数字组合";
		}else if (!account.matches(compare)) {
			return "用户名请输入6-20位字母或字母数字组合";
		}
		return null;
		
	}
	/**
	 * 判断注册帐号是否全为数字
	 * @param account
	 * @return
	 */
	public static String isAllNum(String account) {
		String compare = "[0-9]*";
		if (account.equals("")) {
			return null;
		}
		if (account.matches(compare)) {
			return "用户名不能全为数字";
		}
		return null;
		
	}
	/**
	 * 判断帐号是否为手机号
	 * @param account
	 * @return true is phone number,false is not phone number
	 */
	public static boolean isPhoneNum(String account){
		String compare = "1[0-9]{10}";
		return account.matches(compare);
	}
	/**
	 * 登录密码 
	 * @param psw
	 * @return
	 */
//	public static String checkPsw(String psw){
//		String compare = "[a-zA-Z0-9~!@#$%^&*]*";
//		if (psw.equals("")) {
//			return "密码不能为空";
//		}else if (psw.length()<6||psw.length()>20) {
//			return "密码为6-20位字符";
//		}else if (!psw.matches(compare)) {
//			return "密码不能包含特殊字符";
//		}
//		return null;
//		
//	}
	
	/**
	 * 找回密码 
	 * @param psw
	 * @return
	 */
	public static String checkRetrievePsw(String psw){
		if (psw.equals("")) {
			return "新密码请输入6-20位字符";
		}else if (psw.length()<6||psw.length()>20) {
			return "新密码请输入6-20位字符";
		}else if (!psw.matches(mRegisterPatternReg)) {
			return "新密码请输入6-20位字符";
		}
		return null;
		
	}

	/**
	 * 登录密码 
	 * @param psw
	 * @return
	 */
	public static String checkPsw(String psw){
		if (psw.equals("")) {
			return "请输入正确的密码";
		}else if (psw.length()<6||psw.length()>20) {
			return "请输入正确的密码";
		}else if (!psw.matches(mRLoginPatternReg)) {
			return "请输入正确的密码";
		}
		return null;
		
	}
	/**
	 * 登录密码 （注册）
	 * @param psw
	 * @return
	 */
	public static String checkPswReg(String psw){
		if (psw.equals("")) {
			return "登录密码请输入6-20位字符";
		}else if (psw.length()<6||psw.length()>20) {
			return "登录密码请输入6-20位字符";
		}else if (!psw.matches(mRegisterPatternReg)) {
			return "登录密码请输入6-20位字符";
		}
		return null;
		
	}
	/**
	 * 确认密码 （注册）
	 * @param psw
	 * @return
	 */
	public static String checkPswOk(String psw){
		String compare = "[a-zA-Z0-9~!@#$%^&*]*";
		if (psw.equals("")) {
			return "确认密码请输入6-20位字符";
		}else if (psw.length()<6||psw.length()>20) {
			return "确认密码请输入6-20位字符";
		}else if (!psw.matches(compare)) {
			return "确认密码请输入6-20位字符";
		}
		return null;
		
	}
	/**
	 * 密码修改中输入旧密码 
	 * @param psw
	 * @return
	 */
	public static String checkPswEdit(String psw){
		if (psw.equals("")) {
			return "请输入正确的旧密码";
		}else if (psw.length()<6||psw.length()>20) {
			return "请输入正确的旧密码";
		}else if (!psw.matches(mRLoginPatternReg)) {
			return "请输入正确的旧密码";
		}
		return null;
		
	}
	
	/**
	 * 密码修改中输入新密码 
	 * @param psw
	 * @return
	 */
	public static String checkPswEditNew(String psw){
		if (psw.equals("")) {
			return "新密码请输入6-20位字符";
		}else if (psw.length()<6||psw.length()>20) {
			return "新密码请输入6-20位字符";
		}else if (!psw.matches(mRegisterPatternReg)) {
			return "新密码请输入6-20位字符";
		}
		return null;
		
	}
	
	/**
	 * 密码修改中输入确认密码 
	 * @param psw
	 * @return
	 */
	public static String checkPswEditConfirm(String psw){
		String compare = "[a-zA-Z0-9~!@#$%^&*]*";
		if (psw.equals("")) {
			return "确认密码请输入6-20位字符";
		}else if (psw.length()<6||psw.length()>20) {
			return "确认密码请输入6-20位字符";
		}else if (!psw.matches(compare)) {
			return "确认密码请输入6-20位字符";
		}
		return null;
		
	}
	/**
	 * 客户编号
	 * @param markNum
	 * @return
	 */
//	public static String checkMarkNum(String markNum){
//		if (markNum.equals("")) {
//			return "客户编号不能为空";
//		}else if (markNum.length()<1||markNum.length()>16) {
//			return "客户编号为1-16位数字";
//		}
//		return null;
//		
//	}
	
	/**
	 * 客户编号
	 * @param markNum
	 * @return
	 */
	public static String checkMarkNum(String markNum){
		String compare = "[0-9]*";
		if (markNum.equals("")) {
			return "请输入正确的客户编号";
		}else if (markNum.length()>10||markNum.length()<7) {
			return "请输入正确的客户编号";
		}else if (!markNum.matches(compare)) {
			return "请输入正确的客户编号";
		}
		
		return null;
		
	}
	
	
//	public static String checkQueryKey(String queryKey) {
//		if (queryKey.equals("")) {
//			return "查询密码不能为空";
//		}else if (queryKey.length()<1||queryKey.length()>16) {
//			return "查询密码为1-16位字符";
//		}
//		return null;
//		
//	}
	
	public static String checkQueryKey(String queryKey) {
		/**--------20140409将查询密码改为和登录密码一样的正则----------**/
		String compare = "[a-zA-Z0-9~!@#$%^&*]*";
//		String compare = "[a-zA-Z0-9_]*";
		/**-----------------------------**/
		if (queryKey.equals("")) {
			return "请输入正确的查询密码";
		}else if (queryKey.length()<6||queryKey.length()>12) {
			return "请输入正确的查询密码";
		}else if (!queryKey.matches(compare)) {
			return "请输入正确的查询密码";
		}
		return null;
		
	}
	public static String checkQueryKeyOld(String queryKey) {
		/**--------20140409将查询密码改为和登录密码一样的正则----------**/
		String compare = "[a-zA-Z0-9~!@#$%^&*]*";
//		String compare = "[a-zA-Z0-9_]*";
		/**-----------------------------**/
		if (queryKey.equals("")) {
			return "请输入正确的旧密码";
		}else if (queryKey.length()<6||queryKey.length()>12) {
			return "请输入正确的旧密码";
		}else if (!queryKey.matches(compare)) {
			return "请输入正确的旧密码";
		}
		return null;
		
	}
	public static String checkQueryKeyNew(String queryKey) {
		String compare = "[0-9]*";
		if (queryKey.equals("")) {
			return "新密码请输入6-12位数字";
		}else if (queryKey.length()<6||queryKey.length()>12) {
			return "新密码请输入6-12位数字";
		}else if (!queryKey.matches(compare)) {
			return "新密码请输入6-12位数字";
		}
		return null;
		
	}
	public static String checkQueryKeyNew2(String queryKey) {
		String compare = "[0-9]*";
		if (queryKey.equals("")) {
			return "确认密码请输入6-20位数字";
		}else if (queryKey.length()<6||queryKey.length()>12) {
			return "确认密码请输入6-20位数字";
		}else if (!queryKey.matches(compare)) {
			return "确认密码请输入6-20位数字";
		}
		return null;
		
	}
	/**
	 * 手机号码
	 * @param phoneNum
	 * @return
	 */
//	public static String checkPhoneNum(String phoneNum) {
//		String compare = "1[0-9]{10}";
//		if (phoneNum.equals("")) {
//			return "手机号不能为空";
//		}else if (phoneNum.length()!=11) {
//			return "手机号码为11位数字";
//		}else if (!phoneNum.matches(compare)) {
//			return "手机号码不正确";
//		}
//		return null;
//		
//	}
	
	/**
	 * 手机号码
	 * @param phoneNum
	 * @return
	 */
	public static String checkPhoneNum(String phoneNum) {
		String compare = "1[0-9]{10}";
		if (phoneNum == null || phoneNum.equals("")) {
			return "手机号不能为空";
		}else if (!phoneNum.matches(compare)) {
			return "请输入正确的手机号";
		}
		return null;
		
	}
	
	/**
	 * 座机号码
	 * @param phoneNum
	 * @return
	 */
	public static String checkHomeNum(String phoneNum) {
		String compare="^(\\d{3,4}-)\\d{7,8}$";
		if (phoneNum == null || phoneNum.equals("")) {
			return "座机号不能为空";
		}else if (!isHomeNO(phoneNum)) {
			return "请输入正确的座机号";
		}
		return null;
	}

	/*
    * 验证号码 手机号 固话均可
    *
    */
	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;

//		String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
		String expression = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$";
		CharSequence inputStr = phoneNumber;

		Pattern pattern = Pattern.compile(expression);

		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches() ) {
			isValid = true;
		}

		return isValid;

	}

	/**
	 * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 * @param mobile 移动、联通、电信运营商的号码段
	 *<p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	 *、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
	 *<p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
	 *<p>电信的号段：133、153、180（未启用）、189</p>
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean isMobile(String mobile){
		String regex = "(\\+\\d+)?1[3458]\\d{9}$";
		return Pattern.matches(regex, mobile);
	}
	/**
	 * 区号+座机号码+分机号码
	 * @param fixedPhone
	 * @return
	 */
	public static boolean isFixedPhone(String fixedPhone){
		String reg="(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
				"(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
		return Pattern.matches(reg, fixedPhone);
	}
	/**
	 * 匹配中国邮政编码
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean isPostCode(String postCode){
		String reg = "[1-9]\\d{5}";
		return Pattern.matches(reg, postCode);
	}



//	public static void main(String[] args) {
//		String mobile = "18600000000";
//		boolean ret = isMobile(mobile);
//		System.out.println(ret);
//
//		String postCode = "110200";
//		ret = isPostCode(postCode);
//		System.out.println(ret);
//
//		String isFixedPhone = "0571-8888880-111";
//		ret = isFixedPhone(isFixedPhone);
//		System.out.println(ret);
//
//	}


	/**
	 * 邮箱
	 * @param email
	 * @return
	 */
	/** 
	 * 验证手机格式 
	 */  
	public static boolean isMobileNO(String mobiles) {
	    /* 
	    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 
	    联通：130、131、132、152、155、156、185、186 
	    电信：133、153、180、189、（1349卫通） 
	    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9 
	    */  
	    String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
	    if (TextUtils.isEmpty(mobiles)) return false;
	    else return mobiles.matches(telRegex);  
	   }
	
	
	/** 
	 * 验证座机格式 
	 */  
	public static boolean isHomeNO(String mobiles) {
	    String telRegex = "^(\\d{3,4})\\d{7,8}$";
	    if (TextUtils.isEmpty(mobiles)) return false;
	    else return mobiles.matches(telRegex);  
	   }
	
	
	
	
	public static String checkEmail(String email){
		
		String compare = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		if (email.equals("")) {
			return null;
		}else if (!email.matches(compare)) {
			return "请输入正确的邮箱地址";
		}
		return null;
	}
	/*************************************20150204 ning.zhou*****************************************/
	/*************************************修改邮箱输入汉字而崩溃的问题*****************************************/
	/*****************************************Bug :0005354*****************************************/
//	/**
//	 * 邮箱，邮箱不能为空
//	 * @param email
//	 * @return
//	 */
//	public static String checkEmail2(String email){
//		String compare = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
//		if (email.equals("")) {
//			return "请输入正确的邮箱地址";
//		}else if (!email.matches(compare)) {
//			return "请输入正确的邮箱地址";
//		}
//		return null;
//	}
	/**
	 * 因为邮箱输入汉字崩溃的问题修改正则表达式
	 * @return
	 */
	public static String checkEmail2(String email) {
//		String compare = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		String compare = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(compare);
		Matcher m = p.matcher(email);
		if (email.equals("")) {
			return "请输入正确的邮箱地址";
		}
		if (!m.matches()) {
			return "请输入正确的邮箱地址";
		}
		return null;
	}
	/***************************************20150204 ning.zhou***************************************/
	/********************************************end*************************************************/
	/**
	 * 联系人
	 * @param contactPerson
	 * @return
	 */
	public static String checkContactPerson(String contactPerson){
		//String compare = "^[\u4e00-\u9fa5]*$";
		if (contactPerson.equals("")) {
			return null;
		}else if (contactPerson.length()<2||contactPerson.length()>4) {
			return "请输入正确的联系人";
		}else if (!contactPerson.matches(UITools.REGEXP_CONTANT)) {
			return "请输入正确的联系人";
		}
		return null;
		
	}
	/**
	 * 地址
	 * @param addr
	 * @return
	 */
	public static String checkAddr(String addr) {
		int number = 0;
		try {
			number = addr.getBytes("GB18030").length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (addr.equals("")) {
			return null;
		}else if (number<3||number>120) {
			return "请输入正确的地址";
		}
		return null;
		
	}
	/**
	 * 验证码
	 * @param verif
	 * @return
	 */
//	public static String checkVerif(String verif) {
//		if (verif.equals("")) {
//			return "验证码不能为空";
//		}else if (verif.length()!=6) {
//			return "验证码为6位数字";
//		}
//		return null;
//		
//	}
	
	/**
	 * 验证码
	 * @param verif
	 * @return
	 */
	public static String checkVerif(String verif) {
		if (verif.equals("")) {
			return "请输入正确的验证码";
		}else if (verif.length()!=6) {
			return "请输入正确的验证码";
		}
		return null;
		
	}
	/**
	 * 查询关键字
	 * @param keyWord
	 * @return
	 */
	public static String checkKeyWord(String keyWord) {
		if (keyWord.length()<1||keyWord.length()>30) {
			return "关键字为1-39位字符";
		}
		return null;
		
	}
	/**
	 * 检查输入的是否为身份证
	 * @param value
	 * @return
	 */
	public static String cheakPersonalCard2(String value) {
		int length = 0;
		if (Util.isEmpty(value)) {
			return "请输入身份证号！";
		} else {
			length = value.length();
			if (length == 15) {
			} else if (length == 18) {
			} else {
				return "您输入身份证号位数不对！";
			}
		}
		String[] areasArray = {"11", "12", "13", "14", "15", "21", "22", "23", "31",
				"32", "33", "34", "35", "36", "37", "41", "42", "43", "44",
				"45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
				"64", "65", "71", "81", "82", "91"};

		HashSet<String> areasSet = new HashSet<String>(Arrays.asList(areasArray));
		String valueStart2 = value.substring(0, 2);


		if (areasSet.contains(valueStart2)) {
		} else {
			return "您输入正确的身份证号！";
		}

		Pattern pattern = null;
		Matcher matcher = null;


		int year = 0;
		switch (length) {
			case 15:
				year = Integer.parseInt(value.substring(6, 8)) + 1900;


				if (year % 4 == 0 || (year % 100 == 0 && year % 4 == 0)) {
					pattern = Pattern.compile("^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$"); // 测试出生日期的合法性
				} else {
					pattern = Pattern.compile("^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$"); // 测试出生日期的合法性
				}
				matcher = pattern.matcher(value);
				if (matcher.find()) {
					return null;
				} else {
					return "您输入正确的身份证号！";
				}
			case 18:
				year = Integer.parseInt(value.substring(6, 10));


				if (year % 4 == 0 || (year % 100 == 0 && year % 4 == 0)) {
					pattern = Pattern.compile("^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$"); // 测试出生日期的合法性
				} else {
					pattern = Pattern.compile("^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$"); // 测试出生日期的合法性
				}


				matcher = pattern.matcher(value);
				if (matcher.find()) {
					int S = (Integer.parseInt(value.substring(0, 1)) + Integer.parseInt(value.substring(10, 11))) * 7 + (Integer.parseInt(value.substring(1, 2)) + Integer.parseInt(value.substring(11, 12))) * 9 + (Integer.parseInt(value.substring(2, 3)) + Integer.parseInt(value.substring(12, 13))) * 10 + (Integer.parseInt(value.substring(3, 4)) + Integer.parseInt(value.substring(13, 14))) * 5 + (Integer.parseInt(value.substring(4, 5)) + Integer.parseInt(value.substring(14, 15))) * 8 + (Integer.parseInt(value.substring(5, 6)) + Integer.parseInt(value.substring(15, 16))) * 4 + (Integer.parseInt(value.substring(6, 7)) + Integer.parseInt(value.substring(16, 17))) * 2 + Integer.parseInt(value.substring(7, 8)) * 1 + Integer.parseInt(value.substring(8, 9)) * 6 + Integer.parseInt(value.substring(9, 10)) * 3;
					int Y = S % 11;
					String M = "F";
					String JYM = "10X98765432";
					M = JYM.substring(Y, Y + 1); // 判断校验位
					if (M.equals(value.substring(17, 18))) {
						return null; // 检测ID的校验位
					} else {
						return "您输入正确的身份证号！";
					}
				} else {
					return "您输入正确的身份证号！";
				}
			default:
				return "您输入正确的身份证号！";
		}
	}
	/**
	 * 检查输入的是否为身份证
	 */
	public static String cheakPersonalCard(String personalCard){
		//15位身份证正则表达式
		String compare = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
		//18位身份证正则表达式
		String compare1 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
		String compare2 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
		String compare3 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|x)$";
		if (personalCard.equals("")) {
			return "请输入正确的身份证号";
		}else if (personalCard.length()!=15&&personalCard.length()!=18) {
			return "请输入正确的身份证号";
		}else if ((!personalCard.matches(compare))
				&&(!personalCard.matches(compare1))
				&&(!personalCard.matches(compare2))
				&&(!personalCard.matches(compare3))) {
			return "请输入正确的身份证号";
		}
		return null;
	}

	public static String upCaseXforId(String id){
		if (!TextUtils.isEmpty(id)){
			if (id.contains("x")){
				return id.replace("x","X");
			}
		}
		return id;
	}
	private final static Pattern idcard = Pattern.compile("\\^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$");
	/**
	 * 检验18位身份证号码（15位号码可以只检测生日是否正确即可）
	 * @author wolfchen
	 * @param idCard 18为的身份证号码
	 * @return Boolean 是否合法
	 **/

	public static boolean check(Context context, String idCard) {
		if (idcard.matcher(idCard).matches()) {
			if (idCard.length() == 18) {
				//将前17位加权因子保存在数组里
				int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
				//这是除以11后，可能产生的11位余数、验证码，也保存成数组
				int[] idCardY = {1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};
				//用来保存前17位各自乖以加权因子后的总和
				int idCardWiSum = 0;
				for (int i = 0; i < 17; i++) {
					idCardWiSum += Integer.valueOf(idCard.substring(i, i + 1)) * idCardWi[i];
				}
				int idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置
				String idCardLast = idCard.substring(17);//得到最后一位身份证号码
				//如果等于2，则说明校验码是10，身份证号码最后一位应该是X
				if (idCardMod == 2) {
					if (idCardLast.equalsIgnoreCase("X")) {
						return true;
					} else {
						Toast.makeText(context, "身份证号最后一位有误",Toast.LENGTH_LONG).show();
					}
				} else {
					//用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
					if (idCardLast.equals(String.valueOf(idCardY[idCardMod]))) {
						return true;
					} else {
						Toast.makeText(context, "身份证无效",Toast.LENGTH_LONG).show();
					}
				}
			} else {
				Toast.makeText(context, "长度不够",Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(context, "身份证格式错误",Toast.LENGTH_LONG).show();
		}
		return false;
	}


	public static boolean  isCnNewID(String id){
		char idChar[] = id.toCharArray();//412727199401156573
		//4*7+  1*9+  2*10+  7*5+  2*8+  7*4+  1*2+ 9*1+  9*6+  4*3+  0*7+  1*9+  1*10+  5*5+  6*8+  5*4=  7*2==325
		char arrExp[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};//加权因子
		char arrValid[] = {1, 0, 'x', 9, 8, 7, 6, 5, 4, 3, 2};//校验码
		char arrRe[] = {0,1,2,3,4,5,6,7,8,9,10};//余数
		int sum=0;
		int idx=-1;
		char end=idChar[17];
		boolean isId=false;
		int currentNum=-1;
		if(id.length()==18) {
			for (int i = 0; i < id.length()-1; i++) {

				sum +=idChar[i]*arrExp[i];
			}
			idx = sum%11;//余数

			for (int i = 0; i < arrRe.length; i++) {
				if(idx==arrRe[i]) {
					currentNum=i;
				}
			}
			if(currentNum!=-1) {
				char c = arrValid[currentNum];
				if(end==c) {
					isId=true;
				}
			}
		}else {
			isId = false;
		}
		return isId;
	}


	/**
	 * 找回密码 
	 * @param psw
	 * @return
	 */
	public static String checkBusiNum(String psw){
		String compare = "[0-9]*";
		if (psw.equals("")) {
			return "请输入正确的申请编号";
		}else if (psw.length()<10||psw.length()>16) {
			return "请输入正确的申请编号";
		}else if (!psw.matches(compare)) {
			return "请输入正确的申请编号";
		}
		return null;
		
	}
	/**
	 * 高压新装申请企业名称
	 * @param name
	 * @return
	 */
	public static String checkCompanyName(String name){
		if (name.equals("")) {
			return "请输入正确的名称";
		}else if (name.length()<3||name.length()>50) {
			return "请输入正确的名称";
		}
		return null;
		
	}
	/**
	 * 高压新装申请企业地址
	 * @param addr
	 * @return
	 */
	public static String checkCompanyAddr(String addr){
		if (addr.equals("")) {
			return "请输入正确的地址";
		}else if (addr.length()<3||addr.length()>120) {
			return "请输入正确的地址";
		}
		return null;
		
	}


	/**
	 * 银行卡校验规则
	 *
	 * @param bankNo
	 *
	 */
	public static boolean luhmCheck(String bankNo) {
		// UiToast toast = new UiToast(context, null);
		// if (bankNo.length() < 16 || bankNo.length() > 19) {
		// toast.setStr("银行卡号长度必须在16到19之间");
		// toast.show();
		// }

		int lastNum = Integer.parseInt(bankNo.substring(bankNo.length() - 1,
				bankNo.length()));// 取出最后一位（与luhm进行比较）

		String first15Num = bankNo.substring(0, bankNo.length() - 1);// 前15或18位
		// System.out.println(first15Num);
		char[] newArr = new char[first15Num.length()]; // 倒叙装入newArr
		char[] tempArr = first15Num.toCharArray();
		for (int i = 0; i < tempArr.length; i++) {
			newArr[tempArr.length - 1 - i] = tempArr[i];
		}

		int[] arrSingleNum = new int[newArr.length]; // 奇数位*2的积 <9
		int[] arrSingleNum2 = new int[newArr.length];// 奇数位*2的积 >9
		int[] arrDoubleNum = new int[newArr.length]; // 偶数位数组

		for (int j = 0; j < newArr.length; j++) {
			if ((j + 1) % 2 == 1) {// 奇数位
				if ((newArr[j] - 48) * 2 < 9)
					arrSingleNum[j] = (newArr[j] - 48) * 2;
				else
					arrSingleNum2[j] = (newArr[j] - 48) * 2;
			} else
				// 偶数位
				arrDoubleNum[j] = newArr[j] - 48;
		}

		int[] arrSingleNumChild = new int[newArr.length]; // 奇数位*2 >9
		// 的分割之后的数组个位数
		int[] arrSingleNum2Child = new int[newArr.length];// 奇数位*2 >9
		// 的分割之后的数组十位数

		for (int h = 0; h < arrSingleNum2.length; h++) {
			arrSingleNumChild[h] = (arrSingleNum2[h]) % 10;
			arrSingleNum2Child[h] = (arrSingleNum2[h]) / 10;
		}

		int sumSingleNum = 0; // 奇数位*2 < 9 的数组之和
		int sumDoubleNum = 0; // 偶数位数组之和
		int sumSingleNumChild = 0; // 奇数位*2 >9 的分割之后的数组个位数之和
		int sumSingleNum2Child = 0; // 奇数位*2 >9 的分割之后的数组十位数之和
		int sumTotal = 0;
		for (int m = 0; m < arrSingleNum.length; m++) {
			sumSingleNum = sumSingleNum + arrSingleNum[m];
		}

		for (int n = 0; n < arrDoubleNum.length; n++) {
			sumDoubleNum = sumDoubleNum + arrDoubleNum[n];
		}

		for (int p = 0; p < arrSingleNumChild.length; p++) {
			sumSingleNumChild = sumSingleNumChild + arrSingleNumChild[p];
			sumSingleNum2Child = sumSingleNum2Child + arrSingleNum2Child[p];
		}

		sumTotal = sumSingleNum + sumDoubleNum + sumSingleNumChild
				+ sumSingleNum2Child;

		// 计算Luhm值
		int k = sumTotal % 10 == 0 ? 10 : sumTotal % 10;
		int luhm = 10 - k;

		if (lastNum == luhm) {

			return true;

		} else if (bankNo.length() < 16 || bankNo.length() > 19) {
			return false;
		} else {

			return false;
		}
	}


	/**
	 * 高压新装申请固定电话
	 * @param tel
	 * @return
	 */
	public static String checkCompanyTel(String tel){
		if (tel.equals("")) {
			return "请输入正确的电话号码";
		}else if (tel.length()<6||tel.length()>13) {
			return "请输入正确的电话号码";
		}
		return null;
		}
		
		/**
		 * 高压新装申请证件号码
		 * @param certNo
		 * @return
		 */
		public static String checkCompanyCertNo(String certNo){
			if (certNo.equals("")) {
				return "请输入正确的证件号码";
			}else if (certNo.length()<6||certNo.length()>36) {
				return "请输入正确的证件号码";
			}
			return null;
			
	}

	/**
	 * 检测密码强度
	 * @param password
	 * @return
     */
		public static boolean checkPasswordStrength(String password){
//			 Regex.Replace( "^(?:([a-z])|([A-Z])|([0-9])|(.)){6,}|(.)+$", "$1$2$3$4$5").Length;
			String regexZ = "\\d*";
			String regexS = "[a-zA-Z]+";
			String regexT = "\\W+$";
			String regexZT = "\\D*";
			String regexST = "[\\d\\W]*";
			String regexZS = "\\w*";
			String regexZST = "[\\w\\W]*";
//			if (password.matches ("(?i)^((\\d+[\\da-z]*[a-z]+)|([a-z]+[\\da-z]*\\d+)|([a-z]+[\\da-z]*[a-z]*)|(\\d+[\\da-z]*\\d*))$"))
//			{
//				System.out.println ("密码强");
//			}
			if (password.matches(regexZ)) {
//				return "弱";
				return false;
			}
			if (password.matches(regexS)) {
//				return "弱";
				return false;
			}
			if (password.matches(regexT)) {
//				return "弱";
				return false;
			}
			if (password.matches(regexZT)) {
//				return "中";
				return true;
			}
			if (password.matches(regexST)) {
//				return "中";
				return true;
			}
			if (password.matches(regexZS)) {
//				return "中";
				return true;
			}
			return password.matches(regexZST);
		}



}
