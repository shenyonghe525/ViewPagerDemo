package com.syhviewpagerdemo;

import com.nineoldandroids.view.ViewHelper;

import android.view.View;
import android.support.v4.view.ViewPager;

public class DepthPageTransformer implements ViewPager.PageTransformer {
	private static final float MIN_SCALE = 0.75f;

	/**
	 * ��Aҳ�л���Bҳ��A��position:0.0 ~ -1;B��position:1.0 ~ 0.0��
	 * С��1�Ĳ�������Ļ��಻�ɼ�������1�Ĳ�������Ļ�Ҳ಻�ɼ�
	 */
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		if (position < -1) { // [-Infinity,-1)
			// This page is way off-screen to the left.
			// view.setAlpha(0);

			// 3.0����ʵ��
			ViewHelper.setAlpha(view, 0);
			
		} else if (position <= 0) { // [-1,0]
			// Use the default slide transition when moving to the left page
			// view.setAlpha(1);
			// view.setTranslationX(0);
			// view.setScaleX(1);
			// view.setScaleY(1);

			// 3.0����ʵ��
			ViewHelper.setAlpha(view, 1);
			ViewHelper.setTranslationX(view, 0);
			ViewHelper.setScaleX(view, 1);
			ViewHelper.setScaleY(view, 1);

		} else if (position <= 1) { // (0,1]
			// Scale the page down (between MIN_SCALE and 1)
			float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
					* (1 - Math.abs(position));
			// Fade the page out.
			// view.setAlpha(1 - position);
			// // Counteract the default slide transition
			// view.setTranslationX(pageWidth * -position);
			// view.setScaleX(scaleFactor);
			// view.setScaleY(scaleFactor);

			// 3.0����ʵ��
			ViewHelper.setAlpha(view, 1 - position);
			ViewHelper.setTranslationX(view, pageWidth * -position);
			ViewHelper.setScaleX(view, scaleFactor);
			ViewHelper.setScaleY(view, scaleFactor);

		} else { // (1,+Infinity]
			// This page is way off-screen to the right.
//			view.setAlpha(0);

			// 3.0����ʵ��
			ViewHelper.setAlpha(view, 0);

		}
	}
}