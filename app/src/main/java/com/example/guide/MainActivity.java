package com.example.guide;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity {
	protected static final String tag = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		final SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
		
		//发送一个颜色的消息
		new Handler(){
			public void handleMessage(android.os.Message msg) {
				//接受到消息后的处理
				boolean b = sharedPreferences.getBoolean("is_first",true);
				
				if(b){
					//第一次进入应用，进入导航界面
					sharedPreferences.edit().putBoolean("is_first", false).commit();
					Intent intent = new Intent(MainActivity.this,GuideActivity.class);
					startActivity(intent);
					Log.i(tag, "是第一次进入");
				}else{
					//不是第一次进入应用,直接跳转到主界面
					Log.i(tag, "是第二次进入");
					Intent intent = new Intent(MainActivity.this,HomeActivity.class);
					startActivity(intent);
				}
				
				finish();
			};
		}.sendEmptyMessageDelayed(0, 3000);
	}
}
