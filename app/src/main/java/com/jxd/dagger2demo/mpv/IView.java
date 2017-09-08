package com.jxd.dagger2demo.mpv;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2017/8/31.
 */

public interface IView {

    void showProgress();

    void hideProgress();

    void toast(String msg);

    LifecycleTransformer bindLifecycle();
}
