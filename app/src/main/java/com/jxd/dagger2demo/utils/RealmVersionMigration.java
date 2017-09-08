package com.jxd.dagger2demo.utils;

import android.util.Log;

import com.jxd.dagger2demo.config.Constant;

import java.util.UUID;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmFieldType;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Administrator on 2017/9/6.
 */

public class RealmVersionMigration implements RealmMigration {
    public static String TAG = RealmVersionMigration.class.getSimpleName();
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        Log.i(TAG , "realm old version = " + oldVersion + " new version =" + newVersion);
        if( oldVersion == Constant.REALM_VERSION_2 || oldVersion == Constant.REALM_VERSION_3  ||oldVersion == Constant.REALM_VERSION_4) {
            db_update_3(realm);
        }
    }

    @Override
    public int hashCode() {
        return RealmVersionMigration.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj!=null && obj instanceof RealmVersionMigration;
    }

    private  void db_update_3(DynamicRealm realm){
         RealmSchema schema = realm.getSchema();
        RealmObjectSchema realmObjectSchema = schema.get("User");
        boolean hasField = realmObjectSchema.hasField("userid");
        if(!hasField){
            realmObjectSchema.addField("userid", String.class , FieldAttribute.PRIMARY_KEY).transform(new RealmObjectSchema.Function() {
                @Override
                public void apply(DynamicRealmObject obj) {
                    obj.setString("userid", UUID.randomUUID().toString());
                }
            });
        }else{
            RealmFieldType realmFieldType = realmObjectSchema.getFieldType("userid");
            //realmObjectSchema.
        }

        hasField = realmObjectSchema.hasField("username");
        if(hasField && !realmObjectSchema.isRequired("username")){
            //RealmFieldType realmFieldType = realmObjectSchema.getFieldType("username");
            realmObjectSchema.setRequired("username", true);
        }
        if(!realmObjectSchema.isRequired("password")){
            realmObjectSchema.setRequired("password",true);
        }
    }
}
