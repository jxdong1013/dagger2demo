package com.jxd.dagger2demo.entity;

/**
 * Created by Administrator on 2017/9/1.
 */

public class BizData<T> extends DataBase{
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
