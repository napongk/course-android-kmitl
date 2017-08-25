package kmitl.lab03.a58070033.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        dot = new Dot(0,0,60);
        dot.setListener(this);
        dotView = (DotView) findViewById(R.id.dotView);

    }

    public void onRandomDot (View view) {
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY= random.nextInt(this.dotView.getHeight());
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
    }

    @Override
    public void onDotChanged(Dot randomDot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }
}
