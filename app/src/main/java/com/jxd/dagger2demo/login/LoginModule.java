package com.jxd.dagger2demo.login;

import android.app.Activity;

import com.jxd.dagger2demo.ApiService;
import com.jxd.dagger2demo.BaseActivity;
import com.jxd.dagger2demo.mpv.IPresenter;
import com.jxd.dagger2demo.mpv.PreActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/31.
 */
@Module
public class LoginModule {

    ILoginView view;

    public LoginModule(ILoginView view){
        this.view=view;
    }

    @Provides
    public ILoginPresenter provideLoginPresenter(ApiService apiService ){
        return new LoginPresenter( view , apiService );
    }

    @PreActivity
    @Provides
    public ILoginView provideLoginView(){
        return  this.view;
    }

}
