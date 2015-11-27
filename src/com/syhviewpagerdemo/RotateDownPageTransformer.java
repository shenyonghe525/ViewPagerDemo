package com.syhviewpagerdemo;

import android.support.v4.view.ViewPager;
import android.view.View;

public class RotateDownPageTransformer implements ViewPager.PageTransformer {

	private static final float MAX_ROTATE = 20f;

	private float mRot;

	/**
	 * ��Aҳ�л���Bҳ��A��position:0.0 ~ -1;B��position:1.0 ~ 0.0��
	 * С��1�Ĳ�������Ļ��಻�ɼ�������1�Ĳ�������Ļ�Ҳ಻�ɼ�
	 */
	// A�ĽǶȱ仯0.0��~-20�ȣ�B�ĽǶȱ仯20��~0.0�ȡ�
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		if (position < -1) { // [-Infinity,-1)
			view.setRotation(0);
			view.setAlpha(0);

		} else if (position <= 0) { // [-1,0]
			// �Ƕȱ仯0.0��~-20��
			mRot = position * MAX_ROTATE;
			view.setPivotX(pageWidth / 2);
			view.setPivotY(view.getMeasuredHeight());
			view.setRotation(mRot);
			// ͸���ȱ仯
			view.setAlpha(1);

		} else if (position <= 1) { // (0,1]
			// �Ƕȱ仯20��~0.0��
			mRot = position * MAX_ROTATE;
			view.setPivotX(pageWidth / 2);
			view.setPivotY(view.getMeasuredHeight());
			view.setRotation(mRot);
			// ͸���ȱ仯
			view.setAlpha(1 - position);

		} else { // (1,+Infinity]
			view.setRotation(0);
			view.setAlpha(0);
		}
	}

}
