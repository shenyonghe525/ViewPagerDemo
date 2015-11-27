package com.syhviewpagerdemo;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * 
 * @author shenyonghe
 *
 * 2015-11-26
 */
class MyPageAdapter extends PagerAdapter {

	private  GuideActivity mainActivity;
	List<View> mViews = new ArrayList<View>();

	/**
	 * @param mainActivity
	 */
	MyPageAdapter(GuideActivity mainActivity,List<View> views)
	{
		this.mainActivity = mainActivity;
		this.mViews = views;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mViews.get(position));
		return mViews.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position,
			Object object) {
		container.removeView(mViews.get(position));
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public int getCount() {
		return mViews.size();
	}
}