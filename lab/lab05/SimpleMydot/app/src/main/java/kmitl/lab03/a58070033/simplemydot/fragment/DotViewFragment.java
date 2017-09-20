package kmitl.lab03.a58070033.simplemydot.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import kmitl.lab03.a58070033.simplemydot.R;
import kmitl.lab03.a58070033.simplemydot.model.Colors;
import kmitl.lab03.a58070033.simplemydot.model.Dot;
import kmitl.lab03.a58070033.simplemydot.model.Dots;
import kmitl.lab03.a58070033.simplemydot.model.ScreenCapture;
import kmitl.lab03.a58070033.simplemydot.view.DotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotViewFragment extends Fragment implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener, View.OnClickListener {

    final private int REQUEST_CODE_EXTERNAL_STORAGE = 1;
    private DotView dotView;
    private Dots dots;
    private Dot dot;
    private onDotViewFragmentListener dotFragmentListener;


    public DotViewFragment newInstance() {
        Bundle args = new Bundle();
        DotViewFragment fragment = new DotViewFragment();
        fragment.setListener(dotFragmentListener);
        setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dot_view, container, false);
        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);


        Button randomButton = (Button) rootView.findViewById(R.id.randomBtn);
        Button clearButton = (Button) rootView.findViewById(R.id.clearBtn);
        Button undoButton = (Button) rootView.findViewById(R.id.undoBtn);
        Button shareButton = (Button) rootView.findViewById(R.id.shareButton);
        randomButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        undoButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.randomBtn) onRandomDot(v);
        else if (v.getId() == R.id.clearBtn) onRemoveAll(v);
        else if (v.getId() == R.id.undoBtn) undoButton(v);
        else if (v.getId() == R.id.shareButton) shareButton(v);
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
        if(!EditDotFragment.isEnabled) {
            int dotPosition = dots.findDot(x, y);
            if (dotPosition == -1) {
                Dot newDot = new Dot(x, y, 60, new Colors().getColor());
                dots.addDot(newDot);
            } else {
                dots.removeBy(dotPosition);
            }
        }
    }

    @Override
    public void onDotViewLongPressed(int x, int y) {
        if(!EditDotFragment.isEnabled) {
        int dotPosition = dots.findDot(x, y);
        if (dotPosition > -1) {
//            Toast.makeText(getContext(), "Long Pressed", Toast.LENGTH_SHORT).show();
                EditDotFragment.isEnabled = true;
                onDotViewFragmentListener listener = getDotFragmentListener();
                dot = dots.getAllDot().get(dotPosition);
                listener.onDotViewFrag(dotPosition, dots, dot);
            }
        }
    }


    private boolean askforPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            return false;
        }
        return true;
    }

    public void shareButton(View view) {
        if (askforPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_CODE_EXTERNAL_STORAGE)) {
            Bitmap bm = ScreenCapture.takescreenshotOfRootView(view.getRootView());
            Uri uri = getImageUri(this.getContext(), bm);
            useShare(uri);
        }
    }

    public void useShare(Uri uri) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Share SimpleMyDot via"));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "Permission Granted, please press button again", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public Uri getImageUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String imgPath = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "DotBitmap", null);
        return Uri.parse(imgPath);
    }

    public void undoButton(View view) {
        dots.undoDot();
    }

    public void setListener(onDotViewFragmentListener listener) {
        this.dotFragmentListener = listener;
    }

    private onDotViewFragmentListener getDotFragmentListener() {
        Fragment fragment = getParentFragment();
        try {
            if (fragment != null) {
                return (onDotViewFragmentListener) fragment;
            } else {
                return (onDotViewFragmentListener) getActivity();
            }
        } catch (ClassCastException ignored) {
        }
        return null;
    }

    public interface onDotViewFragmentListener {
        void onDotViewFrag(int dotPosition, Dots dots, Dot dot);

    }


}
