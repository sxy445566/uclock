package com.sxy.uclock.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

/**********************************************
 * @类名: ModelUtils
 * 
 * @描述: 公用方法类
 * 
 * @作者： SXY
 * 
 * @版本： V1.0
 * 
 * @修订历史：
 * 
 ***********************************************/
@SuppressLint({ "DefaultLocale", "InflateParams" })
public class ModelUtils {
	/**
	 * @function 检测网络是否可用
	 * @param context
	 *            上下文对象
	 * @return true：可用， false：不可用
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * @function 判断WIFI网络是否可用
	 * 
	 * @param context
	 *            上下文对象
	 * @return true：可用 ，false：不可用
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	/**
	 * @function 获取当前网络类型
	 * @param context
	 *            上下文对象
	 * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
	 */
	public static int getNetworkType(Context context) {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if (!TextUtils.isEmpty(extraInfo)) {
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = NETTYPE_CMNET;
				} else {
					netType = NETTYPE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NETTYPE_WIFI;
		}
		return netType;
	}
	/** * 双击退出函数 */
	private static Boolean isExit = false;

	public static void exitBy2Click(Context context) {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(context, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出z
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
		} else {
			System.exit(0);
		}

	}
	/**
	 * @function 获取屏幕宽度
	 * @param res
	 *            资源类，可以通过getResources得到
	 * @return 屏幕宽度（px）
	 */
	public static int getScreenWidth(Resources res) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = res.getDisplayMetrics();
		return dm.widthPixels;
	}

	/**
	 * @function 获取屏幕高度
	 * @param res
	 *            资源类，可以通过getResources得到
	 * @return 屏幕宽度（px）
	 */
	public static int getScreenHeight(Resources res) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = res.getDisplayMetrics();
		return dm.heightPixels;
	}

	/**
	 * 设控件高度
	 * 
	 */
	public static void setViewHeight(View view, Resources res) {
		int height = getScreenHeight(res) / 14;
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view
				.getLayoutParams();
		linearParams.height = height;
	}

	/**
	 * Function 根据图片名从Assets中读取图片（未压缩）
	 * 
	 * @param fileName
	 *            需要获取的图片名
	 * @param context
	 *            上下文对象
	 * @return 图片名对应的位图
	 */
	public static Bitmap getImageFromAssetsFile(String fileName, Context context) {
		Bitmap image = null;
		AssetManager am = context.getAssets();
		try {
			InputStream is = am.open(fileName);
			image = BitmapFactory.decodeStream(is).copy(
					Bitmap.Config.ARGB_8888, true);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * 判断是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumer(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 往Shared中存数据的方法
	 * 
	 * @param context
	 *            上下文对象
	 * @param filename
	 *            文件名字
	 * @param key
	 *            存放数据的key
	 * @param value
	 *            存放数据的value
	 */
	public static void setShareData(Context context, String filename,
			String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(filename,
				Context.MODE_PRIVATE);
		// SharedPreferences sp = context.getSharedPreferences("PersonInfo",
		// Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 往Shared中存数据的方法
	 * 
	 * @param context
	 *            上下文对象
	 * @param filename
	 *            文件名字
	 */
	public static void setShareData(Context context, String filename,
			Map<String, String> map) {
		SharedPreferences sp = context.getSharedPreferences(filename,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			editor.putString(entry.getKey(), entry.getValue());
		}
		editor.commit();
	}

	/**
	 * 取得Shared中数据的方法
	 * 
	 * @param key
	 *            存放数据的名字
	 * @param context
	 *            上下文对象
	 * @param shareName
	 *            share文件名
	 */
	public static String getShareData(Context context, String shareName,
			String key) {
		SharedPreferences sp = context.getSharedPreferences(shareName,
				Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}

	/**
	 * 取得Shared中数据的方法(取得默认值)
	 * 
	 * @param key
	 *            存放数据的键
	 * @param context
	 *            上下文对象
	 * @param content
	 *            默认值
	 * @param shareName
	 *            share文件名
	 */
	public static String getShare(Context context, String key, String content,
			String shareName) {
		SharedPreferences sp = context.getSharedPreferences(shareName,
				Context.MODE_PRIVATE);
		return sp.getString(key, content);
	}

	/**
	 * 清空share
	 * 
	 * @param context
	 *            上下文
	 * @param shareName
	 *            share文件的名字
	 */
	public static void cleanShare(Context context, String shareName) {
		SharedPreferences sp = context.getSharedPreferences(shareName,
				Context.MODE_PRIVATE);
		sp.edit().clear().commit();
	}

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	private static long lastClickTime;

	/**
	 * @方法名: isFastClick
	 * @方法描述: 判断是否是快速点击
	 * @return： boolean
	 */
	public static boolean isFastClick() {
		long time = System.currentTimeMillis();
		if (time - lastClickTime < 1000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径 [url=home.php?mod=space&uid=309376]@return[/url]
	 *            degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	public static Bitmap rotateBitMap(Bitmap bp) {
		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postRotate(90);
		Bitmap nowBp = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(),
				bp.getHeight(), matrix, true);
		if (bp.isRecycled()) {
			bp.recycle();
			System.gc();
		}
		return nowBp;
	}

	public static Bitmap rotateBitMap(Bitmap bp, int rotate) {
		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postRotate(rotate);
		Bitmap nowBp = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(),
				bp.getHeight(), matrix, true);
		if (bp.isRecycled()) {
			bp.recycle();
			bp = null;
			System.gc();
		}
		return nowBp;
	}

	

	/**
	 * 压缩图片大小并获得压缩后的图片
	 * 
	 * @param outFilepath 压缩后的图片路径
	 *            inFilepath需要压缩的图片路径 width压缩后的宽度 height压缩后的高度
	 * @return file压缩后的图片
	 */
	public static File getSimplePicFile(String outFilepath, String inFilepath,
			int width, int height) {
		if (saveBitmap(getSmallBitmap(inFilepath, width, height), outFilepath)) {
			return new File(outFilepath);
		}
		return null;
	}

	/**
	 * 获取压缩图片的比例
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 获取压缩后的图片
	 * 
	 * @param filePath
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap getSmallBitmap(String filePath, int width, int height) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		options.inSampleSize = calculateInSampleSize(options, width, height);

		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}

	/**
	 * 保存图片到指定的路径
	 * 
	 * @param bm
	 * @param outFilepath
	 * @return
	 */
	public static boolean saveBitmap(Bitmap bm, String outFilepath) {
		boolean bool = false;
		File f = new File(outFilepath);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
			bool = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * @function 拷贝assets文件夹中的文件到制定路径
	 * @param context
	 * @param dir
	 * @param fileName
	 */
	public static void CopyAssets(Context context, String dir, String fileName) {
		File mWorkingPath = new File(dir);
		if (!mWorkingPath.exists()) {
			if (!mWorkingPath.mkdirs()) {
				Log.e("--CopyAssets--", "cannot create directory.");
			}
		}
		try {
			InputStream in = context.getResources().getAssets().open(fileName);
			System.err.println("");
			File outFile = new File(mWorkingPath, fileName);
			OutputStream out = new FileOutputStream(outFile);
			// Transfer bytes from in to out
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean stringIsNull(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

//	/**
//	 * 打电话方法
//	 *
//	 * @param context
//	 */
//	public static void callPhone(final Context context, final String phoneNum) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setTitle("确定拨打电话？");
//		builder.setNegativeButton("取消", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//		builder.setPositiveButton("确定", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
//						+ phoneNum));
//				context.startActivity(intent);
//			}
//		});
//		builder.show();
//	}
//
//	/**
//	 * 打开系统短信，发送短信
//	 *
//	 * @param context
//	 * @param phoneNum
//	 * @param message
//	 */
//	public static void sendSMS(Context context, String phoneNum, String message)
//
//	{
//
//		Uri smsToUri = Uri.parse("smsto:" + phoneNum);
//		Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
//		intent.putExtra("sms_body", message);
//		context.startActivity(intent);
//
//	}
//
//	/**
//	 * 直接发送短信
//	 *
//	 * @param phoneNum
//	 * @param content
//	 */
//	public static void sendSMS(String phoneNum, String content) {
//
//		SmsManager smsManager = SmsManager.getDefault();
//		if (content.length() >= 70) {
//			// 短信字数大于70，自动分条
//			List<String> ms = smsManager.divideMessage(content);
//
//			for (String str : ms) {
//				// 短信发送
//				smsManager.sendTextMessage(phoneNum, null, str, null, null);
//			}
//		} else {
//			smsManager.sendTextMessage(phoneNum, null, content, null, null);
//		}
//	}

	/**
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
//	public static Dialog createLoadingDialog(Context context, String msg) {
//
//		LayoutInflater inflater = LayoutInflater.from(context);
//		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
//		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
//		// main.xml中的ImageView
//		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
//		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
//		// 加载动画
//		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
//				context, R.anim.load_animation);
//		// 使用ImageView显示动画
//		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
//		tipTextView.setText(msg);// 设置加载信息
//
//		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
//
//		loadingDialog.setCancelable(false);// 不可以用“返回键”取消
//		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.MATCH_PARENT,
//				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
//		return loadingDialog;
//
//	}

	/**
	 * 验证密码为6-11位字母或数字
	 * 
	 * @param pw
	 * @return
	 */
	public static boolean isPassword(String pw) {
		String pwRegex = "[a-z0-9]{6,11}";
		if (TextUtils.isEmpty(pw))
			return false;
		else
			return pw.matches(pwRegex);
	}

//	/**
//	 * 验证身份证号格式
//	 *
//	 * @param id
//	 * @return
//	 */
//	public static boolean isCardID(String id) {
//		String cardIDRegex = "([0-9]{17}([0-9]|X))|([0-9]{15})";
//		if (TextUtils.isEmpty(id))
//			return false;
//		else
//			return id.matches(cardIDRegex);
//	}
//
//	/**
//	 * 验证银行卡号格式
//	 *
//	 * @param id
//	 * @return
//	 */
//	public static boolean isBankID(String id) {
//		String bankIDRegex = "[0-9]{16}|[0-9]{19}";
//		if (TextUtils.isEmpty(id))
//			return false;
//		else {
//			if (id.matches(bankIDRegex)) {
//				return checkBankCard(id);
//			} else {
//				return false;
//			}
//		}
//	}
//
//	/**
//	 * 校验银行卡卡号
//	 *
//	 * @param cardId
//	 * @return
//	 */
//	public static boolean checkBankCard(String cardId) {
//		char bit = getBankCardCheckCode(cardId
//				.substring(0, cardId.length() - 1));
//		return cardId.charAt(cardId.length() - 1) == bit;
//	}
//
//	/**
//	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
//	 *
//	 * @param nonCheckCodeCardId
//	 * @return
//	 */
//	public static char getBankCardCheckCode(String nonCheckCodeCardId) {
//		if (nonCheckCodeCardId == null
//				|| nonCheckCodeCardId.trim().length() == 0
//				|| !nonCheckCodeCardId.matches("\\d+")) {
//			throw new IllegalArgumentException("Bank card code must be number!");
//		}
//		char[] chs = nonCheckCodeCardId.trim().toCharArray();
//		int luhmSum = 0;
//		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
//			int k = chs[i] - '0';
//			if (j % 2 == 0) {
//				k *= 2;
//				k = k / 10 + k % 10;
//			}
//			luhmSum += k;
//		}
//		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
//	}
//
//	/**
//	 * 校验车牌号
//	 *
//	 * @param cardId
//	 * @return
//	 */
//	public static boolean checkCarNumber(String carNumber) {
//		String carNumberRegex = "[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5}";
//		if (TextUtils.isEmpty(carNumber))
//			return false;
//		else {
//			return carNumber.matches(carNumberRegex);
//		}
//	}

	/**
	 * 校验字符串是否为数字和字母组合
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean checkIsNumberAndLetter(String str) {
		String regex = "^[A-Za-z0-9]+$";
		if (TextUtils.isEmpty(str))
			return false;
		else {
			return str.matches(regex);
		}
	}
}
