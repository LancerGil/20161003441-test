package com.example.administrator.a20161003441;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView userid;
    private GridView gridView;
    // 图片ID数组，对应所需显示的图片，对应main_item中的Imageview
    private int[] imgs = new int[]{R.drawable.p1, R.drawable.p2,
            R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6,};
    // 图片编号数组，显示在图片下的文字，对应main_item中的textview
    private String[] tits = new String[]{"安卓教程", "问题集锦", "提出问题", "实时交流",
            "上课签名", "个人资料"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String data = "";
        Bundle extras = getIntent().getExtras();//获取参数
        if (extras != null) {
            data = extras.getString("userid");//得到健为userid的值
        }
        userid = this.findViewById(R.id.main_userid);
        userid.setText(data);//显示登录的用户名
        gridView = findViewById(R.id.main_gridView1);
        // 生成动态数组，并且转入数据，将gridview中每一个item绑定为一个对象，其实也可以先设计一个类
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 6; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", imgs[i]);
            map.put("title", tits[i]);
            lstImageItem.add(map);
        }
        SimpleAdapter saImageItems = new SimpleAdapter(this,//Adapter用于将数据和控件绑定，有多种Adapter
                lstImageItem,// 数据来源
                R.layout.main_item,// main_item的XML实现，即gridview中每个item对应的布局
                // 动态数组与pic_item对应的子项
                new String[]{"image", "title"},
                // main_item的XML文件里面的一个ImageView, TextView的ID
                new int[]{R.id.main_item_imageView1, R.id.main_item_title});
        // 绑定数据并且显示
        gridView.setAdapter(saImageItems);
    }
}
