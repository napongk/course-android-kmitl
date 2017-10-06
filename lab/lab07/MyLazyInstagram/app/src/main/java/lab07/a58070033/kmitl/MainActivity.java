package lab07.a58070033.kmitl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile("nature");

        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter);
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
                    UserProfile userProfile = response.body();
                    display(userProfile);
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

    private void display(UserProfile usrProf){
        TextView textUser = (TextView) findViewById(R.id.textUser);
        textUser.setText("@"+usrProf.getUser());
        TextView textPost = (TextView) findViewById(R.id.textPost);
        textPost.setText(""+usrProf.getPost());
        TextView textFollower = (TextView) findViewById(R.id.textFollower);
        textFollower.setText(""+usrProf.getFollower());
        TextView textFollwing = (TextView) findViewById(R.id.textFollowing);
        textFollwing.setText(""+usrProf.getFollowing());
        TextView textBio = (TextView) findViewById(R.id.textBio);
        textBio.setText(""+usrProf.getBio());

    }

    private void displayImage(UserProfile usrProf){
        ImageView imageProfile = (ImageView) findViewById(R.id.imageProfile);
        Glide.with(this).load(usrProf.getUrlProfile()).into(imageProfile);
    }
}
