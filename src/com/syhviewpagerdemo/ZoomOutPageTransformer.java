package com.syhviewpagerdemo;

import com.nineoldandroids.view.ViewHelper;

import android.view.View;
import android.support.v4.view.ViewPager;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
	private static final float MIN_SCALE = 0.85f;
	private static final float MIN_ALPHA = 0.5f;

	/**
	 * 从A页切换到B页，A的position:0.0 ~ -1;B的position:1.0 ~ 0.0。
	 * 小于1的部分在屏幕左侧不可见，大于1的部分在屏幕右侧不可见
	 */
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		int pageHeight = view.getHeight();
		if (position < -1) { // [-Infinity,-1)
			// This page is way off-screen to the left.
//			view.setAlpha(0);
			// 3.0以下实现
			ViewHelper.setAlpha(view, 0);
		} else if (position <= 1) { // [-1,1]
			// Modify the default slide transition to shrink the page as well
			float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
			float vertMargin = pageHeight * (1 - scaleFactor) / 2;
			float horzMargin = pageWidth * (1 - scaleFactor) / 2;
			if (position < 0) {
				// view.setTranslationX(horzMargin - vertMargin / 2);
				// 3.0以下实现
				ViewHelper.setTranslationX(view, horzMargin - vertMargin / 2);
			} else {
				// view.setTranslationX(-horzMargin + vertMargin / 2);
				// 3.0以下实现
				ViewHelper.setTranslationX(view, -horzMargin + vertMargin / 2);
			}
			// Scale the page down (between MIN_SCALE and 1)
			// view.setScaleX(scaleFactor);
			// view.setScaleY(scaleFactor);
			// // Fade the page relative to its size.
			// view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
			// / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
			// 3.0以下实现
			ViewHelper.setScaleX(view, scaleFactor);
			ViewHelper.setScaleY(view, scaleFactor);
			ViewHelper.setAlpha(view, MIN_ALPHA + (scaleFactor - MIN_SCALE)
					/ (1 - MIN_SCALE) * (1 - MIN_ALPHA));
		} else { // (1,+Infinity]
			// This page is way off-screen to the right.
			// view.setAlpha(0);

			// 3.0以下实现
			ViewHelper.setAlpha(view, 0);
		}
	}
}