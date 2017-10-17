package lab07.a58070033.kmitl.api;

import lab07.a58070033.kmitl.model.FollowBody;
import lab07.a58070033.kmitl.model.FollowMessage;
import lab07.a58070033.kmitl.model.UserProfile;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by student on 10/6/2017 AD.
 */

public interface LazyInstagramApi {

    String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);


    @Headers("Content-Type: application/json")
    @POST("/follow")
    Call<FollowMessage> getFollow(@Body FollowBody body);


}
