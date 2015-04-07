package br.com.livroandroid.sensores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author rlecheta
 *
 */
public class BussulaView extends View {

	private static final double M_PI = 3.14;
	/**
	 * 0 representa o Norte.
	 * O angulo em graus (90, 180) etc que difere no Norte, para corrigir a bussula
	 */
	private int angulo = 0;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public BussulaView(Context context) {
		super(context);
		init();
	}

	public BussulaView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BussulaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {

		// Inicia o Paint (pincel)
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);
		paint.setColor(Color.BLUE);
		paint.setTextSize(22);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int x = getMeasuredWidth() / 2;
		int y = getMeasuredHeight() / 2;
		float radiusCompass;

		if (x > y) {
			radiusCompass = (float) (y * 0.9);
		} else {
			radiusCompass = (float) (x * 0.9);
		}
		
		
		canvas.drawCircle(x, y, radiusCompass, paint);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

		canvas.drawLine(
				x,
				y,
				(float) (x + radiusCompass
						* Math.sin((double) (-angulo) * M_PI / 180)),
				(float) (y - radiusCompass
						* Math.cos((double) (-angulo) * 3.14 / 180)),
				paint);

		canvas.drawText(String.valueOf(angulo), x + 10, y,
				paint);

	}

	public void update(int valor) {
		angulo = valor;
		invalidate();
	}

}