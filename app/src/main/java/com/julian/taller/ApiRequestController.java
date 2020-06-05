package com.julian.taller;

import retrofit2.Retrofit;

public class ApiRequestController {
    private static ApiRequestController apiRequestController = null;

    private ApiIntercace apiInterface;
    public static ApiRequestController getInstance() {
        if (apiRequestController == null) {
            apiRequestController = new ApiRequestController();
        }

        return apiRequestController;
    }

    private ApiRequestController(){

        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiIntercace.class);
    }

    public ApiIntercace getApiInterface() {
        return apiInterface;
    }
}
