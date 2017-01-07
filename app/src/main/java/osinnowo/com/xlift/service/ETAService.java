package osinnowo.com.xlift.service;

import osinnowo.com.xlift.model.EtaEstimateEndpoint;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by upperlink on 07/01/2017.
 */

public interface ETAService {
    @GET("/v1/eta")
    Call<EtaEstimateEndpoint> getData(
            @Query("lat") String lat,
            @Query("lng") String lng,
            @Header("authorization") String header
    );
}
