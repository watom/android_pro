package com.haitao.www.myformer.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.haitao.www.myformer.log.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**********************************************************************************
Copyright (C), 2011-2012, 北京国电通网络技术有限公司. 
FileName: com.smartlife.mobile.util.UITools.java
Author:　     lei.peng　　　 
Version :     V1.0.1
Date:         0921
Description:　
 ***********************************************************************************
History:　　　update past records 
 <Author>　          <Date>　　 <Version>　  <Description> 
lei.peng　　　2012-09-21　　V4.0.2　　        build
lei.peng     2012-10-08     V4.0.5     BUG-0000492  修改正则表达式。涉及文件：UITools.java
st.cheng     2012-11-15    V4.0.5      BUG-0000836  注释掉了对尾部字符的正则涉及文件：UITools.java
 ***********************************************************************************/ 

public class UITools {


	//验证地址正则表达式(中、英文字母、数字、“-”)
	public final static String REGEXP_ADDRESS="^[-A-Za-z0-9\\u4e00-\\u9fa5]*$";
	//验证用户名(英文字母、数字、“_”)
	public final static String REGEXP_USERNAME="^[_A-Za-z0-9]*$";
	/************2012-10-29  lei.peng  BUG-0000492 begin**********/
	//验证房间名、电器名、模式名、安防名(中、英文字母、数字)
	public final static String REGEXP_NAME="^[A-Za-z0-9\\u4e00-\\u9fa5]*$";
	/************2012-10-29  lei.peng  BUG-0000492 end**********/
	//验证安防编号(数字、英文字母)
	public final static String REGEXP_SECYRITY_CODE="^[A-Za-z0-9]+$";
	//验证密码(数字、英文字母、常用符号)除去中文及空白字符"\s"
	public final static String REGEXP_PASSWORD="^[^\\u4e00-\\u9fa5\\s]*$";
	//验证电器型号（英文字母、数字、空格含特殊符号（—、/）
	public final static String REGEXP_EQU_MODEL="^[\\s\\-\\/A-Za-z0-9]*$";
	//验证用户姓名(中文汉字)
    public final static String REGEXP_CONTANT="^[\u4e00-\u9fa5]*$";
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		Log.e("dippx", "scale"+scale);
		return (int) (pxValue / scale + 0.5f);
	}



	/**
	 * 设置EditText控件输入的长度限制及格式验证(用于房间名、电器名、模式名等包含中文字符的格式验证)
	 * @param context 上下文
	 * @param et EditText控件
	 *
	 * 
	 */
	public static void inputNameConfine(final Context context, final EditText et, final int maxlength){

		/* 2012-06-11 BUG-0002179 modify by shuntong.cheng begin*/
		final Pattern pattern = Pattern.compile(REGEXP_NAME);
		/* 2012-06-11 BUG-0002179 modify by shuntong.cheng end*/
		et.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				temp = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				/**** 2012-02-09   修改BUG-0001355 begin*****/
				int num=getBytesLength(temp);
				/**** 2012-02-09   修改BUG-0001355 end*****/

				if (num > maxlength) {
					ToastUtils.showToast(context, "您的输入已经超过长度限制");
					et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
					et.setSelection(et.getText().toString().length());

				}

				Matcher matcher = pattern.matcher(et.getText().toString());
				boolean b= matcher.matches();			
				if(!b){	
					/**********2012-06-12 lei.peng  BUG-0002214  begin***********/
					ToastUtils.showToast(context, "您只能输入中、英文、数字！");
					/**********2012-06-12 lei.peng  BUG-0002214  end***********/
					Log.e("text", et.getText().toString());
					et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
					et.setSelection(et.getText().toString().length());
				}
			}
		});

	}

	/**
	 * 设置EditText控件输入的长度限制及格式验证(用于电器型号的格式验证)
	 * @param context 上下文
	 * @param et EditText控件
	 *
	 * 
	 */
	public static void inputEquModleConfine(final Context context, final EditText et, final int maxlength){


		final Pattern pattern = Pattern.compile(REGEXP_EQU_MODEL);
		final Pattern pattern_s = Pattern.compile("^[\\s]*$");

		et.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				temp = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

				int num=getBytesLength(temp);
				String msg=et.getText().toString();
				if (num > maxlength) {
					ToastUtils.showToast(context, "您的输入已经超过长度限制!");
					et.setText(et.getText().toString().subSequence(0,msg.length()-1));
					et.setSelection(et.getText().toString().length());
					return;

				}

				if(msg.length()>0){
					//判断首尾和末尾是否是空格
					Matcher matcher_head = pattern_s.matcher(msg.substring(0, 1));
//					Matcher matcher_end = pattern_s.matcher(msg.substring(msg.length()-1, msg.length()));
					boolean b_head= matcher_head.matches();
//					boolean b_end= matcher_end.matches();
					//首字母或尾子母为空格则验证失败
					if(b_head){
						ToastUtils.showToast(context, "首字母不能为空格！");
						et.setText(msg.substring(1,msg.length()));
						et.setSelection(et.getText().toString().length());
						return;
					}
					/* 2011-11-15 BUG-0000836 modify by st.cheng begin*/
					
//					if(b_end){
//						CommonToastView.showMsg(context, "尾字母不能为空格！", CommonToastView.LENGTH_SHORT);
//						et.setText(msg.substring(0,msg.length()-1));
//						et.setSelection(et.getText().toString().length());
//						return;
//					}
					  /* 2011-11-15 BUG-0000836 modify by st.cheng end*/
					//整个文本验证
					Matcher matcher = pattern.matcher(et.getText().toString());

					boolean b= matcher.matches();			
					if(!b){	
						ToastUtils.showToast(context, "您只能输入英文、数字、空格 、“-” “/”！");
						Log.e("text", et.getText().toString());
						et.setText(msg.substring(0,msg.length()-1));
						et.setSelection(et.getText().toString().length());
					}

				}


			}
		});

	}


	/**
	 * 设置EditText控件输入的长度限制及格式验证(用于房间名、电器名、模式名等包含中文字符的格式验证)
	 * @param context 上下文
	 * @param et EditText控件
	 *
	 * 
	 */
	public static void inputPasswordConfine(final Context context, final EditText et, final int maxlength){

		/* 2012-06-11 BUG-0002179 modify by shuntong.cheng begin*/
		final Pattern pattern = Pattern.compile(REGEXP_PASSWORD);
		/* 2012-06-11 BUG-0002179 modify by shuntong.cheng end*/
		et.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				temp = s;

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				int num=getBytesLength(temp);
				if (num > maxlength) {
					ToastUtils.showToast(context, "您的输入已经超过长度限制！");
					et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
					et.setSelection(et.getText().toString().length());

				}
				Log.e("sgcc", "input="+temp);
				Log.e("sgcc", "text="+et.getText().toString());
				Matcher matcher = pattern.matcher(et.getText().toString());
				boolean b= matcher.matches();			
				if(!b){	
					ToastUtils.showToast(context, "您只能输入英文、数字、常用符号！");
					Log.e("text", et.getText().toString());
					et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
					et.setSelection(et.getText().toString().length());
				}
			}
		});
	}


	/**
	 * 设置EditText控件输入的长度限制及格式验证，用于验证家庭地址
	 * @param context 上下文
	 * @param et EditText控件
	 * @param maxlength 字符长度限制一个中文占两个字符
	 * 
	 */
	public static void inputAddressConfine(final Context context, final EditText et, final int maxlength){

		final Pattern pattern = Pattern.compile(REGEXP_ADDRESS);

		et.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				temp = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				//获取字符串字符长度（中文==2） 
				int num=getBytesLength(temp);

				if (num > maxlength) {
					ToastUtils.showToast(context, "您的输入已经超过长度限制");
					et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
					et.setSelection(et.getText().toString().length());					
				}

				Matcher matcher = pattern.matcher(et.getText().toString());
				boolean b= matcher.matches();			
				if(!b){	
					ToastUtils.showToast(context, "您只能输入中英文数字及");
					Log.e("text", et.getText().toString());
					et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
					et.setSelection(et.getText().toString().length());
				}
			}
		});

	}

	/**
	 * 设置EditText控件输入的长度限制及格式验证(用于用户名格式验证)
	 * @param context 上下文
	 * @param et EditText控件
	 * @param maxlength 长度限制
	 * 
	 */
	public static void inputUserNameConfine(final Context context, final EditText et, final int maxlength){
		final Pattern pattern = Pattern.compile(REGEXP_USERNAME);

		et.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				temp = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {

				/**** 2012-02-09   修改BUG-0001355 start*****/

				if (temp.length() > maxlength) {
					/**** 2012-02-09   修改BUG-0001355 end*****/
					ToastUtils.showToast(context, "您的输入已经超过长度限制！");
					et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
					et.setSelection(et.getText().toString().length());

				}
				if(et.getText().toString().trim()!=null&&!et.getText().toString().trim().equals(""))
				{	

					Log.e("sgcc", "head ="+et.getText().toString().trim().substring(0, 1));
//					if(!isLetter(et.getText().toString().trim().substring(0, 1)))
//					{
////						CommonToastView.showMsg(context, "首位必须是字母！", CommonToastView.LENGTH_SHORT);
//						ToastUtils.showToast(context, "首位必须是字母！");
//						et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
//						et.setSelection(et.getText().toString().length());
//					}
					Matcher matcher = pattern.matcher(et.getText().toString());
					boolean b= matcher.matches();			
					if(!b){	
//						CommonToastView.showMsg(context, "您只能输入英文、数字及下划线！", CommonToastView.LENGTH_SHORT);
						
//						ToastUtils.showToast(context, context.getString(R.string.zsdl_input_only_checking4));
						
						Log.e("text", et.getText().toString());
						et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
						et.setSelection(et.getText().toString().length());
					}
				}
			}
		});

	}
	/**
	 * 设置EditText控件输入的长度限制及格式验证(用于用户姓名格式验证)
	 * @param context 上下文
	 * @param et EditText控件
	 * @param maxlength 长度限制
	 *
	 */
	public static void inputContactConfine(final Context context, final EditText et, final int maxlength){
		final Pattern pattern = Pattern.compile(REGEXP_CONTANT);

		et.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				temp = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {

				/**** 2012-02-09   修改BUG-0001355 start*****/

				if (temp.length() > maxlength) {
					/**** 2012-02-09   修改BUG-0001355 end*****/
//					CommonToastView.showMsg(context, "您的输入已经超过长度限制！", CommonToastView.LENGTH_SHORT);
					//ToastUtils.showToast(context, context.getString(R.string.zsdl_input_exceed_length));
					et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
					et.setSelection(et.getText().toString().length());

				}
				if(et.getText().toString().trim()!=null&&!et.getText().toString().trim().equals(""))
				{	

					Log.e("sgcc", "head ="+et.getText().toString().trim().substring(0, 1));
//					if(!isLetter(et.getText().toString().trim().substring(0, 1)))
//					{
////						CommonToastView.showMsg(context, "首位必须是字母！", CommonToastView.LENGTH_SHORT);
//						ToastUtils.showToast(context, "首位必须是字母！");
//						et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
//						et.setSelection(et.getText().toString().length());
//					}
					Matcher matcher = pattern.matcher(et.getText().toString());
					boolean b= matcher.matches();			
					if(!b){	
//						CommonToastView.showMsg(context, "您只能输入英文、数字及下划线！", CommonToastView.LENGTH_SHORT);
						
					//	ToastUtils.showToast(context, context.getString(R.string.zsdl_input_only_checking6));
						
						Log.e("text", et.getText().toString());
						et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
						et.setSelection(et.getText().toString().length());
					}
				}
			}
		});

	}

	/**
	 * 设置EditText控件输入的长度限制及格式验证(用于安防编号验证)
	 * @param context 上下文
	 * @param et EditText控件
	 * @param maxlength 长度限制
	 * 
	 */
	public static void inputSrcurityCodeConfine(final Context context, final EditText et, final int maxlength){
		final Pattern pattern = Pattern.compile(REGEXP_SECYRITY_CODE);

		et.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				temp = s;

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

				if (temp.length() > maxlength) {

					ToastUtils.showToast(context, "您的输入已经超过长度限制！");
					et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
					et.setSelection(et.getText().toString().length());

				}
				if(et.getText().toString().trim()!=null&&!et.getText().toString().trim().equals(""))
				{	

					Matcher matcher = pattern.matcher(et.getText().toString());
					boolean b= matcher.matches();			
					if(!b){	
						ToastUtils.showToast(context, "您只能输入英文、数字！");

						Log.e("text", et.getText().toString());
						et.setText(et.getText().toString().subSequence(0,et.getText().toString().length()-1));
						et.setSelection(et.getText().toString().length());
					}
				}
			}
		});

	}


	/**
	 * 判断输入的字符是否为中文
	 * **/
	public static boolean isChinese(char c) {
		String regEx = "[\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(c + "");
		return m.find();
	}
	/**** 2012-02-09   修改BUG-0001321 start*****/
	/**
	 * 判断输入的字符是否为字母
	 * **/
	public static boolean isLetter(String c) {
		String regEx = "[A-Za-z]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(c );
		return m.find();
	}
	/**** 2012-02-09  修改BUG-0001321 end*****/

	/**
	 * 获取字符长度一个中文占两个字符
	 * @param Str
	 * @return
	 */
	public static final int getBytesLength(String Str) {
		int byteslength = 0;
		for (int i = 0; i < Str.length(); i++) {
			char ch = Str.charAt(i);
			short chint = (short) ch;
			if ((chint >= 48) && (chint <= 57)) {
				// 这个字符是数字
				byteslength++;
			} else if (((chint >= 91) && (chint <= 122))
					|| ((chint >= 65) && (chint <= 90)) || (chint == 95)) {
				// 这个字符是英文或者是 "_ "
				byteslength++;
			} else if (((chint & 0XFF00) >> 8) > 0) {
				// 这个字符是中文
				byteslength++;
				byteslength++;
			}else{
				//特殊字符
				byteslength++;
			}
		}
		return byteslength;
	}
	/**
	 * 获取字符长度一个中文占两个字符
	 * @param Str
	 * @return
	 */
	public static final int getBytesLength(CharSequence Str) {
		int byteslength = 0;
		for (int i = 0; i < Str.length(); i++) {
			char ch = Str.charAt(i);
			short chint = (short) ch;
			if ((chint >= 48) && (chint <= 57)) {
				// 这个字符是数字
				byteslength++;
			} else if (((chint >= 91) && (chint <= 122))
					|| ((chint >= 65) && (chint <= 90)) || (chint == 95)) {
				// 这个字符是英文或者是 "_ "
				byteslength++;
			} else if (((chint & 0XFF00) >> 8) > 0) {
				// 这个字符是中文
				byteslength++;
				byteslength++;
			}else{
				//特殊字符
				byteslength++;
			}
		}
		return byteslength;
	}
	
	
	
	/**
	 * 定义从右侧进入的动画效果
	 * @return
	 */
	public static Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(500);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	/**
	 * 定义从左侧退出的动画效果
	 * @return
	 */
	public static Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	/**
	 * 定义从左侧进入的动画效果
	 * @return
	 */
	public static Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(500);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	/**
	 * 定义从右侧退出时的动画效果
	 * @return
	 */
	public static Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoRight.setDuration(500);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}

	/**
	 * 将图片适配屏幕宽度
	 * @param activity
	 * @param bitmap 原图片
	 * @return 等比例放大适配宽度的图片
	 */
	public static Bitmap setScaleBitmap(android.app.Activity activity, Bitmap bitmap){
		Display display = activity.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();//屏幕宽度
		float scaleWidte = (float)width/(float)bitmap.getWidth();//缩放比例
		//缩放动作
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidte, scaleWidte);
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		return bitmap2;
	}
	
	/**
	 * 适配不同机型卡表购电界面图片展示大小问题
	 * @param activity
	 * @param bitmap
	 * @return
	 */
	public static Bitmap setSurfaceBitmap(android.app.Activity activity, Bitmap bitmap){
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) activity
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		display.getMetrics(dm);
		float SCREEN_DENSITY = dm.density;
		int SCREEN_WIDTH = dm.widthPixels; 
		int SCREEN_HEIGHT = dm.heightPixels;

		float scaleWidte = (float) SCREEN_WIDTH / (float) 720;//缩放比例
		float scaleHeight = (float) SCREEN_HEIGHT / (float) 1280;//缩放比例
		float scale = (float) SCREEN_HEIGHT/ (SCREEN_DENSITY*640);//高度
		//缩放动作
		Matrix matrix = new Matrix();
//		matrix.postScale(1, scaleHeight);
		matrix.postScale(scale, scale);
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		return bitmap2;
		
	}
}
