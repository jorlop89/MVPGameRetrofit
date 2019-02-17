package jorgelopez.com.mvpgameretrofit.root;

import javax.inject.Singleton;

import dagger.Component;
import jorgelopez.com.mvpgameretrofit.http.TwitchModule;
import jorgelopez.com.mvpgameretrofit.main.MainActivity;
import jorgelopez.com.mvpgameretrofit.main.MainModule;

@Singleton
@Component(modules = {ApplicationModule.class, MainModule.class, TwitchModule.class})
public interface ApplicationComponent {
    void inject(MainActivity target);
}
