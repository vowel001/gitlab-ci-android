package com.ehgmrr.ui.update;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.ehgmrr.R;
import com.ehgmrr.db.AppDatabase;
import com.ehgmrr.db.UserDao;
import com.ehgmrr.pojo.User;
import com.ehgmrr.ui.signup.SignupActivity;
import com.ehgmrr.utils.AppMain;
import com.ehgmrr.utils.SharedPref;

public class UpdateActivity extends AppCompatActivity {

    /**
     * first name
     */
    private EditText mFname;
    /**
     * phone
     */
    private EditText mPhone;
    /**
     * email
     */
    private EditText mEmail;
    /**
     * Password
     */
    private EditText mPassword;
    /**
     * Update
     */
    private Button mUpdateInfo;

    User user;
    AppDatabase database;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = Room.databaseBuilder(this, AppDatabase.class, "db-app")
                .allowMainThreadQueries()
                .build();

        userDao = database.getUserDao();

        user = new User();

        initView();

        getUserInfo();

    }

    private void initView() {
        mFname = findViewById(R.id.fname);
        mPhone = findViewById(R.id.phone);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mUpdateInfo = findViewById(R.id.update_info);

        mUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mFname.getText().toString().isEmpty()) {

                    Toast.makeText(UpdateActivity.this, "Username is Empty !", Toast.LENGTH_SHORT).show();

                }  else if (mPhone.getText().toString().isEmpty()) {

                    Toast.makeText(UpdateActivity.this, "Phone is Empty !", Toast.LENGTH_SHORT).show();

                } else if (mEmail.getText().toString().isEmpty()) {

                    Toast.makeText(UpdateActivity.this, "Email is Empty !", Toast.LENGTH_SHORT).show();

                } else if (mPassword.getText().toString().isEmpty()) {

                    Toast.makeText(UpdateActivity.this, "Password is Empty !", Toast.LENGTH_SHORT).show();

                } else {

                    updateAccount(mFname.getText().toString().trim() , mPhone.getText().toString().trim(),
                            mEmail.getText().toString().trim(), mPassword.getText().toString().trim());

                }

            }
        });

    }

    private void getUserInfo () {

        User info = userDao.findByUsername(AppMain.getInstance().getSharedPref().readString(SharedPref.APP_USERNAME));

        mFname.setText(info.getUsername());
        mPhone.setText(info.getPhone());
        mEmail.setText(info.getEmail());
        mPassword.setText(info.getPassword());

    }

    private void updateAccount(final String username, final String phone, final String email, final String password) {

        User result = userDao.findByUsername(AppMain.getInstance().getSharedPref().readString(SharedPref.APP_USERNAME));

        result.setUsername(username);
        result.setPhone(phone);
        result.setEmail(email);
        result.setPassword(password);

        userDao.update(user);

        Toast.makeText(this, "Update information successfully ^_^", Toast.LENGTH_SHORT).show();

        finish();

        AppMain.getInstance().getSharedPref().writeString(SharedPref.APP_USERNAME,username);


    }
}
