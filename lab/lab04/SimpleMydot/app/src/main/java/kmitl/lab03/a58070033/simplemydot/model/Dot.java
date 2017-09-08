package kmitl.lab03.a58070033.simplemydot.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by student on 8/25/2017 AD.
 */

public class Dot {


    public interface OnDotChangedListener {
        void onDotChanged(Dot dot);
    }

    private OnDotChangedListener listener;

    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }


    private int centerX;
    private int centerY;
    public ArrayList<Integer> cenListX = new ArrayList<>();
    public ArrayList<Integer> cenListY = new ArrayList<>();
    public ArrayList<Integer> redC = new ArrayList<>();
    public ArrayList<Integer> greenC = new ArrayList<>();
    public ArrayList<Integer> blueC = new ArrayList<>();
    public ArrayList<Integer> radius = new ArrayList<>();

    public ArrayList<Integer> getRedC() {
        return redC;
    }

    public void setRedC(ArrayList<Integer> redC) {
        this.redC = redC;
        this.listener.onDotChanged(this);
    }

    public ArrayList<Integer> getGreenC() {
        return greenC;
    }

    public void setGreenC(ArrayList<Integer> greenC) {
        this.greenC = greenC;
        this.listener.onDotChanged(this);
    }

    public ArrayList<Integer> getBlueC() {
        return blueC;
    }

    public void setBlueC(ArrayList<Integer> blueC) {
        this.blueC = blueC;
        this.listener.onDotChanged(this);
    }

    /**
     * Test
     ***********************************/
    public ArrayList<Integer> getCenListX() {
        return cenListX;
    }

    public void setCenListX(ArrayList<Integer> cenListX) {
        this.cenListX = cenListX;
        this.listener.onDotChanged(this);
    }

    public ArrayList<Integer> getCenListY() {
        return cenListY;
    }

    public void setCenListY(ArrayList<Integer> cenListY) {
        this.cenListY = cenListY;
        this.listener.onDotChanged(this);
    }

    public Dot(OnDotChangedListener listener, int centerX, int centerY, ArrayList<Integer> cenListX, ArrayList<Integer> cenListY, ArrayList<Integer> redC, ArrayList<Integer> greenC, ArrayList<Integer> blueC, ArrayList<Integer> radius) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.cenListX = cenListX;
        this.cenListY = cenListY;
        this.redC = redC;
        this.greenC = greenC;
        this.blueC = blueC;
        this.radius = radius;
    }

    /**
     * Test
     *******************************/


    public Dot(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;

    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;

    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }


    public ArrayList<Integer> getRadius() {
        return radius;
    }

    public void setRadius(ArrayList<Integer> radius) {
        this.radius = radius;
        this.listener.onDotChanged(this);
    }
}
