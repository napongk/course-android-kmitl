package kmitl.lab05.a58070033.lab05_workshop;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kmitl.lab05.a58070033.lab05_workshop.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentation, new MainFragment().newInstance("EVEANDBOY")).commit();

        Button accessFragment = (Button) findViewById(R.id.accessButton);
        accessFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainFragment.message.equals("EVEANDBOY")) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentation, new MainFragment().newInstance("SALES 90%!")).commit();
                }
                else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentation, new MainFragment().newInstance("EVEANDBOY")).commit();
                }
            }
        });
    }
}
