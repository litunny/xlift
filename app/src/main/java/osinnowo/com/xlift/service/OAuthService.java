package osinnowo.com.xlift.service;

import osinnowo.com.xlift.model.OAuth;
import osinnowo.com.xlift.model.OAuthRequest;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by upperlink on 07/01/2017.
 */

public interface OAuthService {
    @Headers({
            "authorization: Basic T2VCUGwxb3piNC1rOjk3ZVFTWnVuWEh6QVRxMjJzZ1VyUHBpM3NrcDRrMG1Z",
            "Content-Type: application/json"
    })
    @POST("/oauth/token")
    Call<OAuth> getData(@Body OAuthRequest oAuthRequest);
}
