package com.jxd.dagger2demo.mpv;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2017/9/1.
 */

@Scope
@Retention(RUNTIME)
public @interface PreActivity {
}
