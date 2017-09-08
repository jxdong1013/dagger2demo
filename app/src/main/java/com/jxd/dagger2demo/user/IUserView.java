package com.jxd.dagger2demo.user;

import com.jxd.dagger2demo.entity.User;
import com.jxd.dagger2demo.mpv.IView;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2017/9/4.
 */

public interface IUserView extends IView , RealmChangeListener<RealmResults<User>> {

}
