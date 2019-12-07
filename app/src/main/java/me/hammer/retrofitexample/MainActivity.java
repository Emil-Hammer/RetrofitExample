package me.hammer.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import me.hammer.retrofitexample.model.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public void btnGetRequest(View view) {
        final TextView txtView_GET = findViewById(R.id.txtView_GET);
        Call<PostModel> call = apiService.getPost();

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                txtView_GET.setText("Succesfully retrieved: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                txtView_GET.setText(t.getMessage());
            }
        });
    }

    public void btnPostRequest(View view) {
        final TextView txtView_POST = findViewById(R.id.txtView_POST);
        PostModel newPost = new PostModel(1,"Test", "Det her er bodyen");

        Call<PostModel> call = apiService.postPost(newPost);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                txtView_POST.setText("Succesfully posted: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                txtView_POST.setText(t.getMessage());
            }
        });
    }

    public void btnDeleteRequest(View view) {
        final TextView txtView_DELETE = findViewById(R.id.txtView_DELETE);
        Call<PostModel> call = apiService.deletePost();

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                txtView_DELETE.setText("Succesfully deleted: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                txtView_DELETE.setText(t.getMessage());
            }
        });
    }
}
