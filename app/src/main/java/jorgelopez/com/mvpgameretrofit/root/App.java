package jorgelopez.com.mvpgameretrofit.root;

import android.app.Application;

import jorgelopez.com.mvpgameretrofit.main.MainModule;

public class App extends Application {
    private ApplicationComponent component;
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .mainModule(new MainModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
