package kmitl.lab03.a58070033.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 9/8/2017 AD.
 */

public class DotParcelable implements Parcelable {
    private int centerX;
    private int centerY;
    private int radius;
    private int color;

    public DotParcelable(int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }


    protected DotParcelable(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(radius);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DotParcelable> CREATOR = new Creator<DotParcelable>() {
        @Override
        public DotParcelable createFromParcel(Parcel in) {
            return new DotParcelable(in);
        }

        @Override
        public DotParcelable[] newArray(int size) {
            return new DotParcelable[size];
        }
    };

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }
}
