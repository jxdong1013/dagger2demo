package com.jxd.dagger2demo.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jxd.dagger2demo.BaseFragment;
import com.jxd.dagger2demo.DApplication;
import com.jxd.dagger2demo.R;
import com.jxd.dagger2demo.entity.Address;
import com.jxd.dagger2demo.entity.User;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends BaseFragment implements IUserView {
    public static String TAG = UserFragment.class.getSimpleName();

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.company)
    EditText company;
    @BindView(R.id.job)
    EditText job;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.progress)
    ContentLoadingProgressBar progress;

    @Inject
    IUserPresenter userPresenter;
    Unbinder unbinder;
    UserComponent userComponent;



    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UserFragment.
     */
    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DApplication application = (DApplication) getActivity().getApplication();

        userComponent = DaggerUserComponent.builder()
                .appComponent(application.getAppComponent())
                .userModule(new UserModule(this))
                .build();
        userComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }


    @OnClick(R.id.btnAdd)
    public void onUserClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            addUser();
        }
    }

    private void addUser() {
        User user = new User();
        user.setUsername(username.getText().toString());
        user.setAddress(address.getText().toString());
        user.setAge(age.getText().toString());
        user.setJob(job.getText().toString());
        user.setCompany(company.getText().toString());
        user.setPassword(password.getText().toString());
        user.setPhone(phone.getText().toString());
        user.setUserid(UUID.randomUUID().toString());

        user.setAddressesList( new RealmList<Address>());
        Address address = new Address();
        address.setAddressid(UUID.randomUUID().toString());
        address.setPhone(user.getPhone());
        address.setAddress(user.getAddress());
        address.setName(user.getUsername());
        user.getAddressesList().add(address);

        address = new Address();
        address.setAddressid(UUID.randomUUID().toString());
        address.setPhone(user.getPhone());
        address.setAddress(user.getAddress());
        address.setName(user.getUsername());
        user.getAddressesList().add(address);

        userPresenter.add(user);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void showProgress() {
        progress.show();
    }

    @Override
    public void hideProgress() {
        progress.hide();
    }

    @Override
    public void toast(String msg) {
        progress.hide();
        Toast.makeText( this.application , msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if( userPresenter!=null ){
            userPresenter.onDestory();
            userPresenter=null;
        }
    }

    @Override
    public void onChange(RealmResults<User> element) {

    }
}
