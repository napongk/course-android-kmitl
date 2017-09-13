package kmitl.lab03.a58070033.simplemydot;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import kmitl.lab03.a58070033.simplemydot.model.Colors;
import kmitl.lab03.a58070033.simplemydot.model.Dot;
import kmitl.lab03.a58070033.simplemydot.model.Dots;
import kmitl.lab03.a58070033.simplemydot.model.ScreenCapture;
import kmitl.lab03.a58070033.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity
        implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener{


    private DotView dotView;
    private Dots dots;
    final private int REQUEST_CODE_EXTERNAL_STORAGE = 1;
    String[] choose = {"Change Color", "Change Size", "Delete"};

    ScreenCapture capt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);


        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        Button shareButton = (Button) findViewById(R.id.sharebutton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askforPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,REQUEST_CODE_EXTERNAL_STORAGE);
                Bitmap bm = ScreenCapture.takescreenshotOfRootView(rootView);
                Uri uri = ScreenCapture.saveBitmap(bm);
                useShare(uri);
            }
        });
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 60, new Colors().getColor());
        dots.addDot(newDot);
    }


    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onRemoveAll(View view) {
        dots.clearAll();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if(dotPosition == -1) {
            Dot newDot = new Dot(x, y, 60, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.removeBy(dotPosition);
        }
    }

    private void askforPermission(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(this,permission)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{permission}, requestCode);
        }

    }

    public void useShare(Uri uri){
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Share SimpleMyDot via"));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_EXTERNAL_STORAGE:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(this, "Denied", Toast.LENGTH_SHORT);
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
