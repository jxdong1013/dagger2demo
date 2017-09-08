package com.jxd.dagger2demo.user;

import com.jxd.dagger2demo.AppComponent;
import com.jxd.dagger2demo.mpv.PreActivity;

import dagger.Component;
import io.realm.Realm;

/**
 * Created by Administrator on 2017/9/4.
 */

@PreActivity
@Component(modules = {UserModule.class},dependencies = {AppComponent.class})
public interface UserComponent  {

    void inject(UserFragment userFragment);
    void inject(UserListFragment userListFragment);

    IUserPresenter getUserPresenter();

    IUserView getUserView();

    UserModel getUserModel();

    Realm getRealm();
}
