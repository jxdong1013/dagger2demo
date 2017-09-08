package com.jxd.dagger2demo.user;

import com.jxd.dagger2demo.ApiService;
import com.jxd.dagger2demo.entity.User;
import com.jxd.dagger2demo.mpv.IModel;
import com.jxd.dagger2demo.utils.RealmUtil;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2017/9/4.
 */

public class UserModel implements IModel{

    //@Inject
    ApiService apiService;

    //@Inject
    Realm realm;

    //@Inject
    public UserModel (ApiService apiService , Realm realm){
        this.apiService=apiService;
        this.realm = realm;
    }

    public void addUser( final User user , Realm.Transaction.OnSuccess onSuccess , Realm.Transaction.OnError onError ){
        //Realm realm = RealmUtil.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                    //RealmObject  obj = realm.copyFromRealm( user );
                    realm.copyToRealmOrUpdate(user);
            }
        }, onSuccess, onError);
    }

    public void list( RealmChangeListener<RealmResults<User>> realmChangeListener ){

        RealmResults<User> users = realm.where(User.class).findAllAsync();
        users.addChangeListener( realmChangeListener );

    }



    @Override
    public void onDestory() {
        if( realm!=null ){
            realm.removeAllChangeListeners();
            realm.close();
        }
    }
}
