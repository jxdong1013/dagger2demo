package com.jxd.dagger2demo.user;

import com.jxd.dagger2demo.entity.User;
import com.jxd.dagger2demo.mpv.IPresenter;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2017/9/4.
 */

public interface IUserPresenter extends IPresenter {
    void add(User user);

    void list();
}
