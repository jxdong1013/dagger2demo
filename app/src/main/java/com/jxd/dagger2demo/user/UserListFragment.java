package com.jxd.dagger2demo.user;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jxd.dagger2demo.BaseFragment;
import com.jxd.dagger2demo.DApplication;
import com.jxd.dagger2demo.R;
import com.jxd.dagger2demo.entity.User;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends BaseFragment implements IUserView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<User> userList;
    UserListAdapter userListAdapter;
    @Inject
    IUserPresenter iUserPresenter;
    UserComponent userComponent;
    Unbinder unbinder;

    public UserListFragment() {
        // Required empty public constructor
    }

    public static UserListFragment newInstance() {
        UserListFragment fragment = new UserListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DApplication application = (DApplication) getActivity().getApplication();
        userComponent = DaggerUserComponent.builder()
                .appComponent( application.getAppComponent())
                .userModule(new UserModule(this))
                .build();
        userComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_list, container, false);
        unbinder = ButterKnife.bind(this , root);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        userList=new ArrayList<>();
        userListAdapter=new UserListAdapter(userList);
        recyclerView.setAdapter(userListAdapter);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();

        iUserPresenter.list( );
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if( iUserPresenter !=null ){
            iUserPresenter.onDestory();
            iUserPresenter=null;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nonnull
    @Override
    public LifecycleTransformer bindUntilEvent(@Nonnull Object event) {
        return this.bindToLifecycle();
    }

    @Override
    public void onChange(RealmResults<User> element) {

        this.hideProgress();

        userListAdapter.setNewData(element);
    }

    @Override
    public void showProgress() {
        //super.showProgress();
        toast("loading");
    }

    @Override
    public void hideProgress() {
        //super.hideProgress();
        toast("hide");
    }

    @Override
    public void toast(String msg) {
        //super.toast(msg);
        Toast.makeText( this.application ,msg,Toast.LENGTH_LONG).show();
    }
}
