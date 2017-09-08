package com.jxd.dagger2demo.user;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxd.dagger2demo.R;
import com.jxd.dagger2demo.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

public class UserListAdapter extends BaseQuickAdapter<User , BaseViewHolder> {

    public UserListAdapter(   @Nullable List<User> data) {
        super(R.layout.layout_user_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        helper.setText(R.id.username , item.getUsername());
        helper.setText(R.id.job,item.getJob());
        helper.setText(R.id.address,item.getAddress());
    }

}
