package io.github.ypsitau.examplecutomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View implements Runnable {
	private int iColor;
	private Paint paint;
	private int x, y;
	private int xDir, yDir;
	private int wdBullet;
	private int htBullet;
	private int xMax, yMax;
	private int period;
	private final int dirAmount = 4;
	private int colorBullet;
	final Handler handler = new Handler();

	private static final int[] colorTbl = new int[]{
			Color.rgb(255, 192, 192),
			Color.rgb(192, 255, 192),
			Color.rgb(192, 192, 255),
			Color.rgb(255, 255, 192),
	};

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		iColor = 0;
		paint = new Paint();
		x = 10;
		y = 10;
		wdBullet = 100;
		htBullet = 100;
		xDir = dirAmount;
		yDir = dirAmount;
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
		period = typedArray.getInteger(R.styleable.CustomView_period, 10);
		colorBullet = typedArray.getInteger(R.styleable.CustomView_colorBullet, Color.BLACK);
		typedArray.recycle();
	}

	public CustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomView(Context context) {
		this(context, null);
	}

	public void startPeriodicJob() {
		handler.post(this);
	}

	@Override
	public void run() {
		if (x < 0) {
			xDir = dirAmount;
		} else if (x > xMax - wdBullet) {
			xDir = -dirAmount;
		}
		if (y < 0) {
			yDir = dirAmount;
		} else if (y > yMax - htBullet) {
			yDir = -dirAmount;
		}
		x += xDir;
		y += yDir;
		invalidate();
		handler.postDelayed(this, period);
	}

	@Override
	protected void onSizeChanged(int w, int h, int old_w, int old_h) {
		super.onSizeChanged(w, h, old_w, old_h);
		xMax = w;
		yMax = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(colorTbl[iColor]);
		paint.setColor(colorBullet);
		canvas.drawRect(x, y, x + 100, y + 100, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			iColor++;
			if (iColor >= colorTbl.length) iColor = 0;
			invalidate();
		}
		return true;
	}
}
