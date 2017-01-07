package osinnowo.com.xlift.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by upperlink on 06/01/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "https://api.lyft.com";
    private static Retrofit retrofit = null;

    public  static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
