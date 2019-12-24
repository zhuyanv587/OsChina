package net.yan.oschina.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.okhttplib.callback.Callback;

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
import android.widget.Toast;

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

import static android.view.accessibility.AccessibilityEvent.MAX_TEXT_LENGTH;

public class TweetActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btn_image1, btn_image2, btn_image3, btn_image4;

    private Button tween_transfer;

    private String large;

    private ImageButton btn_back;

    private EditText tweet_text;
    private static final String SOFTWARE_NAME = "请输入软件名";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        btn_image1 = findViewById(R.id.tweet_img1);
        btn_image2 = findViewById(R.id.tweet_img2);
        btn_image3 = findViewById(R.id.tweet_img3);
        btn_image4 = findViewById(R.id.tweet_img4);

        btn_back = findViewById(R.id.tweet_back);

        tween_transfer = findViewById(R.id.tween_transfer);

        tweet_text = findViewById(R.id.tweet_text);

        btn_image1.setOnClickListener(this);
        btn_image2.setOnClickListener(this);
        btn_image3.setOnClickListener(this);
        btn_image4.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        tween_transfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tweet_img1:
                handleSelectPicture();
                break;
            case R.id.tweet_img2:
                break;
            case R.id.tweet_img3:
                insertTrendSoftware();
                break;
            case R.id.tweet_img4:
                break;
            case R.id.tween_transfer:
                OkHttpUtil.getDefault(this)
                        .doGetAsync(HttpInfo.Builder().setUrl(URLList.SEND_TWEET + "?msg=" + tweet_text.getText().toString() +
                                "&access_token=" + ACache.get(TweetActivity.this).getAsString("token")).build(), new Callback() {
                            @Override
                            public void onSuccess(HttpInfo info) throws IOException {
                                Toast.makeText(TweetActivity.this, "发送成功", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(HttpInfo info) throws IOException {
                                Toast.makeText(TweetActivity.this, "发送失败", Toast.LENGTH_LONG).show();


                            }
                        });

                break;
            case R.id.tweet_back:
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

    private void insertTrendSoftware() {
        // 在光标所在处插入“#软件名#”
        int curTextLength = tweet_text.getText().length();
        if (curTextLength >= MAX_TEXT_LENGTH) return;
        String software = SOFTWARE_NAME;
        int start, end;
        if ((MAX_TEXT_LENGTH - curTextLength) >= software.length()) {
            start = tweet_text.getSelectionStart() + 1;
            end = start + software.length() - 2;
        } else {
            int num = MAX_TEXT_LENGTH - curTextLength;
            if (num < software.length()) {
                software = software.substring(0, num);
            }
            start = tweet_text.getSelectionStart() + 1;
            end = start + software.length() - 1;
        }
        if (start > MAX_TEXT_LENGTH || end > MAX_TEXT_LENGTH) {
            start = MAX_TEXT_LENGTH;
            end = MAX_TEXT_LENGTH;
        }
        tweet_text.getText().insert(tweet_text.getSelectionStart(), software);
        tweet_text.setSelection(start, end);// 设置选中文字
    }

}
