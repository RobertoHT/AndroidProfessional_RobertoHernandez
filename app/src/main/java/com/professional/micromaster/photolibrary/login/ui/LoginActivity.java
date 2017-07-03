package com.professional.micromaster.photolibrary.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.professional.micromaster.photolibrary.MainActivity;
import com.professional.micromaster.photolibrary.R;
import com.professional.micromaster.photolibrary.login.LoginPresenter;
import com.professional.micromaster.photolibrary.login.LoginPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {
    @Bind(R.id.container)
    RelativeLayout container;
    @Bind(R.id.txtEmail)
    EditText txtEmail;
    @Bind(R.id.txtPassword)
    EditText txtPassword;
    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenterImpl(this);
        presenter.onCreate();
        presenter.checkForAuthenticatedUser();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    @OnClick(R.id.btnLogin)
    public void handleLogin() {
        presenter.validateLogin(txtEmail.getText().toString(), txtPassword.getText().toString());
    }

    @Override
    public void loginError(String error) {
        txtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        txtPassword.setError(msgError);
    }

    @Override
    public void registerError(String error) {
        txtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        txtPassword.setError(msgError);
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void setInputs(boolean enable) {
        txtEmail.setEnabled(enable);
        txtPassword.setEnabled(enable);
        btnLogin.setEnabled(enable);
    }
}
