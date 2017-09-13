package kmitl.lab03.a58070033.simplemydot.model;


import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Colors {

    Random rand = new Random();

    private List<Integer> colorList = new ArrayList<>();

    public Colors() {
        for(int i = 0; i <10 ; i += 1){
            addColor();
        }
    }
    public int getColor() {
        return colorList.get(
                new Random().nextInt(colorList.size()));
    }

    public void addColor() {
        colorList.add(Color.rgb(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)));
    }
}
