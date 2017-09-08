package com.jxd.dagger2demo.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jxd.dagger2demo.BaseActivity;
import com.jxd.dagger2demo.DApplication;
import com.jxd.dagger2demo.R;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends  BaseActivity<ILoginPresenter> implements ILoginView {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    Unbinder unbinder;

    //LoginComponent loginComponent;
    //@Inject
    //ILoginPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);

        DaggerLoginComponent
                .builder()
                .appComponent( ((DApplication )this.getApplication()).getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
            unbinder=null;
        }
    }

    @OnClick(R.id.btnLogin)
    public void login(View v){

        String usernamestr = username.getText().toString();
        String passwordstr = password.getText().toString();
        iPresenter.login( usernamestr,passwordstr);
    }

    @Override
    public void showProgress() {
        //super.showProgress();
        Toast.makeText(this, "showProgress", Toast.LENGTH_LONG).show();

    }

    @Override
    public void hideProgress() {
        //super.hideProgress();
        Toast.makeText(this, "hideProgress", Toast.LENGTH_LONG).show();
    }

    @Override
    public void toast(String msg) {
//        super.toast(msg);
        Toast.makeText(this, "toast", Toast.LENGTH_LONG).show();
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    public void clearAll() {
        username.setText("");
        password.setText("");
    }


}
