package com.ehgmrr.ui.reset;

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

public class ResetActivity extends AppCompatActivity {

    private EditText mEmail;
    private Button mReset;
    private EditText mNewPassword;

    User user;
    AppDatabase database;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        database = Room.databaseBuilder(this, AppDatabase.class, "db-app")
                .allowMainThreadQueries()
                .build();

        userDao = database.getUserDao();

        user = new User();

        initView();
    }

    private void initView() {

        mEmail = findViewById(R.id.email);
        mReset = findViewById(R.id.reset);
        mNewPassword = findViewById(R.id.new_password);

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEmail.getText().toString().isEmpty() || mEmail.getText().toString().isEmpty()) {

                    Toast.makeText(ResetActivity.this, "Enter all feilds !", Toast.LENGTH_SHORT).show();

                } else {

                    reset(mEmail.getText().toString(),mNewPassword.getText().toString());

                }

            }
        });


    }

    private void reset(String email , String password) {

        User result = userDao.findByEmail(email);

        if (result != null) {

            result.setPassword(password);

            Toast.makeText(this, "Success ^_^", Toast.LENGTH_SHORT).show();

            userDao.update(result);

            finish();

        } else {

            Toast.makeText(this, "Not Found @_@", Toast.LENGTH_SHORT).show();

        }

    }

}
