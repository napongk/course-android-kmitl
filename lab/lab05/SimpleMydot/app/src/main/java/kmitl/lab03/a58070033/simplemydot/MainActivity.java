package kmitl.lab03.a58070033.simplemydot;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import kmitl.lab03.a58070033.simplemydot.fragment.DotViewFragment;
import kmitl.lab03.a58070033.simplemydot.fragment.EditDotFragment;
import kmitl.lab03.a58070033.simplemydot.model.Dot;
import kmitl.lab03.a58070033.simplemydot.model.Dots;

public class MainActivity extends AppCompatActivity implements DotViewFragment.onDotViewFragmentListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            initialFragment();
        }


    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new DotViewFragment().newInstance()).commit();
    }

    private void editFragment(int dotPosition, Dots dots, Dot dot) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new EditDotFragment().newInstance(dotPosition, dots, dot)).commit();
    }


    @Override
    public void onDotViewFrag(int dotPosition, Dots dots, Dot dot) {
        editFragment(dotPosition, dots, dot);
    }


}
