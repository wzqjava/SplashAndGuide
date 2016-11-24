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
		
		//����һ����ɫ����Ϣ
		new Handler(){
			public void handleMessage(android.os.Message msg) {
				//���ܵ���Ϣ��Ĵ���
				boolean b = sharedPreferences.getBoolean("is_first",true);
				
				if(b){
					//��һ�ν���Ӧ�ã����뵼������
					sharedPreferences.edit().putBoolean("is_first", false).commit();
					Intent intent = new Intent(MainActivity.this,GuideActivity.class);
					startActivity(intent);
					Log.i(tag, "�ǵ�һ�ν���");
				}else{
					//���ǵ�һ�ν���Ӧ��,ֱ����ת��������
					Log.i(tag, "�ǵڶ��ν���");
					Intent intent = new Intent(MainActivity.this,HomeActivity.class);
					startActivity(intent);
				}
				
				finish();
			};
		}.sendEmptyMessageDelayed(0, 3000);
	}
}
