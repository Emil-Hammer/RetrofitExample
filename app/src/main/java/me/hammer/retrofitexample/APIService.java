package me.hammer.retrofitexample;

import me.hammer.retrofitexample.model.PostModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("/posts/1")
    Call<PostModel> getPost();

    @POST("/posts")
    Call<PostModel> postPost(@Body PostModel postModel);

    @DELETE("/posts/1")
    Call<PostModel> deletePost();
}
