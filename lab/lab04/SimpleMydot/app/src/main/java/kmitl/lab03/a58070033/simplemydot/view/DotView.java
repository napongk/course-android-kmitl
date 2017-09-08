package kmitl.lab03.a58070033.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.a58070033.simplemydot.model.Dot;


/**
 * Created by student on 8/25/2017 AD.
 */

public class DotView extends View {
    private Paint paint;
    private Dot dot;
    private Random rand = new Random();


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dot != null) {
            for (int i = 0; i < dot.cenListX.size(); i++) {
//                paint.setColor(Color.GREEN);
                paint.setColor(Color.rgb(dot.redC.get(i), dot.greenC.get(i), dot.blueC.get(i)));
                canvas.drawCircle(dot.cenListX.get(i), dot.cenListY.get(i), dot.radius.get(i), paint);
            }
        }
    }

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

    public void setDot(Dot dot) {
        this.dot = dot;
    }
}
