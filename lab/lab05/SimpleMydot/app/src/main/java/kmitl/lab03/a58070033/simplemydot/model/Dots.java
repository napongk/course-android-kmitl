package kmitl.lab03.a58070033.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Dots implements Parcelable {

    public Dots(Parcel in) {
    }

    public static final Creator<Dots> CREATOR = new Creator<Dots>() {
        @Override
        public Dots createFromParcel(Parcel in) {
            return new Dots(in);
        }

        @Override
        public Dots[] newArray(int size) {
            return new Dots[size];
        }
    };

    public Dots() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public interface OnDotsChangeListener {
        void onDotsChanged(Dots dots);
    }

    private OnDotsChangeListener listener;

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }

    private List<Dot> allDot = new ArrayList<>();

    public List<Dot> getAllDot() {
        return allDot;
    }

    public void addDot(Dot dot) {
        this.allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void removeBy(int position) {
        allDot.remove(position);
        this.listener.onDotsChanged(this);
    }

    public void undoDot() {
        if (!allDot.isEmpty()) {
            allDot.remove(allDot.size() - 1);
            this.listener.onDotsChanged(this);
        }
    }

    public void clearAll() {
        allDot.clear();
        this.listener.onDotsChanged(this);
    }

    public void submitDot(int dotPosition, Dot dot) {
        allDot.set(dotPosition, dot);
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y) {
        for (int i = 0; i < allDot.size(); i++) {
            int centerX = allDot.get(i).getCenterX();
            int centerY = allDot.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                    Math.sqrt(Math.pow(centerY - y, 2));
            if (distance <= 60) {
                return i;
            }
        }
        return -1;
    }


}
