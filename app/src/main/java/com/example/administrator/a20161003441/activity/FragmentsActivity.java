package com.example.administrator.a20161003441.activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.a20161003441.R;
import com.example.administrator.a20161003441.fragments.HomeFragment;
import com.example.administrator.a20161003441.fragments.HomePagerFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentsActivity extends AppCompatActivity implements HomePagerFragment.OnHomePagerInteractionListener, HomeFragment.OnHomeInteractionListener {
    private static final String TAG = "FragmentsActivity";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private SectionsPagerAdapter mHomePagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private FloatingActionButton fab;
    private AlertDialog menuDialog;
    private View menuView;
    private GridView gvMenu;

    /** 菜单图片 **/
    int[] menu_image_array = { R.drawable.p1,
                    R.drawable.p2, R.drawable.p3,
                    R.drawable.p4, R.drawable.p5,
                    R.drawable.p6};
    /** 菜单文字 **/
    String[] menu_name_array = { "收藏", "留言", "社区", "下载", "网址", "账户"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "这是massage", Snackbar.LENGTH_LONG).setAction("这是action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FragmentsActivity.this, "你点击了action", Toast.LENGTH_SHORT).show();

                    }
                }).show();
            }
        });

        menuView = View.inflate(this,R.layout.grid_menu,null);
        // 创建AlertDialog
        menuDialog = new AlertDialog.Builder(this).create();
        menuDialog.setView(menuView); //对话框上显示.menu_main布局
        menuDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU)// 监听按键
                    dialog.dismiss();   //关闭对话框
                return false;
            }
        });

        gvMenu = menuView.findViewById(R.id.gv_menu);
        gvMenu.setAdapter(getMenuAdapter(menu_name_array, menu_image_array));//gridview绑定数据
        /** 监听menu选项 **/
        gvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                switch (arg2) {
                    case 0:// 搜索
                    case 1:// 文件管理
                    case 2:// 下载管理
                    case 3:// 全屏
                    case 4:// 翻页
                    case 5:// 翻页
                        Snackbar.make(fab,"选择了"+menu_name_array[arg2],Snackbar.LENGTH_SHORT).show();
                        break;
                }

                menuDialog.dismiss();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragments, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(TAG,"refreshing menu");
        switch (mViewPager.getCurrentItem()){
            case 0:
                menu.findItem(R.id.grid_menu).setVisible(false);
                menu.findItem(R.id.gender).setVisible(true);
                menu.findItem(R.id.hobby).setVisible(true);
                break;
            case 1:
                menu.findItem(R.id.grid_menu).setVisible(true);
                menu.findItem(R.id.gender).setVisible(false);
                menu.findItem(R.id.hobby).setVisible(false);
                break;
            case 2:
                menu.findItem(R.id.grid_menu).setVisible(false);
                menu.findItem(R.id.gender).setVisible(false);
                menu.findItem(R.id.hobby).setVisible(false);
                break;
            default:
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.male:
            case R.id.female:
                item.setChecked(true);
                Snackbar.make(fab, "选择了性别"+item.getTitle(), Snackbar.LENGTH_LONG).setAction("这是action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FragmentsActivity.this, "你点击了action", Toast.LENGTH_SHORT).show();

                    }
                }).show();
                break;
            case R.id.sport:
            case R.id.sing:
            case R.id.trip:
                item.setChecked(!item.isChecked());
                if (item.isChecked())
                    Snackbar.make(fab, "选择了爱好"+item.getTitle(), Snackbar.LENGTH_LONG).setAction("这是action", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(FragmentsActivity.this, "你点击了action", Toast.LENGTH_SHORT).show();

                        }
                    }).show();
                break;
            case R.id.grid_menu:
                if (menuDialog == null) {
                    menuDialog = new AlertDialog.Builder(this).setView(menuView).show();
                } else {
                    menuDialog.show();
                }
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * 实现Home1里的点击回调
     *
     * @param v
     */
    @Override
    public void onHomePagerInteraction(View v) {
        switch (v.getId()) {
            case R.id.tv_home_pager:
                Snackbar.make(fab,"dataBinding action succeed!",Snackbar.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    /**
     * 实现Home里面的点掉回调
     *
     * @param uri
     */
    @Override
    public void onHomeInteraction(Uri uri) {

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private SectionsPagerAdapter mHomePagerAdapter;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_inaty, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 2:
                    textView.setText(getString(R.string.section_format) + getString(R.string.catePage));
                    break;
                case 3:
                    textView.setText(getString(R.string.section_format) + getString(R.string.userPage));
                    break;
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment toReturn;
            switch (position) {
                case 0:
                    toReturn = HomeFragment.newInstance();
                    break;
                default:
                    toReturn = PlaceholderFragment.newInstance(position + 1);
                    break;
            }
            return toReturn;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    private SimpleAdapter getMenuAdapter(String[] menuNameArray,
                                         int[] imageResourceArray) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < menuNameArray.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", imageResourceArray[i]);
            map.put("itemText", menuNameArray[i]);
            data.add(map);
        }
        SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
                R.layout.menu_item, new String[] { "itemImage", "itemText" },
                new int[] { R.id.menuitem_imageView1, R.id.menuitem_textView1 });
        return simperAdapter;
    }
}
