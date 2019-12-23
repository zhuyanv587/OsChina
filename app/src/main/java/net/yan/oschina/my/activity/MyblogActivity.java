package net.yan.oschina.my.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.yan.oschina.R;
import net.yan.oschina.my.fragment.MyFragment;

public class MyblogActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myblog);

        img=findViewById(R.id.my_blog);
        img.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_blog:
            Intent intent=new Intent(MyblogActivity.this, MyFragment.class);
            startActivity(intent);
            break;
        }
    }
}
