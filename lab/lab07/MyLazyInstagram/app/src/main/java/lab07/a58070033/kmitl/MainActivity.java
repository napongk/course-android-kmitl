package lab07.a58070033.kmitl;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lab07.a58070033.kmitl.adapter.PostAdapter;
import lab07.a58070033.kmitl.api.LazyInstagramApi;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Responsed";
    private UserProfile usrProfile;
    private ImageButton gridView, listView;
    private Spinner user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//

        user = (Spinner) findViewById(R.id.spinner);

        String[] userString = getResources().getStringArray(R.array.id);
        ArrayAdapter<String> adapterUser = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, userString);
        user.setAdapter(adapterUser);
        user.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getUserProfile(user.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getUserProfile("android");
            }
        });


    }

    private void getUserProfile(String usrName) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstagramApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyInstagramApi api = retrofit.create(LazyInstagramApi.class);

        Call<UserProfile> call = api.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    final UserProfile userProfile = response.body();
                    display(userProfile,"grid");

                    gridView = (ImageButton) findViewById(R.id.viewGrid);
                    listView = (ImageButton) findViewById(R.id.viewList);

                    gridView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            display(userProfile,"grid");
                            gridView.setBackgroundColor(Color.parseColor("#dcdcdc"));
                            listView.setBackgroundColor(Color.parseColor("#f9f9f9"));
                        }
                    });

                    listView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            display(userProfile,"list");
                            listView.setBackgroundColor(Color.parseColor("#dcdcdc"));
                            gridView.setBackgroundColor(Color.parseColor("#f9f9f9"));
                        }
                    });
                    displayImage(userProfile);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                TextView textUser = (TextView) findViewById(R.id.textUser);
                textUser.setText("Failed");
            }
        });

    }

    private void display(UserProfile usrProf, String choose){
        TextView textUser = (TextView) findViewById(R.id.textUser);
        textUser.setText("@"+usrProf.getUser());
        TextView textPost = (TextView) findViewById(R.id.textPost);
        textPost.setText("Post\n"+usrProf.getPost());
        TextView textFollower = (TextView) findViewById(R.id.textFollower);
        textFollower.setText("Follower\n"+usrProf.getFollower());
        TextView textFollwing = (TextView) findViewById(R.id.textFollowing);
        textFollwing.setText("Following\n"+usrProf.getFollowing());
        TextView textBio = (TextView) findViewById(R.id.textBio);
        textBio.setText(""+usrProf.getBio());

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        if(choose.equals("grid")){
            list.setLayoutManager(new GridLayoutManager(this,3));
        }
        else{
            list.setLayoutManager(new LinearLayoutManager(this));
        }
        PostAdapter adapter = new PostAdapter(this,choose);
        adapter.setData(usrProf.getPosts());
        list.setAdapter(adapter);



    }

    private void displayImage(UserProfile usrProf){
        ImageView imageProfile = (ImageView) findViewById(R.id.imageProfile);
        Glide.with(this).load(usrProf.getUrlProfile()).into(imageProfile);
    }

}
