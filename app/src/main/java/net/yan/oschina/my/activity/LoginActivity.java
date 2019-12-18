package net.yan.oschina.my.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import net.yan.oschina.MainActivity;
import net.yan.oschina.R;
import net.yan.oschina.net.OauthClient;
import net.yan.oschina.util.ACache;
import net.yan.oschina.util.CallBackForUser;
import net.yan.oschina.util.LoginAccessUtil;

public class LoginActivity extends AppCompatActivity implements CallBackForUser, View.OnClickListener {

    @BindView(R.id.tv_login_forget_password)
    TextView tv_login_forget_password;

    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.login_pull)
    LinearLayout login_pull;
    @BindView(R.id.navigation_back)
    ImageButton navigation_back;

    @BindView(R.id.login_username)
    EditText login_username;
    @BindView(R.id.login_password)
    EditText login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        login_pull = findViewById(R.id.login_pull);
        navigation_back =findViewById(R.id.navigation_back);

        login_username.setOnClickListener(this);
        login_password.setOnClickListener(this);
        tv_login_forget_password .setOnClickListener(this);
        btn_login .setOnClickListener(this);
        btn_register .setOnClickListener(this);
        login_pull.setOnClickListener(this);
        navigation_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.navigation_back:
                finish();
                break;
        }

    }

    public void login() {
        OauthClient client = new OauthClient();
        client.setClientId("k3m0e56x47ff4dQsEMqH");
        client.setClientSecret("H47A2MkNxnAhUiJGKCZdAvBhhEtlRDfw");
        client.setRedirectUrl("http://www.baidu.com");
        client.setUsername(login_username.getText().toString());
        client.setPassword(login_password.getText().toString());

        LoginAccessUtil.login(this,client);
    }

    @Override
    public void getUserMsg(String userJson) {
        ACache.get(this).put("user",userJson);
        ACache.get(this).put("isLogin",true);
        ACache.get(this).put("token", JSON.parseObject(userJson).getJSONObject("token").getString("accessToken"));
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
