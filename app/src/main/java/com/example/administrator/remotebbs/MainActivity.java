package com.example.administrator.remotebbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.remotebbs.Lib.Const;
import com.example.administrator.remotebbs.Remote.BbsRemote;
import com.example.administrator.remotebbs.Remote.TaskInterface;
import com.example.administrator.remotebbs.model.Data;
import com.example.administrator.remotebbs.model.Result;

public class MainActivity extends AppCompatActivity implements TaskInterface {

    private Button post;
    private RecyclerView recyclerview;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BbsRemote.loadGet(this, null);
        initView();
        setRecyclerview();
    }

    private void initView() {
        post = (Button) findViewById(R.id.post);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    private void setRecyclerview(){
        adapter = new RecyclerAdapter();
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goDetail(View view){
        Intent intent = new Intent(this, DetailActivity.class);
        startActivityForResult(intent, Const.GO_POST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Const.GO_POST:
                if(resultCode == RESULT_OK){
                    // 갱신
                } else{
                    //
                }
        }
    }

    @Override
    public void setData(Data[] datas) {
        adapter.setData(datas);
    }


    @Override
    public void setResult(Result result) {

    }
}
