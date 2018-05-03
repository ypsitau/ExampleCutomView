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

public class CustomView extends View {
	private static final int[] colorTbl = new int[]{
			Color.rgb(255, 192, 192),
			Color.rgb(192, 255, 192),
			Color.rgb(192, 192, 255),
			Color.rgb(255, 255, 192),
	};
	final Handler handler = new Handler();
	final float dp = getContext().getResources().getDisplayMetrics().density;
	final float sp = getContext().getResources().getDisplayMetrics().scaledDensity;
	private int iColor;
	private Paint paint;
	private float x, y;
	private float xDir, yDir;
	private float sizeBullet;
	private float textSize;
	private float xMax, yMax;
	private int period;
	private float dirAmount;
	private int colorBullet;

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		iColor = 0;
		paint = new Paint();
		x = 10 * dp;
		y = 10 * dp;
		dirAmount = 2 * dp;
		xDir = dirAmount;
		yDir = dirAmount;
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
		period = typedArray.getInteger(R.styleable.CustomView_period, 10);
		sizeBullet = typedArray.getDimension(R.styleable.CustomView_sizeBullet, 60f * dp);
		textSize = typedArray.getDimension(R.styleable.CustomView_textSize, 20f * sp);
		colorBullet = typedArray.getInteger(R.styleable.CustomView_colorBullet, Color.BLACK);
		typedArray.recycle();
		Util.Printf("dp=%f, sp=%f\n", dp, sp);
	}

	public CustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomView(Context context) {
		this(context, null);
	}

	public void startPeriodicJob() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (x < 0) {
					xDir = dirAmount;
				} else if (x > xMax - sizeBullet) {
					xDir = -dirAmount;
				}
				if (y < 0) {
					yDir = dirAmount;
				} else if (y > yMax - sizeBullet) {
					yDir = -dirAmount;
				}
				x += xDir;
				y += yDir;
				invalidate();
				handler.postDelayed(this, period);
			}
		});
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
		paint.setColor(Color.BLACK);
		paint.setTextSize(textSize);
		String str = String.format("Period: %d", period);
		canvas.drawText(str, 0, textSize, paint);
		paint.setColor(colorBullet);
		canvas.drawRect(x, y, x + sizeBullet, y + sizeBullet, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			Util.Printf("%08x ACTION_DOWN (%.2f, %.2f)\n",
					System.identityHashCode(this), event.getX(), event.getY());
			iColor++;
			if (iColor >= colorTbl.length) iColor = 0;
			invalidate();
		} else if (action == MotionEvent.ACTION_UP) {
			Util.Printf("%08x ACTION_UP   (%.2f, %.2f)\n",
					System.identityHashCode(this), event.getX(), event.getY());
		}
		return true;
	}
}
