package net.yan.oschina.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import net.yan.oschina.R;
import net.yan.oschina.net.BlogResult;

import androidx.appcompat.app.AppCompatActivity;

public class MySetActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton back;
    private ImageView setImage1, setImage2, setImage3, setImage4, setImage5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_set);

        setImage1 = findViewById(R.id.set_img1);
        setImage2 = findViewById(R.id.set_img2);
        setImage3 = findViewById(R.id.set_img3);
        setImage4 = findViewById(R.id.set_img4);
        setImage5 = findViewById(R.id.set_img5);

        back = findViewById(R.id.img_set_back);

        back.setOnClickListener(this);
        setImage1.setOnClickListener(this);
        setImage2.setOnClickListener(this);
        setImage3.setOnClickListener(this);
        setImage4.setOnClickListener(this);
        setImage5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_set_back:
                finish();
                break;
            case R.id.set_img1:
                break;
            case R.id.set_img2:
                break;
            case R.id.set_img3:
                break;
            case R.id.set_img4:
                break;
            case R.id.set_img5:
                break;
        }
    }
}
