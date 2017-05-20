package com.example.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {
	public static final String tag = "GuideActivity";
	private ViewPager view_pager;
	private Button button;
	private List<ImageView> arrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		
		view_pager = (ViewPager) findViewById(R.id.view_pager);
		button = (Button) findViewById(R.id.button);
		
		//填充一个包含了数据的集合
		initData();
		//设置数据适配器
		view_pager.setAdapter(new MyPagerAdapter());
		//当选中最后一页的时候操作
		view_pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				//选中某一页的方法
				if(arg0 == arrayList.size()-1){
					//按钮显示
					button.setVisibility(View.VISIBLE);
					button.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(GuideActivity.this, HomeActivity.class);
							startActivity(intent);
							
							//跳转过后需要去关闭当前的activity
							finish();
						}
					});
				}else{
					button.setVisibility(View.GONE);//不可见，而且不占用位置
//					button.setVisibility(View.INVISIBLE);//不可见，占用位置
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
	
	private void initData() {
		arrayList = new ArrayList<ImageView>();
		
		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setBackgroundResource(R.drawable.guide_1);
		
		ImageView imageView1 = new ImageView(getApplicationContext());
		imageView1.setBackgroundResource(R.drawable.guide_2);
		
		ImageView imageView2 = new ImageView(getApplicationContext());
		imageView2.setBackgroundResource(R.drawable.guide_3);
		
		arrayList.add(imageView);
		arrayList.add(imageView1);
		arrayList.add(imageView2);
	}

	class MyPagerAdapter extends PagerAdapter{
		@Override
		public int getCount() {
			return arrayList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		//向viewpager添加一个界面的方法
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Log.i(tag, "instantiateItem position ="+position);
			container.addView(arrayList.get(position));
			return arrayList.get(position);
		}
		
		//从viewpager中移除一个view的操作
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			Log.i(tag, "destroyItem position ="+position);
			container.removeView((View)object);
		}
	}
}
