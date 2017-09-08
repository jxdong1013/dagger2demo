package com.jxd.dagger2demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jxd.dagger2demo.user.UserFragment;
import com.jxd.dagger2demo.user.UserListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.menu1)
    ImageView menu1;
    @BindView(R.id.menu2)
    ImageView menu2;
    @BindView(R.id.menu3)
    ImageView menu3;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if( unbinder!=null ){
            unbinder.unbind();
            unbinder=null;
        }
    }

    @OnClick({R.id.menu1,R.id.menu2,R.id.menu3})
    public void menuClick(View v){
        if(v.getId()==R.id.menu1){
            loadFragment();
        }else if(v.getId()==R.id.menu2){
            loadUserFragment();
        }else if(v.getId()==R.id.menu3){
            loadUserListFragment();
        }
    }
    private void loadUserListFragment(){
        UserListFragment userListFragment = UserListFragment.newInstance();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace( R.id.container , userListFragment ,  UserFragment.TAG)
                .commit();
    }

    private void loadUserFragment(){
        UserFragment userFragment = UserFragment.newInstance();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace( R.id.container , userFragment ,  UserFragment.TAG)
                .commit();
    }

    private void loadFragment(){
        MainFragment mainFragment = MainFragment.newInstance("","");
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace( R.id.container , mainFragment ,  MainFragment.TAG)
                .commit();
    }

}
