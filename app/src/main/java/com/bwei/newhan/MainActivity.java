package com.bwei.newhan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.bwei.newhan.fragment.FragmentOne;
import com.bwei.newhan.fragment.FragmentTwo;

import thinkfreely.changemodelibrary.ChangeModeController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    Button btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //第一步 当前要立即变色的页面
        ChangeModeController.getInstance().init(this,R.attr.class).setTheme(this, R.style.DayTheme, R.style.NightTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn = (Button) findViewById(R.id.main_btn1);
        btn2 = (Button) findViewById(R.id.main_btn2);

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);

        btn.setSelected(true);
        btn.setTextColor(getResources().getColor(R.color.colorAccent));

        getSupportFragmentManager().beginTransaction().add(R.id.main_fm,new FragmentOne()).commit();

    }

    public void select(){

        btn.setSelected(false);
        btn2.setSelected(false);

        btn.setTextColor(getResources().getColor(R.color.dayTextColor));
        btn2.setTextColor(getResources().getColor(R.color.dayTextColor));

    }

    @Override
    public void onClick(View view) {

        select();

        switch(view.getId()){

            case R.id.main_btn1:
                btn.setSelected(true);
                btn.setTextColor(getResources().getColor(R.color.colorAccent));
                getSupportFragmentManager().beginTransaction().add(R.id.main_fm,new FragmentOne()).commit();
                break;
            case R.id.main_btn2:
                btn2.setSelected(true);
                btn2.setTextColor(getResources().getColor(R.color.colorAccent));
                getSupportFragmentManager().beginTransaction().add(R.id.main_fm,new FragmentTwo()).commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TypedValue attrTypedValue;
        switch (item.getItemId()) {
            case R.id.item_menu_day:
                //第二步 设置切换
                ChangeModeController.changeDay(this, R.style.DayTheme);
                attrTypedValue = ChangeModeController.getAttrTypedValue(this, R.attr.zztextColor);
                toolbar.setTitleTextColor(getResources().getColor(attrTypedValue.resourceId));
                break;
            case R.id.item_menu_night:
                ChangeModeController.changeNight(this, R.style.NightTheme);
                attrTypedValue = ChangeModeController.getAttrTypedValue(this, R.attr.zztextColor);
                toolbar.setTitleTextColor(getResources().getColor(attrTypedValue.resourceId));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //第三步   在onDestroy调用
        ChangeModeController.onDestory();
    }
}
