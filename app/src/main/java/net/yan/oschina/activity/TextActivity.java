package net.yan.oschina.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import net.yan.oschina.R;

public class TextActivity extends AppCompatActivity implements View.OnClickListener {

    private Button text_send;

    private ImageButton btn_back;

    private EditText text_url,text_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        text_url = findViewById(R.id.text_url);
        text_title = findViewById(R.id.text_title);

        btn_back = findViewById(R.id.text_back);
        btn_back.setOnClickListener(this);
        text_send = findViewById(R.id.text_send);
        text_send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_back:
                finish();
                break;
            case R.id.text_send:
                Toast.makeText(TextActivity.this,"投递成功",Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }
}
