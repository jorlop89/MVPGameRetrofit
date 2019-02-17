package jorgelopez.com.mvpgameretrofit.main;

import android.support.annotation.Nullable;

import java.util.List;

import jorgelopez.com.mvpgameretrofit.http.twitch.Game;
import jorgelopez.com.mvpgameretrofit.http.twitch.Twitch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MainActivityMVP.Presenter {

    @Nullable
    private MainActivityMVP.View view;

    public MainActivityPresenter() {
    }

    @Override
    public void setView(MainActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void sendButtonClicked() {
        if(view != null){
            String name = view.getName();
            String id = view.getId();

            if(name.trim().equals("") && id.trim().equals("")) {
                view.showInputError();
                view.enableViews(false);
            }

            else{
                String viewName = view.getName();
                String viewId = view.getId();
                Call<Twitch> call = view.getTwitchApi().getGame("xzx5hs4xgbx7wb9m0jh9zdqfu6ip5s", viewName, viewId);
                call.enqueue(new Callback<Twitch>() {
                    @Override
                    public void onResponse(Call<Twitch> call, Response<Twitch> response) {
                        List<Game> games = response.body().getGames();
                        if(games != null){
                            for(Game game: games){
                                String txtGame = game.getName() + "(" + game.getId() + ")";
                                String url = game.getBoxArtUrl();
                                view.enableViews(true);
                                view.setGameName(txtGame);
                                view.setGameImage(url);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Twitch> call, Throwable t) {
                        view.enableViews(false);
                        t.printStackTrace();
                    }
                });
            }
        }
    }
}
