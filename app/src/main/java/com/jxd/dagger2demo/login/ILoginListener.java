package com.jxd.dagger2demo.login;

import com.jxd.dagger2demo.entity.BizData;
import com.jxd.dagger2demo.entity.User;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2017/8/31.
 */

public interface ILoginListener {
    void onSuccess(BizData<User> user);
    void onError(Throwable throwable);
    LifecycleTransformer bindLifecycle();
}
