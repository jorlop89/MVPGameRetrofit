package jorgelopez.com.mvpgameretrofit.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import jorgelopez.com.mvpgameretrofit.R;
import jorgelopez.com.mvpgameretrofit.http.TwitchAPI;
import jorgelopez.com.mvpgameretrofit.root.App;
import jorgelopez.com.mvpgameretrofit.root.ApplicationComponent;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View {

    @Inject
    MainActivityMVP.Presenter presenter;

    EditText etName;
    EditText etId;
    Button btnSend;

    ImageView ivGame;
    TextView tvGame;

    @Inject
    TwitchAPI twitchAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ApplicationComponent component = ((App) getApplication()).getComponent();
        component.inject(this);

        etName = findViewById(R.id.et_name);
        etId = findViewById(R.id.et_id);

        btnSend = findViewById(R.id.btn_send);

        ivGame = findViewById(R.id.iv_game);

        tvGame = findViewById(R.id.tv_game);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public String getId() {
        return etId.getText().toString();
    }

    @Override
    public String getName() {
        return etName.getText().toString();
    }

    @Override
    public void setName(String name) {
        etName.setText(name);
    }

    @Override
    public void setId(String id) {
        etId.setText(id);
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Inserta un id o un nombre para realizar la busqueda", Toast.LENGTH_LONG).show();
    }

    @Override
    public TwitchAPI getTwitchApi() {
        return twitchAPI;
    }

    @Override
    public void setGameName(String gameName) {
        //String txtGame = game.getName() + "(" + game.getId() + ")";
        tvGame.setText(gameName);
    }

    @Override
    public void setGameImage(String url) {
        String finalUrl = url.replace("{width}", "200").replace("{height}", "300");
        Glide.with(getBaseContext()).load(finalUrl).into(ivGame);
    }

    @Override
    public void enableViews(boolean enabled) {
        if(enabled){
            ivGame.setVisibility(View.VISIBLE);
            tvGame.setVisibility(View.VISIBLE);
        }

        else{
            ivGame.setVisibility(View.GONE);
            tvGame.setVisibility(View.GONE);
        }


    }

}
