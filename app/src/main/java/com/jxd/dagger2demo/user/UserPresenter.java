package com.jxd.dagger2demo.user;

import android.text.TextUtils;

import com.jxd.dagger2demo.entity.User;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Administrator on 2017/9/4.
 */

public class UserPresenter implements IUserPresenter  , Realm.Transaction.OnSuccess , Realm.Transaction.OnError{
    IUserView userView;
    UserModel userModel;

    //@Inject
    public UserPresenter(IUserView userView , UserModel userModel){
        this.userView = userView;
        this.userModel = userModel;
    }

    @Override
    public void add(User user) {
        if(TextUtils.isEmpty( user.getUsername())){
            userView.toast("user name is empty!");
            return;
        }

        userView.showProgress();

        userModel.addUser( user , this , this );

    }

    @Override
    public void list() {

        userView.showProgress();

        userModel.list( userView );

    }

    @Override
    public void onError(Throwable error) {
        userView.hideProgress();
        userView.toast( error.getMessage() );
    }

    @Override
    public void onSuccess() {
        userView.hideProgress();
        userView.toast("success");
    }

    @Override
    public void onDestory() {
        if(userModel!=null) {
            userModel.onDestory();
        }
        userModel=null;
        userView=null;
    }
}
