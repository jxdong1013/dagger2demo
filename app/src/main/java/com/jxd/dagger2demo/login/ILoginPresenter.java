package com.jxd.dagger2demo.login;

import com.jxd.dagger2demo.mpv.IPresenter;

/**
 * Created by Administrator on 2017/8/31.
 */

public interface ILoginPresenter extends IPresenter , ILoginListener {

    void login(String username , String password);
}
