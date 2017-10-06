package lab07.a58070033.kmitl.api;

import lab07.a58070033.kmitl.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by student on 10/6/2017 AD.
 */

public interface LazyInstagramApi {

    String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);
}
