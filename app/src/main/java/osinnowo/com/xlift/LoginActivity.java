package osinnowo.com.xlift;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import osinnowo.com.xlift.model.OAuth;
import osinnowo.com.xlift.model.OAuthRequest;
import osinnowo.com.xlift.network.ApiClient;
import osinnowo.com.xlift.service.OAuthService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) View _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;
    @BindView(R.id.login_page) LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            layout.setBackgroundDrawable( getResources().getDrawable(R.drawable.taxibackground) );
        } else {
            layout.setBackground( getResources().getDrawable(R.drawable.taxibackground));
        }



        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });



    }

    private void login () {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);


        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        OAuthService service = ApiClient.getClient().create(OAuthService.class);
        OAuthRequest request = new OAuthRequest();

        request.setGrantType("client_credentials");
        request.setScope("public");

        Call<OAuth> call = service.getData(request);

        call.enqueue(new Callback<OAuth>() {
            @Override
            public void onResponse(Response<OAuth> response, Retrofit retrofit) {

                Log.d("bad", response.raw().toString());
                OAuth Res = response.body();
                Log.d("bad", Res.getAccessToken());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                SharedPreferences settings = getApplicationContext().getSharedPreferences("OAUTH_STORE", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("token", Res.getAccessToken());
                editor.apply();

                //SharedPreferences s = getApplicationContext().getSharedPreferences("OAUTH_STORE", 0);
                //Toast.makeText(getApplicationContext(), s.getString("token", null), Toast.LENGTH_LONG).show();

                startActivity(intent);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
