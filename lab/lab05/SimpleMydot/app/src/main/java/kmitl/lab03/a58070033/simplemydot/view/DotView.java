package kmitl.lab03.a58070033.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab03.a58070033.simplemydot.model.Dot;
import kmitl.lab03.a58070033.simplemydot.model.Dots;

public class DotView extends View {

    private final int longPressTimeset = 2000;
    private Paint paint;
    private Dots allDot;
    private long longPressTimer;
    private boolean longPressed;
    private OnDotViewPressListener onDotViewPressListener;

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.allDot != null) {
            for (Dot dot : allDot.getAllDot()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(
                        dot.getCenterX(),
                        dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }

    public void setOnDotViewPressListener(
            OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onDotViewPressListener.onDotViewPressed((int) e.getX(), (int) e.getY());
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            onDotViewPressListener.onDotViewLongPressed((int) e.getX(), (int) e.getY());
        }
    });


    public void setDots(Dots dots) {
        this.allDot = dots;
    }


    public interface OnDotViewPressListener {
        void onDotViewPressed(int x, int y);

        void onDotViewLongPressed(int x, int y);
    }

}
