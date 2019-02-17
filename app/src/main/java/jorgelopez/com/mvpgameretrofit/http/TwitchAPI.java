package jorgelopez.com.mvpgameretrofit.http;

import jorgelopez.com.mvpgameretrofit.http.twitch.Twitch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TwitchAPI {

    @GET("games")
    Call<Twitch> getGame(@Header("Client-Id") String clientId, @Query("name") String name, @Query("id") String id);
}
