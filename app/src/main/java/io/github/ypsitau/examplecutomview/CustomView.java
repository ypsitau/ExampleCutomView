package io.github.ypsitau.examplecutomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {
	private int status;
	private Paint paint;
	private Rect rect;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Init();
	}

	private void Init() {
		status = 0;
		paint = new Paint();
		paint.setColor(getColor());
		rect = new Rect();
	}

	@Override
	protected void onSizeChanged(int w, int h, int old_w, int old_h) {
		rect.left = w / 10;
		rect.top = h / 10;
		rect.right = w * 9 / 10;
		rect.bottom = h * 9 / 10;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawRect(rect, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			status = (status >= 2) ? 0 : status+1;
			paint.setColor(getColor());
			invalidate();
		}
		return true;
	}

	private int getColor() {
		int color;

		switch (this.status) {
			case 0:
				color = Color.RED;
				break;
			case 1:
				color = Color.GREEN;
				break;
			case 2:
				color = Color.BLUE;
				break;
			default:
				color = Color.BLACK;
		}
		return color;
	}
}
