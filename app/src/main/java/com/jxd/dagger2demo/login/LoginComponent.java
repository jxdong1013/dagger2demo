package com.jxd.dagger2demo.login;

import com.jxd.dagger2demo.AppComponent;
import com.jxd.dagger2demo.mpv.PreActivity;
import dagger.Component;

/**
 * Created by Administrator on 2017/8/31.
 */
@PreActivity
@Component( modules = {LoginModule.class} , dependencies = AppComponent.class )
public interface LoginComponent {

    void inject(LoginActivity activity);

    ILoginPresenter getLoginPresenter();

}
