package io.github.ypsitau.examplecutomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class CustomView extends View {
	private int iColor;
	private Paint paint;
	private Rect rect;
	private int x, y;
	private int xDir, yDir;
	private final int dirAmount = 1;
	private Timer timer;
	private Handler handler = new Handler();

	private final int colorBg = Color.GRAY;
	private final int colorTbl[] = {
		Color.RED,
		Color.GREEN,
		Color.BLUE,
		Color.BLACK,
	};

	public CustomView(Context context) {
		this(context, null);
	}

	public CustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		iColor = 0;
		paint = new Paint();
		rect = new Rect();
		x = 10;
		y = 10;
		xDir = dirAmount;
		yDir = dirAmount;
	}

	public void start() {
		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						if (x < 0) {
							xDir = dirAmount;
						} else if (x > 1000) {
							xDir = -dirAmount;
						}
						if (y < 0) {
							yDir = dirAmount;
						} else if (y > 1400) {
							yDir = -dirAmount;
						}
						x += xDir;
						y += yDir;
						invalidate();
					}
				});
			}
		}, 100, 1);
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
		canvas.drawColor(colorBg);
		canvas.drawRect(x, y, x + 100, y + 100, paint);
		//paint.setColor(colorTbl[iColor]);
		//canvas.drawRect(rect, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			iColor++;
			if (iColor >= colorTbl.length) iColor = 0;
			invalidate();
		}
		return true;
	}
}
