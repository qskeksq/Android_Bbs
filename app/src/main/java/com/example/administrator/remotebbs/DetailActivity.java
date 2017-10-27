package com.example.administrator.remotebbs;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.remotebbs.Remote.BbsRemote;
import com.example.administrator.remotebbs.Remote.TaskInterface;
import com.example.administrator.remotebbs.model.Data;
import com.example.administrator.remotebbs.model.Result;

public class DetailActivity extends AppCompatActivity implements TaskInterface{

    private EditText inputTitle;
    private EditText inputContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
    }

    private void initView() {
        inputTitle = (EditText) findViewById(R.id.inputTitle);
        inputContent = (EditText) findViewById(R.id.inputContent);
    }

    public void postDetail(View view){
        Data data = new Data();
        data.setTitle(inputTitle.getText().toString());
        data.setContent(inputContent.getText().toString());
        // 특정한 이유가 없으면 날짜는 서버에서 세팅한다
//        data.setDate(new Date().toString());
        String devideID = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        data.setUser_id(devideID);
        Log.e("생성한 데이터", data.toString());
        BbsRemote.loadPost(this, data);
    }

    @Override
    public void setData(Data[] datas) {

    }

    @Override
    public void setResult(Result result) {
        if(result != null){
            if(result.isSuccess()){
                Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "결과값 없음", Toast.LENGTH_SHORT).show();
        }

    }
}
