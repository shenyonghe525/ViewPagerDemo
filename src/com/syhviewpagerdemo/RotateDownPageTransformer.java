package com.syhviewpagerdemo;

import android.support.v4.view.ViewPager;
import android.view.View;

public class RotateDownPageTransformer implements ViewPager.PageTransformer {

	private static final float MAX_ROTATE = 20f;

	private float mRot;

	/**
	 * 从A页切换到B页，A的position:0.0 ~ -1;B的position:1.0 ~ 0.0。
	 * 小于1的部分在屏幕左侧不可见，大于1的部分在屏幕右侧不可见
	 */
	// A的角度变化0.0度~-20度，B的角度变化20度~0.0度。
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		if (position < -1) { // [-Infinity,-1)
			view.setRotation(0);
			view.setAlpha(0);

		} else if (position <= 0) { // [-1,0]
			// 角度变化0.0度~-20度
			mRot = position * MAX_ROTATE;
			view.setPivotX(pageWidth / 2);
			view.setPivotY(view.getMeasuredHeight());
			view.setRotation(mRot);
			// 透明度变化
			view.setAlpha(1);

		} else if (position <= 1) { // (0,1]
			// 角度变化20度~0.0度
			mRot = position * MAX_ROTATE;
			view.setPivotX(pageWidth / 2);
			view.setPivotY(view.getMeasuredHeight());
			view.setRotation(mRot);
			// 透明度变化
			view.setAlpha(1 - position);

		} else { // (1,+Infinity]
			view.setRotation(0);
			view.setAlpha(0);
		}
	}

}
