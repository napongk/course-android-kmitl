package kmitl.lab03.a58070033.simplemydot.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class Dot implements Parcelable {
    private int centerX;
    private int centerY;
    private int radius;
    private int color;

    public Dot(int centerX, int centerY, int radius) {
        this(centerX, centerY, radius, Color.RED);
    }

    public Dot(int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    protected Dot(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
        color = in.readInt();
    }

    public static final Creator<Dot> CREATOR = new Creator<Dot>() {
        @Override
        public Dot createFromParcel(Parcel in) {
            return new Dot(in);
        }

        @Override
        public Dot[] newArray(int size) {
            return new Dot[size];
        }
    };

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(radius);
        dest.writeInt(color);
    }
}
