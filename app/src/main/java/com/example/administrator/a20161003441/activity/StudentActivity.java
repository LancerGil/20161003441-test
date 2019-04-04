package com.example.administrator.a20161003441.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.a20161003441.R;
import com.example.administrator.a20161003441.adapter.StuAdapter;
import com.example.administrator.a20161003441.data.StuContent;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    private RecyclerView rv_stu;
    private StuAdapter stuAdapter;
    private ArrayList<StuContent.StuItemContent> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        //设置数据
        data = getData();

        //设置adapter及其数据
        stuAdapter = new StuAdapter(this,data);

        rv_stu = findViewById(R.id.rv_stu_list);
        rv_stu.setAdapter(stuAdapter);
        rv_stu.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv_stu.addItemDecoration(decoration);
        rv_stu.setHasFixedSize(true);
        rv_stu.setFocusable(false);
    }

    private ArrayList<StuContent.StuItemContent> getData() {
        ArrayList<StuContent.StuItemContent> listitem = new ArrayList<>();
        listitem.add(new StuContent.StuItemContent("马云飞","2013020101",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("张小丽","2013020102",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("李军","2013020103",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("刘艳","2013020104",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("马云飞","2013020101",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("张小丽","2013020102",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("李军","2013020103",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("刘艳","2013020104",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("马云飞","2013020101",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("张小丽","2013020102",R.drawable.girl));
        listitem.add(new StuContent.StuItemContent("李军","2013020103",R.drawable.boy));
        listitem.add(new StuContent.StuItemContent("刘艳","2013020104",R.drawable.girl));
        return listitem;
    }
}
