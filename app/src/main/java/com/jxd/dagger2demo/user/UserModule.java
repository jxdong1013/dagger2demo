package com.jxd.dagger2demo.user;

import com.jxd.dagger2demo.ApiService;
import com.jxd.dagger2demo.utils.RealmUtil;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Administrator on 2017/9/4.
 */

@Module
public class UserModule {
    IUserView userView;
    public UserModule(IUserView userView){
        this.userView = userView;
    }

    @Provides
    public IUserView provideUserView(){
        return this.userView;
    }

    @Provides
    public UserModel provideUserModel(ApiService apiService , Realm realm){
        return new UserModel( apiService , realm );
    }

    @Provides
    public IUserPresenter provideUserPresenter( IUserView userView  , UserModel userModel ){
        return new UserPresenter( userView , userModel );
    }

    @Provides
    public Realm provideRealm(){
        return new RealmUtil().getRealm();
    }

}
