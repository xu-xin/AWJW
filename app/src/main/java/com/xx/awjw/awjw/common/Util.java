package com.xx.awjw.awjw.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xx.awjw.awjw.R;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuxin
 */
public class Util {
	public static ImageLoader imageLoader = ImageLoader.getInstance();
	public static DisplayImageOptions image_display_options = new DisplayImageOptions.Builder()
			.showImageOnLoading(android.R.color.black)
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			// .displayer(new RoundedBitmapDisplayer(5))
			.cacheInMemory(true).cacheOnDisc(true).build();

	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}

	// 获取图片所在文件夹名称
	public static String getDir(String path) {
		String subString = path.substring(0, path.lastIndexOf('/'));
		return subString.substring(subString.lastIndexOf('/') + 1,
				subString.length());
	}

	public static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {
		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

//	public static String getNetData(FinalHttp finalhttp, AjaxParams params,
//			String address,final Context context) {
//		finalhttp.post(address, params, new AjaxCallBack<Object>() {
//			@Override
//			public void onLoading(long count, long current) {
//			}
//
//			@Override
//			public void onSuccess(Object t) {
//				android.util.Log.i("xx", t.toString());
//				if(t.toString().equals("0")){
///*					Intent intent=new Intent(context,MainActivity.class);
//					context.startActivity(intent);*/
//					Toast.makeText(context, R.string.add_topic_success, Constants.MIDDLE_SHOW_TIME).show();
//				}else if(t.toString().equals("1")){
//					Toast.makeText(context, R.string.add_topic_failed, Constants.MIDDLE_SHOW_TIME).show();
//				}
//			}
//
//		});
//		return address;
//	}
	
	//根据表情名字转换为对应id
		private static int getImageId(String fileName) {
			Field field;
			try {
				field = R.drawable.class.getField(fileName);
				Log.v("Integer.valueOf", "Integer.valueOf(field.getInt(fileName))");
				return Integer.valueOf(field.getInt(fileName));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return (Integer) null;
		}
		
		public static SpannableString showExpression(String content, Context context){

			Pattern pattern = Pattern.compile("(\\[#[0-9]{1,2}\\])");
			Matcher matcher = pattern.matcher(content);
			SpannableString ss = new SpannableString(content);
			while (matcher.find()) {
				String str = matcher.group();// [#12]
				Log.v("matcher_GROUP_str", str);
				Pattern p = Pattern.compile("(\\d+)");
				Matcher m = p.matcher(str);
				String expressionName = null;
				while (m.find()) {
					expressionName = "e_" + m.group();
					Log.v("M_GROUP_str", expressionName);
				}
				Drawable drawable = context.getResources().getDrawable(getImageId(expressionName));    
	            drawable.setBounds(0, 0, 45, 45);//这里设置图片的大小    
	            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
				//将表情字符替换为表情
						ss.setSpan(imageSpan,
						matcher.start(), matcher.end(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			return ss;
		}
}
