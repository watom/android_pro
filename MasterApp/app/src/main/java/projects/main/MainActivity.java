package projects.main;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haitao.www.myformer.CourseActivity;
import com.haitao.www.myformer.R;
import com.haitao.www.myformer.SampleActivity;

import projects.main.activity.ShareAppActivity;
import projects.main.main_fragement.DiscoveryFragment;
import projects.main.main_fragement.LookFragment;
import projects.main.main_fragement.NewsFragment;
import projects.main.main_fragement.TalkFragment;
import projects.main.main_fragement.WodeFragment;

import com.haitao.www.myformer.function.kernel_module.barcode.activity.CaptureActivity;
import com.haitao.www.myformer.structure_design.StructureDesignActivity;
import com.haitao.www.myformer.utils.DataUtil;
import com.haitao.www.myformer.utils.Log;
import com.haitao.www.myformer.utils.ToastUtils;
import com.haitao.www.myformer.utils.WebViewTool;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener {
    private Context context;
    private Toolbar toolbar;
    private WebView framelayoutWebview;
    private RadioGroup radiogroupBottomMenu;
    private RadioButton radiobutton01, radiobutton02, radiobutton03, radiobutton04, radiobutton05;
    private Fragment currentFragment, newsFragment, lookFragment, talkFragment, discoveryFragment, wodeFragment;
    private FragmentTransaction addFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ignoreBatteryOptimization(this);
        context = MainActivity.this;
        findViews();
        setFloatingAndDrawe();
        initFragment();

    }

    private void findViews() {
        setToolBar();
        radiogroupBottomMenu = (RadioGroup) findViewById(R.id.radiogroup_bottom_menu);
        radiobutton01 = (RadioButton) findViewById(R.id.radiobutton_01);
        radiobutton02 = (RadioButton) findViewById(R.id.radiobutton_02);
        radiobutton03 = (RadioButton) findViewById(R.id.radiobutton_03);
        radiobutton04 = (RadioButton) findViewById(R.id.radiobutton_04);
        radiobutton05 = (RadioButton) findViewById(R.id.radiobutton_05);
        radiogroupBottomMenu.setOnCheckedChangeListener(this);
        framelayoutWebview = (WebView) findViewById(R.id.framelayout_webview);
    }

    private void setToolBar() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//利用Toolbar代替ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        toolbar.setTitle("开发集合");
        toolbar.setSubtitle("专门用于测试Demo");

        // Navigation Icon 要設定在 setSupoortActionBar 才有作用否則會出現 back button
        toolbar.setNavigationIcon(R.drawable.ic_chat);
        //设置Toolbar右边的menu入口图片,如果不设置会使用系统默认的灰色三点图标
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_plus));
    }

    private void initFragment() {
        FragmentManager fragmentManager;
        newsFragment = new NewsFragment();
        lookFragment = new LookFragment();
        talkFragment = new TalkFragment();
        discoveryFragment = new DiscoveryFragment();
        wodeFragment = new WodeFragment();
        currentFragment = talkFragment;
        radiobutton03.setChecked(true);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, currentFragment).commit();
    }

    private void setFloatingAndDrawe() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, ZhenZhuMeiXueInput.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //设置navigationView的样式
        View headerView = navigationView.getHeaderView(0);
        ImageView geekPortrait = (ImageView) headerView.findViewById(R.id.geek_portrait);
        TextView geekName = (TextView) headerView.findViewById(R.id.geek_name);
        TextView geekMotto = (TextView) headerView.findViewById(R.id.geek_motto);
        geekPortrait.setImageResource(R.mipmap.ic_launcher);
        geekName.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/创艺简魏碑.TTF"));
        geekName.setText("王海涛");
        geekMotto.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/华文行楷.ttf"));
        geekMotto.setText("灵感总会稍纵即逝，机会也会相伴而来，请抓住它！");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //可以通过app:popupTheme属性来控制的，在style文件里可以设置风格、字体颜色大小等等属性。
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_shortcut:

                break;
            case R.id.action_scan:
                Intent openCameraIntent = new Intent(this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            case R.id.action_search:
//                Intent intent = new Intent(Intent.ACTION_PICK);
                Intent intent = new Intent(
                        "android.media.action.IMAGE_CAPTURE");
//                intent.setType("image/*");// 相片类型
                this.startActivityForResult(intent, 222);
                break;
            case R.id.action_settings:
                ToastUtils.showToast(this, "打开布局边界线");
                break;
        }
        return false;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_studio) {
            startActivity(new Intent(MainActivity.this, SampleActivity.class));
        } else if (id == R.id.nav_source) {

        } else if (id == R.id.nav_stream) {

        } else if (id == R.id.nav_voyage) {
            startActivity(new Intent(this, StructureDesignActivity.class));
        } else if (id == R.id.nav_earth) {

        } else if (id == R.id.nav_interest) {
            startActivity(new Intent(MainActivity.this, CourseActivity.class));
        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_share) {
            startActivity(new Intent(this, ShareAppActivity.class));
        } else if (id == R.id.nav_secure) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.e("group", "" + group.getCheckedRadioButtonId());
        Log.e("checkedId", "" + checkedId);
        switch (group.getCheckedRadioButtonId()) {
            case R.id.radiobutton_01:
                switchFragment(newsFragment);
                break;
            case R.id.radiobutton_02:
                switchFragment(lookFragment);
                break;
            case R.id.radiobutton_03:
                switchFragment(talkFragment);
                break;
            case R.id.radiobutton_04:
                switchFragment(discoveryFragment);
                break;
            case R.id.radiobutton_05:
                switchFragment(wodeFragment);
                break;
        }
    }

    private void switchFragment(Fragment fragment) {
        //判断容器中当前显示的Fragment是不是和需要切换的Fragment相同。
        if (currentFragment != fragment) {
            //判断切换的Fragment是否已经添加过。
            if (!fragment.isAdded()) {
                //如果没有添加，则先把当前的Fragment隐藏，把切换的Fragment添加上。
                addFragment = getSupportFragmentManager().beginTransaction().hide(currentFragment).add(R.id.frame_layout, fragment);
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来。
                addFragment = getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment);
            }
            addFragment.commitAllowingStateLoss();
            currentFragment = fragment;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String url = bundle.getString("result");
            new WebViewTool(this, framelayoutWebview, url);
        }
    }

    /**
     * onKeyDown 此方法兼容Android 1.0到Android 2.1 也是常规方法
     * onBackPressed该方法仅适用于2.0或更新版的sdk,它的返回键的所有事件都不会向上一级Activity中传播。
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //点击了返回键
            //如果返回true就拦截了事件，不会进入onBackPressed方法，反之亦然。
            onBackPressed();                       //退出APP。嵌套使用，兼容所有返回事件
            return true;                           //消费该事件
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            //点击了菜单键
            ToastUtils.showToast(this, "点击了菜单键");
            super.openOptionsMenu();
            return super.onKeyDown(keyCode, event); // 如果不是back键正常响应
        }else{
            return false;                           //继续传递该事件
        }
    }

    @Override
    protected void onUserLeaveHint() {
        //点击了home键
        super.onUserLeaveHint();
        ToastUtils.showToast(this, "点击了home键");
        if(MainActivity.this.isTaskRoot()){
            //判断Activity是否是task根
            moveTaskToBack(false);//该方法可将activity退到后台，注意不是finish()退出，效果基本等同于home键。
        }
    }


    long mExitTime = System.currentTimeMillis();

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);//如果抽屉打开，先关闭抽屉
            return;
        } else {
            //1.点击的时间差如果大于2000，则提示用户点击两次退出
            if (System.currentTimeMillis() - mExitTime > 2000) {
                //2.保存当前时间
                mExitTime = System.currentTimeMillis();
                //3.提示
                ToastUtils.showToast(this, "连续返回两次可退出APP");
            } else {
                //4.点击的时间差小于2000，调用父类onBackPressed方法执行退出。
                super.onBackPressed();
                finish();
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //点击返回键并且是长按，则退出
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK || event.isLongPress()) {
            return isQuitDialog();
        } else {
            return false;
        }
    }

    private AlertDialog.Builder dialog;

    private boolean isQuitDialog() {
        dialog = new AlertDialog.Builder(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("退出吗？");
        dialog.setMessage("退出请点击“确认”；继续使用请点击“取消”。");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showToast(context, "谢谢支持");
            }
        });
        dialog.show();
        return true;
    }

    /**
     * 系统提示电池是否需要优化
     * 需要权限：<uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"/>
     */
    private void ignoreBatteryOptimization(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);

            boolean hasIgnored = powerManager.isIgnoringBatteryOptimizations(activity.getPackageName());
            //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
            if (!hasIgnored) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            } else {
                android.util.Log.d("ignoreBattery", "hasIgnored");
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String tag = intent.getStringExtra("EXIT_TAG");
        if (tag != null && !DataUtil.isEmpty(tag)) {
            if ("SINGLETASK".equals(tag)) {//退出程序
                finish();
            }
        }
    }
}
