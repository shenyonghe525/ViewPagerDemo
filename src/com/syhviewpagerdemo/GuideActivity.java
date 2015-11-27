package com.syhviewpagerdemo;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.networkbench.agent.impl.NBSAppAgent;

/**
 * 
 * @author shenyonghe
 * 
 *         2015-11-26
 */
public class GuideActivity extends Activity implements OnPageChangeListener {

	private ViewPager mViewPager;
	private List<View> mViews;
	private ImageView[] mIvPoint;
	private int[] mPointIds = { R.id.iv_point1, R.id.iv_point2, R.id.iv_point3 };

	private Button mJoinBtn;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		NBSAppAgent.setLicenseKey("50ec84b7bd81479aaf073cd7e7e413bb")
				.withLocationServiceEnabled(true).start(this);
		initViews();
	}

	private void initViews()
	{
		initPoints();
		mViews = new ArrayList<View>();
		LayoutInflater inflater = LayoutInflater.from(this);
		mViews.add(inflater.inflate(R.layout.page1, null));
		mViews.add(inflater.inflate(R.layout.page2, null));
		mViews.add(inflater.inflate(R.layout.page3, null));
		mViewPager = (ViewPager) findViewById(R.id.guid_viewPager);
		mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
		mViewPager.setAdapter(new MyPageAdapter(this, mViews));
		mViewPager.setOnPageChangeListener(this);

		mJoinBtn = (Button) mViews.get(2).findViewById(R.id.btn_join);

		mJoinBtn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(GuideActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private void initPoints()
	{
		mIvPoint = new ImageView[3];
		for (int i = 0; i < mIvPoint.length; i++)
		{
			mIvPoint[i] = (ImageView) findViewById(mPointIds[i]);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{

	}

	@Override
	public void onPageSelected(int arg0)
	{
		for (int i = 0; i < mPointIds.length; i++)
		{
			if (arg0 == i)
			{
				mIvPoint[i].setImageResource(R.drawable.login_point_selected);
			} else
			{
				mIvPoint[i].setImageResource(R.drawable.login_point);
			}
		}
	}
}
