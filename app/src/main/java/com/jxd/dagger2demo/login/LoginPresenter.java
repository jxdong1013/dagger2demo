package com.jxd.dagger2demo.login;

import android.text.TextUtils;

import com.jxd.dagger2demo.ApiService;
import com.jxd.dagger2demo.entity.BizData;
import com.jxd.dagger2demo.entity.User;
import com.jxd.dagger2demo.mpv.PreActivity;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/9/1.
 */
@PreActivity
public class LoginPresenter <V extends ILoginView> implements ILoginPresenter {
    V loginView;
    LoginModel loginModel;

    @Inject
    public LoginPresenter(V loginView , ApiService apiService) {
        this.loginView=loginView;
        loginModel = new LoginModel( apiService , this);
    }

    @Override
    public void login(String username, String password) {
        if(TextUtils.isEmpty( username ) || TextUtils.isEmpty(password)){
            loginView.toast("username or password is empty!");
            return;
        }

        loginView.showProgress();

        loginModel.login(username,password);

        loginView.hideProgress();
    }

    @Override
    public void onSuccess(BizData<User> user) {
            loginView.hideProgress();
    }

    @Override
    public void onError(Throwable throwable) {
        loginView.toast("error");
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return loginView.bindLifecycle();
    }

    @Override
    public void onDestory() {
        if(loginModel!=null){
            loginModel.onDestory();
            loginModel=null;
        }
    }
}
