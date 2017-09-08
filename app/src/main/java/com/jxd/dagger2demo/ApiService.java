package com.jxd.dagger2demo;

import com.jxd.dagger2demo.entity.BizData;
import com.jxd.dagger2demo.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/8/31.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("/user/MyBusiness")
    Observable<BizData<User>> login(@Field("username") String username,  @Field("password") String password);
}
