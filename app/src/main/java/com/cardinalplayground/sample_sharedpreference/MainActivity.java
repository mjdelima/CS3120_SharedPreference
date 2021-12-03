package com.cardinalplayground.sample_sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnClear;
    EditText etUsername, etPassword;
    Switch aSwitch;

    //contants
    public static final String SHARED_PREFS = "sharedprefs";
    public static final String KEY_USERNAME = "key_username";
    public static final String KEY_PASSWORD = "key_password";
    public static final String KEY_SWITCH = "key_switch";


    //data holder for our savePref
    String username, password;
    Boolean switch_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSave = findViewById(R.id.btnSave);
        aSwitch = findViewById(R.id.switch1);
        btnClear = findViewById(R.id.btnClear);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
            }
        });


        //load data
        loadData();

        //update the views
        updateViews();

    }

    private void clearData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        Toast.makeText(MainActivity.this, "Data has been cleared!", Toast.LENGTH_LONG).show();

    }

    //this will be used for saving the data
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USERNAME, etUsername.getText().toString());
        editor.putString(KEY_PASSWORD, etPassword.getText().toString());
        editor.putBoolean(KEY_SWITCH, aSwitch.isChecked());

        //to actually saved the changes in the editor
        //editor.commit();
        editor.apply();

        //prompt
        Toast.makeText(MainActivity.this, "Account Saved!", Toast.LENGTH_LONG).show();

    }

    // FOR LOADING THE DATA
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        username = sharedPreferences.getString(KEY_USERNAME, "");
        password = sharedPreferences.getString(KEY_PASSWORD, "");
        switch_status = sharedPreferences.getBoolean(KEY_SWITCH, false);
    }

    //FOR UPDATING VIEWS
    private void updateViews(){
        etPassword.setText(password);
        etUsername.setText(username);
        aSwitch.setChecked(switch_status);
    }

}