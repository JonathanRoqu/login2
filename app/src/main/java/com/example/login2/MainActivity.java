package com.example.login2;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));

        usernameEditText = new EditText(this);
        usernameEditText.setId(R.id.usernameEditText);
        usernameEditText.setHint("Usuario");
        RelativeLayout.LayoutParams usernameParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        usernameEditText.setLayoutParams(usernameParams);
        layout.addView(usernameEditText);

        passwordEditText = new EditText(this);
        passwordEditText.setId(R.id.passwordEditText);
        passwordEditText.setHint("Contraseña");
        RelativeLayout.LayoutParams passwordParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        passwordParams.addRule(RelativeLayout.BELOW, R.id.usernameEditText);
        passwordEditText.setLayoutParams(passwordParams);
        layout.addView(passwordEditText);

        loginButton = new Button(this);
        loginButton.setId(R.id.loginButton);
        loginButton.setText("Iniciar sesión");
        RelativeLayout.LayoutParams loginParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        loginParams.addRule(RelativeLayout.BELOW, R.id.passwordEditText);
        loginButton.setLayoutParams(loginParams);
        layout.addView(loginButton);

        setContentView(layout);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (authenticateUser(username, password)) {
                // Guardar el estado de inicio de sesión
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();

                // Aquí puedes iniciar la actividad principal o hacer lo que quieras después de iniciar sesión
                Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean authenticateUser(String username, String password) {
        // Aquí puedes implementar tu lógica de autenticación, como comprobar contra credenciales almacenadas localmente o conectarte a un servidor
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");

        return username.equals(savedUsername) && password.equals(savedPassword);
    }
}