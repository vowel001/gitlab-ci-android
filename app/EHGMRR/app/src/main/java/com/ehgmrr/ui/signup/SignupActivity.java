package com.ehgmrr.ui.signup;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.ehgmrr.R;
import com.ehgmrr.db.AppDatabase;
import com.ehgmrr.db.UserDao;
import com.ehgmrr.pojo.User;
import com.ehgmrr.utils.AppMain;
import com.ehgmrr.utils.SharedPref;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private EditText mFname;
    private EditText mLname;
    private EditText mPhone;
    private EditText mEmail;
    private EditText mPassword;
    private Spinner mTypeUser;
    private Button mRegister;
    User user;
    AppDatabase database;
    UserDao userDao;
    ArrayAdapter<String> spinnerArrayAdapter;
    private ArrayList<String> spinnerArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = Room.databaseBuilder(this, AppDatabase.class, "db-app")
                .allowMainThreadQueries()
                .build();

        userDao = database.getUserDao();

        user = new User();

        initView();

    }

    private void initView() {

        mFname = findViewById(R.id.fname);
        mLname = findViewById(R.id.lname);
        mPhone = findViewById(R.id.phone);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mTypeUser = findViewById(R.id.type_user);
        mRegister = findViewById(R.id.register);

        spinnerArray.add("Injured");
        spinnerArray.add("Doctor");
        spinnerArray.add("Family");

        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,spinnerArray);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mTypeUser.setAdapter(spinnerArrayAdapter);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mFname.getText().toString().isEmpty()) {

                    Toast.makeText(SignupActivity.this, "First Name is Empty !", Toast.LENGTH_SHORT).show();

                } else if (mLname.getText().toString().isEmpty()) {

                    Toast.makeText(SignupActivity.this, "Last Name is Empty !", Toast.LENGTH_SHORT).show();

                } else if (mPhone.getText().toString().isEmpty()) {

                    Toast.makeText(SignupActivity.this, "Phone is Empty !", Toast.LENGTH_SHORT).show();

                } else if (mEmail.getText().toString().isEmpty()) {

                    Toast.makeText(SignupActivity.this, "Email is Empty !", Toast.LENGTH_SHORT).show();

                } else if (mPassword.getText().toString().isEmpty()) {

                    Toast.makeText(SignupActivity.this, "Password is Empty !", Toast.LENGTH_SHORT).show();

                } else {

                    Register(mFname.getText().toString().trim(), mLname.getText().toString().trim(), mPhone.getText().toString().trim(),
                            mEmail.getText().toString().trim(), mPassword.getText().toString().trim(), mTypeUser.getSelectedItem().toString());

                    Log.e("mFname",mFname.getText().toString());
                    Log.e("mLname",mLname.getText().toString());
                    Log.e("mPhone",mPhone.getText().toString());
                    Log.e("mEmail", mEmail.getText().toString());
                    Log.e("mPassword", mPassword.getText().toString());
                    Log.e("mTypeUser",mTypeUser.getSelectedItem().toString());

                }

            }
        });


    }

    private void Register(final String fname, final String lname, final String phone, final String email, final String password, final String type) {

        user.setUsername(fname + " " + lname);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setType(type);

        userDao.insertAll(user);

        Toast.makeText(this, "Save information successfully ^_^", Toast.LENGTH_SHORT).show();

        finish();

        AppMain.getInstance().getSharedPref().writeString(SharedPref.APP_USERNAME, fname + " " + lname);


    }

}
