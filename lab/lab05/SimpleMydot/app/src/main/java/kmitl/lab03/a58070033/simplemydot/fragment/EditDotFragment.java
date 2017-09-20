package kmitl.lab03.a58070033.simplemydot.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import kmitl.lab03.a58070033.simplemydot.R;
import kmitl.lab03.a58070033.simplemydot.model.Dot;
import kmitl.lab03.a58070033.simplemydot.model.Dots;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener {

    private DotEditFragmentListener listener;
    public static boolean isEnabled = false;
    private Dots dots;
    private Dot dot;
    private int dotPosition;
    private EditText xValue, yValue;
    private SeekBar seekRed, seekGreen, seekBlue, seekSize;
    private TextView preview;

    public EditDotFragment newInstance(int dotPosition, Dots dots, Dot dot) {
        Bundle args = new Bundle();
        args.putParcelable("Dots", dots);
        args.putParcelable("Dot", dot);
        args.putInt("dotPos", dotPosition);
        EditDotFragment fragment = new EditDotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.editfragment, container, false);

        Button submitBtn = (Button) rootView.findViewById(R.id.submitBtn);
        Button cancelBtn = (Button) rootView.findViewById(R.id.cancelBtn);
        submitBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        seekRed = (SeekBar) rootView.findViewById(R.id.seekRed);
        seekGreen = (SeekBar) rootView.findViewById(R.id.seekGreen);
        seekBlue = (SeekBar) rootView.findViewById(R.id.seekBlue);
        seekSize = (SeekBar) rootView.findViewById(R.id.seekSize);

        TextView redValue = (TextView) rootView.findViewById(R.id.redValue);
        TextView blueValue = (TextView) rootView.findViewById(R.id.blueValue);
        TextView greenValue = (TextView) rootView.findViewById(R.id.greenValue);
        TextView sizeValue = (TextView) rootView.findViewById(R.id.sizeValue);
        preview = (TextView) rootView.findViewById(R.id.preview);


        xValue = (EditText) rootView.findViewById(R.id.xValue);
        yValue = (EditText) rootView.findViewById(R.id.yValue);

        dotPosition = getArguments().getInt("dotPos");
        dots = getArguments().getParcelable("Dots");
        dot = getArguments().getParcelable("Dot");


        editColor(seekRed, redValue, getRGB("red", dotPosition, dots));
        editColor(seekGreen, greenValue, getRGB("green", dotPosition, dots));
        editColor(seekBlue, blueValue, getRGB("blue", dotPosition, dots));
        editSize(seekSize, sizeValue, dotPosition, dots);
        xValue.setText(String.valueOf(editXandY(dotPosition, dots, "X")));
        yValue.setText(String.valueOf(editXandY(dotPosition, dots, "Y")));

        preview.setBackgroundColor(dots.getAllDot().get(dotPosition).getColor());

        return rootView;
    }

    private int getRGB(String color, int dotPos, Dots dotsPack) {
        String hexColor = String.format("%06X", (0xFFFFFF & dotsPack.getAllDot().get(dotPos).getColor()));
        switch (color) {
            case "red":
                return Integer.parseInt(hexColor.substring(0, 2), 16);
            case "green":
                return Integer.parseInt(hexColor.substring(2, 4), 16);
            case "blue":
                return Integer.parseInt(hexColor.substring(4), 16);
        }
        return 0;
    }

    public void editColor(final SeekBar seekColor, final TextView colorValue, int color) {
        seekColor.setMax(255);
        seekColor.setProgress(color);
        colorValue.setText(String.valueOf(color));

        seekColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                colorValue.setText(String.valueOf(seekColor.getProgress()));
                preview.setBackgroundColor(Color.rgb(seekRed.getProgress(), seekGreen.getProgress(), seekBlue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    public void editSize(final SeekBar seekSize, final TextView SizeValue, final int dotPos, final Dots dotsPack) {
        seekSize.setMax(200);
        seekSize.setProgress(dotsPack.getAllDot().get(dotPos).getRadius());
        SizeValue.setText(String.valueOf(dotsPack.getAllDot().get(dotPos).getRadius()));

        seekSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                SizeValue.setText(String.valueOf(seekSize.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public int editXandY(int dotPos, Dots dotsPack, String axis) {
        switch (axis) {
            case "X":
                return dotsPack.getAllDot().get(dotPos).getCenterX();
            case "Y":
                return dotsPack.getAllDot().get(dotPos).getCenterY();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancelBtn) {
            getFragmentManager()
                    .beginTransaction()
                    .remove(EditDotFragment.this)
                    .commit();
            isEnabled = false;
        } else if (v.getId() == R.id.submitBtn) {
            changeDotProperties();

        }
    }

    public void setListener(DotEditFragmentListener listener) {
        this.listener = listener;
    }


    public interface DotEditFragmentListener {
        void removeEdit();
    }

    private DotEditFragmentListener getDotEditFragmentListener() {
        Fragment fragment = getParentFragment();
        try {
            if (fragment != null) {
                return (DotEditFragmentListener) fragment;
            } else {
                return (DotEditFragmentListener) getActivity();
            }
        } catch (ClassCastException ignored) {
        }
        return null;
    }

    public void changeDotProperties() {
//        Toast.makeText(getContext(), String.valueOf(seekRed.getProgress() + "," + seekGreen.getProgress() + "," + seekBlue.getProgress()), Toast.LENGTH_SHORT).show();
        dot.setColor(Color.rgb(seekRed.getProgress(), seekGreen.getProgress(), seekBlue.getProgress()));
        dot.setRadius(Integer.parseInt(String.valueOf(seekSize.getProgress())));
        dot.setCenterX(Integer.parseInt(String.valueOf(xValue.getText())));
        dot.setCenterY(Integer.parseInt(String.valueOf(yValue.getText())));
        dots.submitDot(dotPosition, dot);
    }


}
