package com.jxd.dagger2demo;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jxd.dagger2demo.utils.RealmUtil;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

/**
 * Created by Administrator on 2017/8/31.
 */

public class DApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        RealmUtil.init(this);

        Stetho.initialize( Stetho.newInitializerBuilder(this)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build()).build());
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
