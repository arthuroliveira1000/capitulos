package br.com.livroandroid.sensores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Usuário on 07/04/2015.
 */

public class BonecoAndroidView extends View {
    private Paint paint = new Paint();
    private Drawable drawable;
    private int dx, dy;

    public BonecoAndroidView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BonecoAndroidView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        // Configura o fundo cinza e cria a imagem
        paint.setColor(Color.LTGRAY);
        drawable = context.getResources().getDrawable(R.drawable.android);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Desenha o fundo da view (um quadrado cinza)
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        // Desenha a imagem das posições x e y
        canvas.translate(dx, dy);
        drawable.draw(canvas);
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
