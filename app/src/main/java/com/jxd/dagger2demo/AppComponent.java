package com.jxd.dagger2demo;

import android.app.Application;

import com.jxd.dagger2demo.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/8/31.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    //void inject(Application application);

    DApplication getApplicaion();

    OkHttpClient getOkHttpClient();

    Retrofit getRetroft();

    ApiService getApiService();

}
