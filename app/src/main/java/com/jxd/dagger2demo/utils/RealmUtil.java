package com.jxd.dagger2demo.utils;

import android.content.Context;

import com.jxd.dagger2demo.config.Constant;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 2017/9/5.
 */

public class RealmUtil {
    private  Realm realm;

    public  static  void init(Context context){
        Realm.init( context );
    }

    public Realm getRealm(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("jxd.realm")
                .schemaVersion(Constant.REALM_VERSION)
                .migration(new RealmVersionMigration())
                .build();
        return getRealm(realmConfiguration);
    }

    public  Realm getRealm(RealmConfiguration realmConfiguration){
        if(realm==null){
            realm = Realm.getInstance( realmConfiguration);
        }
        return realm;
    }

}
