package network;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by elite on 16. 1. 20..
 */
public interface TyEx {
    @GET("/commoncds.json")
    Call<List<List<Exercises>>> listExercises(@Query("category") String category, @Query("id") int id);
}
