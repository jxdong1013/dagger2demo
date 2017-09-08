package com.jxd.dagger2demo;

import android.app.Application;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.jxd.dagger2demo.utils.RealmUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/31.
 */

@Singleton
@Module
public class AppModule {

    private DApplication application;

    public AppModule(DApplication application){
        this.application = application;
    }
    @Singleton
    @Provides
    public DApplication provideApplication(){
        return this.application;
    }
    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        return  okHttpClient;
    }
    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
    @Singleton
    @Provides
    public GsonConverterFactory provideGsonConverter( Gson gson ){
        return  GsonConverterFactory.create( gson );
    }
    @Singleton
    @Provides
    public RxJava2CallAdapterFactory provideRxJava2CallAdapter(){
        return  RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public Retrofit provideRetroft( GsonConverterFactory gsonConverterFactory , RxJava2CallAdapterFactory rxJava2CallAdapterFactory){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.baidu.com")
                .addConverterFactory( gsonConverterFactory )
                .addCallAdapterFactory( rxJava2CallAdapterFactory)
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    public ApiService provideApiService( Retrofit retrofit){
        ApiService apiService = retrofit.create( ApiService.class );
        return apiService;
    }




}
