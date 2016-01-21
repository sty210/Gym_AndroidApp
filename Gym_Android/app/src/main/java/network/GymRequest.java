package network;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by elite on 16. 1. 21..
 */
public interface GymRequest {
    @GET("/gyms.json")
    Call<List<Gyms>> listGyms();
}
