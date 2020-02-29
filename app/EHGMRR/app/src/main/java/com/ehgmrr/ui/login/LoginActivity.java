package com.ehgmrr.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.ehgmrr.R;
import com.ehgmrr.db.AppDatabase;
import com.ehgmrr.db.UserDao;
import com.ehgmrr.pojo.User;
import com.ehgmrr.ui.filePatient.FilePatientFamilyActivity;
import com.ehgmrr.ui.patientFile.PhysiotherapistActivity;
import com.ehgmrr.ui.reset.ResetActivity;
import com.ehgmrr.ui.signup.SignupActivity;
import com.ehgmrr.ui.viewPatient.ViewPatientActivity;
import com.ehgmrr.utils.AppMain;
import com.ehgmrr.utils.SharedPref;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private TextView mReset;
    private Button mLogin;
    private Button mRegister;

    AppDatabase database;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = Room.databaseBuilder(this, AppDatabase.class, "db-app")
                .allowMainThreadQueries()
                .build();

        userDao = database.getUserDao();
        initView();

    }

    private void initView() {

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mReset = findViewById(R.id.reset);
        mLogin = findViewById(R.id.login);
        mRegister = findViewById(R.id.register);

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetActivity.class);
                startActivity(intent);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUsername.getText().toString().isEmpty()) {

                    Toast.makeText(LoginActivity.this, "Username is Empty !", Toast.LENGTH_SHORT).show();

                } else if (mPassword.getText().toString().isEmpty()) {

                    Toast.makeText(LoginActivity.this, "Password is Empty !", Toast.LENGTH_SHORT).show();

                } else {

                    Login(mUsername.getText().toString(), mPassword.getText().toString());

                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Login(final String Username, final String Password) {

         User result =  userDao.login(Username,Password);

         if (result !=null) {

             Toast.makeText(this, "Success ^_^", Toast.LENGTH_SHORT).show();

             if (result.getType().equals("Doctor")){
                 Intent intent = new Intent(LoginActivity.this, PhysiotherapistActivity.class);
                 startActivity(intent);
                 finish();

             } else if (result.getType().equals("Injured")) {

                 Intent intent = new Intent(LoginActivity.this, ViewPatientActivity.class);
                 intent.putExtra("username",Username);
                 startActivity(intent);
                 finish();

             } else if (result.getType().equals("Family")) {

                 Intent intent = new Intent(LoginActivity.this, FilePatientFamilyActivity.class);
                 startActivity(intent);
                 finish();
             }


             AppMain.getInstance().getSharedPref().writeString(SharedPref.APP_USERNAME,Username);

         } else {

             Toast.makeText(this, "Not Found @_@", Toast.LENGTH_SHORT).show();

         }

    }
}
