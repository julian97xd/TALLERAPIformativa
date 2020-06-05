package com.julian.taller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiRequestController mApiRequestController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiRequestController = ApiRequestController.getInstance();

        Call<CustomOldResponse> mApiCall =
                mApiRequestController
                        .getApiInterface().getOldProducts();
        mApiCall.enqueue(new Callback<CustomOldResponse>() {
            @Override
            public void onResponse(Call<CustomOldResponse> call, Response<CustomOldResponse> response) {
                if (response.isSuccessful()){
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setHasFixedSize(true);
                    Adapter adapter = new Adapter(MainActivity.this, response.body().getGetBooks());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CustomOldResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
