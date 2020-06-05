package com.julian.taller;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiIntercace {
    @GET("volumes?q=harry+pother/")
    Call<CustomOldResponse> getOldProducts();
}
