package net.yan.oschina.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.okhttplib.callback.Callback;

import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;

import net.yan.oschina.R;
import net.yan.oschina.net.URLList;
import net.yan.oschina.util.ACache;
import net.yan.oschina.util.DialogHelp;
import net.yan.oschina.util.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView question_image1, question_image2, question_image3, question_image4, question_image5, question_image6;

    private ImageButton btn_back;

    private Button question_send;

    private EditText question_edt_title, question_text;

    private String large;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question_image1 = findViewById(R.id.question_img1);
        question_image1.setOnClickListener(this);
        question_image2 = findViewById(R.id.question_img2);
        question_image2.setOnClickListener(this);
        question_image3 = findViewById(R.id.question_img3);
        question_image3.setOnClickListener(this);
        question_image4 = findViewById(R.id.question_img4);
        question_image4.setOnClickListener(this);
        question_image5 = findViewById(R.id.question_img5);
        question_image5.setOnClickListener(this);
        question_image6 = findViewById(R.id.question_img6);
        question_image6.setOnClickListener(this);

        question_edt_title = findViewById(R.id.question_edt_title);
        question_text = findViewById(R.id.question_text);

        btn_back = findViewById(R.id.question_back);
        btn_back.setOnClickListener(this);

        question_send = findViewById(R.id.question_send);
        question_send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question_img4:
                handleSelectPicture();
                break;
            case R.id.question_send:
                OkHttpUtil.getDefault(this)
                        .doGetAsync(HttpInfo.Builder().setUrl(URLList.SEND_QUESTION + "?title=" + question_text.getText().toString() +
                                "&content=" + question_text.getText().toString() +
                                "&access_token=" + ACache.get(QuestionActivity.this).getAsString("token")).build(), new Callback() {
                            @Override
                            public void onSuccess(HttpInfo info) throws IOException {
                                Toast.makeText(QuestionActivity.this, "发送成功", Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            public void onFailure(HttpInfo info) throws IOException {
                                Toast.makeText(QuestionActivity.this, "发送失败", Toast.LENGTH_LONG).show();
                            }
                        });

                break;
            case R.id.question_back:
                finish();
                break;
        }
    }

    private void handleSelectPicture() {
        DialogHelp.getSelectDialog(this, getResources().getStringArray(R.array.choose_picture), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goToSelectPicture(i);
            }
        }).show();
    }

    public static final int ACTION_TYPE_ALBUM = 0;
    public static final int ACTION_TYPE_PHOTO = 1;

    private void goToSelectPicture(int position) {
        switch (position) {
            case ACTION_TYPE_ALBUM:
                Intent intent;
                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "选择图片"),
                            ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
                } else {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "选择图片"), ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
                }
                break;
            case ACTION_TYPE_PHOTO:
                // 判断是否挂载了SD卡
                String savePath = "";
                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                    savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/oschina/Camera/";
                    File savedir = new File(savePath);
                    if (!savedir.exists()) {
                        savedir.mkdirs();
                    }
                }

                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String fileName = "osc_" + timeStamp + ".jpg";// 照片命名
                File out = new File(savePath, fileName);
                Uri uri = Uri.fromFile(out);

                large = savePath + fileName;// 该照片的绝对路径

                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
                break;
            default:
                break;
        }
    }
}
