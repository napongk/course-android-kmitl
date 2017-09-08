package kmitl.lab03.a58070033.simplemydot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import kmitl.lab03.a58070033.simplemydot.model.DotParcelable;
import kmitl.lab03.a58070033.simplemydot.model.DotSerializable;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        int x = getIntent().getIntExtra("xValue", 0);
        TextView tvValueX = (TextView) findViewById(R.id.tvValueX);
        TextView tvSerial = (TextView) findViewById(R.id.tvSerial);
        TextView tvParcel = (TextView) findViewById(R.id.tvParcel);
        DotSerializable dotSerializable = (DotSerializable) getIntent().getSerializableExtra("dotSerializable");

        tvValueX.setText(String.valueOf(x));
        tvSerial.setText("centerX : " + dotSerializable.getCenterX() +
                         "\ncenterY : " + dotSerializable.getCenterY() +
                        "\nRadius : " + dotSerializable.getRadius());
        tvSerial.setTextColor(dotSerializable.getColor());

        DotParcelable dotParcelable = getIntent().getParcelableExtra("dotParcelable");

        tvParcel.setText("centerX : " + dotParcelable.getCenterX() +
                "\ncenterY : " + dotParcelable.getCenterY() +
                "\nRadius : " + dotParcelable.getRadius());

        tvParcel.setTextColor(dotParcelable.getColor());
    }
}
