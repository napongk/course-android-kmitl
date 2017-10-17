package lab07.a58070033.kmitl;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import lab07.a58070033.kmitl.adapter.PostAdapter;
import lab07.a58070033.kmitl.api.LazyInstagramApi;
import lab07.a58070033.kmitl.model.FollowBody;
import lab07.a58070033.kmitl.model.FollowMessage;
import lab07.a58070033.kmitl.model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Responsed";
    private UserProfile usrProfile;
    private ImageButton gridView, listView;
    private Spinner user;
    private Button followButton;
    private ProgressBar progress;
    private String selectedUser, selectedView = "grid";
    private FollowMessage follow;
    private Map isFollow = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
        statFollow();
        user = (Spinner) findViewById(R.id.spinner);
        followButton = (Button) findViewById(R.id.follow);

        String[] userString = getResources().getStringArray(R.array.id);
        ArrayAdapter<String> adapterUser = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, userString);
        user.setAdapter(adapterUser);
        user.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getUserProfile(user.getSelectedItem().toString());
                selectedUser = user.getSelectedItem().toString();
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getUserProfile("android");
            }
        });


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void getUserProfile(String usrName) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstagramApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if (isFollow.get(usrName).equals("followed")) {
            followButton.setText("Followed");
            followButton.setTextColor(Color.WHITE);
            followButton.setBackground(getDrawable(R.drawable.round2));
        }
        else{
            followButton.setText("Follow");
            followButton.setTextColor(Color.BLACK);
            followButton.setBackground(getDrawable(R.drawable.round));
        }

        final LazyInstagramApi api = retrofit.create(LazyInstagramApi.class);

        Call<UserProfile> call = api.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    final UserProfile userProfile = response.body();
                    display(userProfile, "grid");

                    gridView = (ImageButton) findViewById(R.id.viewGrid);
                    listView = (ImageButton) findViewById(R.id.viewList);

                    if (selectedView.equals("grid")) {
                        display(userProfile, "grid");
                        gridView.setBackgroundColor(Color.parseColor("#dcdcdc"));
                        listView.setBackgroundColor(Color.parseColor("#f9f9f9"));
                    } else {
                        display(userProfile, "list");
                        listView.setBackgroundColor(Color.parseColor("#dcdcdc"));
                        gridView.setBackgroundColor(Color.parseColor("#f9f9f9"));
                    }


                    gridView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectedView = "grid";
                            if (selectedView.equals("grid")) {
                                display(userProfile, "grid");
                                gridView.setBackgroundColor(Color.parseColor("#dcdcdc"));
                                listView.setBackgroundColor(Color.parseColor("#f9f9f9"));
                            }
                        }
                    });

                    listView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectedView = "list";
                            if (selectedView.equals("list")) {
                                display(userProfile, "list");
                                listView.setBackgroundColor(Color.parseColor("#dcdcdc"));
                                gridView.setBackgroundColor(Color.parseColor("#f9f9f9"));
                            }
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


        progress = (ProgressBar) findViewById(R.id.progressBar3);
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                Call<FollowMessage> call2 = api.getFollow(new FollowBody(selectedUser, false));
                call2.enqueue(new Callback<FollowMessage>() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(Call<FollowMessage> call, Response<FollowMessage> response) {
                        if (response.isSuccessful()) {
                            final FollowMessage followmes = response.body();
                            Toast.makeText(getApplicationContext(), followmes.getMessage(), Toast.LENGTH_SHORT).show();
                            user.getSelectedItem();

                            if (followmes.getMessage().equals("OK")) {
                                progress.setVisibility(View.INVISIBLE);
                                isFollow.put(selectedUser, "followed");
                                if(isFollow.get(selectedUser).equals("followed")){
                                    followButton.setText("Followed");
                                    followButton.setTextColor(Color.WHITE);
                                    followButton.setBackground(getDrawable(R.drawable.round2));
                                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<FollowMessage> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Fail or already followed", Toast.LENGTH_SHORT).show();
                        progress.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });


    }

    private void display(UserProfile usrProf, String choose) {
        TextView textUser = (TextView) findViewById(R.id.textUser);
        textUser.setText("@" + usrProf.getUser());
        TextView textPost = (TextView) findViewById(R.id.textPost);
        textPost.setText("Post\n" + usrProf.getPost());
        TextView textFollower = (TextView) findViewById(R.id.textFollower);
        textFollower.setText("Follower\n" + usrProf.getFollower());
        TextView textFollwing = (TextView) findViewById(R.id.textFollowing);
        textFollwing.setText("Following\n" + usrProf.getFollowing());
        TextView textBio = (TextView) findViewById(R.id.textBio);
        textBio.setText("" + usrProf.getBio());

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        if (choose.equals("grid")) {
            list.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            list.setLayoutManager(new LinearLayoutManager(this));
        }
        PostAdapter adapter = new PostAdapter(this, choose);
        adapter.setData(usrProf.getPosts());
        list.setAdapter(adapter);


    }

    private void displayImage(UserProfile usrProf) {
        ImageView imageProfile = (ImageView) findViewById(R.id.imageProfile);
        Glide.with(this).load(usrProf.getUrlProfile()).into(imageProfile);
    }

    private String getUserBody(UserProfile usrProfile) {
        return usrProfile.getUser();
    }

    private void statFollow() {
        isFollow.put("android", "none");
        isFollow.put("nature", "none");
        isFollow.put("cartoon", "none");
    }
}
