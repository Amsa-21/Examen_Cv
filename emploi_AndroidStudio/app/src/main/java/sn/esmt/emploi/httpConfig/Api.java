package sn.esmt.emploi.httpConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Api {
    @GET("/cv/all")
    public Call<ArrayList<CVsResponse>> all();
    @POST("/cv/save")
    public Call<CVsResponse> save(@Body CVsResponse cVsResponse);
    @GET("/cv/findId")
    public Call<CVsResponse> findById(@Query("id") int id);
    @DELETE("/cv/delete")
    public Call<Void> delete(@Query("id") int id);
    @PUT("/cv/update")
    public Call<CVsResponse> update(@Body CVsResponse cVsResponse);
}