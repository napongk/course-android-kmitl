package kmitl.lab03.a58070033.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.a58070033.simplemydot.model.Dot;
import kmitl.lab03.a58070033.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    private DotView dotView;
    private Dot dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dot = new Dot(0, 0);
        dot.setListener(this);
        dotView = (DotView) findViewById(R.id.dotView);

    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        this.dot.radius.add(60);
        this.dot.setRadius(dot.radius);
        this.dot.cenListX.add(centerX);
        this.dot.setCenListX(dot.cenListX);
        this.dot.cenListY.add(centerY);
        this.dot.setCenListY(dot.cenListY);
        this.dot.redC.add(random.nextInt(255));
        this.dot.blueC.add(random.nextInt(255));
        this.dot.greenC.add(random.nextInt(255));
        this.dot.setRedC(dot.redC);
        this.dot.setGreenC(dot.greenC);
        this.dot.setBlueC(dot.blueC);
//        TextView ttX = (TextView) findViewById(R.id.txtX);
//        TextView ttY = (TextView) findViewById(R.id.txtY);
//        ttX.setText(String.valueOf(centerX));
//        ttY.setText(String.valueOf(centerY));
    }

    public void onClearDot(View view){
        this.dot.cenListX.clear();
        this.dot.cenListY.clear();
        this.dot.radius.clear();
        this.dot.redC.clear();
        this.dot.greenC.clear();
        this.dot.blueC.clear();
        this.dot.setCenListX(dot.cenListX);
        this.dot.setCenListY(dot.cenListY);
        this.dot.setRadius(dot.radius);
        this.dot.setRedC(dot.redC);
        this.dot.setGreenC(dot.greenC);
        this.dot.setBlueC(dot.blueC);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Random random = new Random();
        int getaction = event.getAction();
        switch (getaction) {
            case MotionEvent.ACTION_DOWN:
                // Write your code to perform an action on down
                this.dot.cenListX.add(Math.round(event.getRawX()));
                this.dot.setCenListX(dot.cenListX);
                this.dot.cenListY.add(Math.round(event.getRawY()) - (getWindowManager().getDefaultDisplay().getHeight() - this.dotView.getMeasuredHeight())/2);
                this.dot.setCenListY(dot.cenListY);
                this.dot.radius.add(120);
                this.dot.setRadius(dot.radius);
                this.dot.redC.add(random.nextInt(255));
                this.dot.blueC.add(random.nextInt(255));
                this.dot.greenC.add(random.nextInt(255));
                this.dot.setRedC(dot.redC);
                this.dot.setGreenC(dot.greenC);
                this.dot.setBlueC(dot.blueC);
//                TextView ttX = (TextView) findViewById(R.id.txtX);
//                TextView ttY = (TextView) findViewById(R.id.txtY);
//                ttX.setText(String.valueOf(Math.round(event.getRawX())));
//                ttY.setText(String.valueOf(Math.round(event.getRawY())));
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onDotChanged(Dot randomDot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }
}
