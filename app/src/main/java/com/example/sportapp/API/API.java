package com.example.sportapp.API;

import com.example.sportapp.models.Activity;
import com.example.sportapp.models.Banner;
import com.example.sportapp.models.Result;
import com.example.sportapp.models.SubActivity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {

    @Multipart
    @POST("signup.php")      // parameter we are send to api
    Call<Result> SignUp(@Part("name") RequestBody name,
                        @Part("email") RequestBody email,
                         @Part("password") RequestBody password
                              );

    @Multipart
    @POST("login.php")
    Call<Result> login(   @Part("email") RequestBody email,
                          @Part("password") RequestBody password

    );

    @GET("banner_image.php")
    Call<List<Banner>> getBannerImage();

    @GET("activity.php")
    Call<List<Activity>> getActivity();

    @Multipart
    @POST("sub_activity.php")
    Call<List<SubActivity>> getSubActivity(  @Part("main_name") RequestBody main_name  );

    @Multipart
    @POST("sub_2.php")
    Call<List<SubActivity>> getSubActivity2(  @Part("name") RequestBody name  );

    @Multipart
    @POST("play_activity.php")
    Call<List<SubActivity>> getPlayActivity(  @Part("name") RequestBody name  );

    @Multipart
    @POST("insertActivity.php")      // parameter we are send to api
    Call<SubActivity> addActivity  ( @Part("name") RequestBody name,
                                     @Part("time") RequestBody time,
                                     @Part("Main_Activity") RequestBody Main_Activity,
                                     @Part("describe_activity") RequestBody describe_activity
    );

    @Multipart
    @POST("deleteActivity.php")
    Call<List<SubActivity>> deleteActivity(  @Part("id") RequestBody id  );

    @Multipart
    @POST("get_time.php")
    Call<List<SubActivity>> getTime(  @Part("name") RequestBody name  );

//    @Multipart
//    @POST("updatePassword.php")
//    Call<Result> updatePassword(  @Part("email") RequestBody email  ,
//                                        @Part("password") RequestBody password
//        );

}
