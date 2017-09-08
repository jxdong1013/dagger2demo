package com.jxd.dagger2demo.login;

import com.jxd.dagger2demo.ApiService;
import com.jxd.dagger2demo.entity.BizData;
import com.jxd.dagger2demo.entity.User;
import com.jxd.dagger2demo.mpv.IModel;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/31.
 */

public class LoginModel implements IModel {
    //@Inject
    ApiService  apiService;
    ILoginListener ILoginListener;

    //@Inject
    public LoginModel( ApiService apiService , ILoginListener ILoginListener){
        this.apiService=apiService;
        this.ILoginListener = ILoginListener;
    }


    public void login(String username , String password) {
       apiService.login(username,password)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .compose( ILoginListener.bindLifecycle() )
               .subscribe(new Consumer<BizData<User>>() {
                   @Override
                   public void accept(BizData<User> user) throws Exception {
                        ILoginListener.onSuccess(user);
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                       ILoginListener.onError(throwable);
                   }
               });

    }

    @Override
    public void onDestory() {
        apiService=null;
        ILoginListener=null;
    }
}
