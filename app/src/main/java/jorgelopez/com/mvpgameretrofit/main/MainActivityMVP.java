package jorgelopez.com.mvpgameretrofit.main;

import jorgelopez.com.mvpgameretrofit.http.TwitchAPI;

public interface MainActivityMVP {

    interface View{
        String getId();
        String getName();
        void setName(String name);
        void setId(String id);
        void showInputError();
        TwitchAPI getTwitchApi();
        void setGameName(String gameName);
        void setGameImage(String url);
        void enableViews(boolean enabled);

    }

    interface Presenter{
        void setView(MainActivityMVP.View view);
        void sendButtonClicked();
    }

    interface Model{

    }
}
