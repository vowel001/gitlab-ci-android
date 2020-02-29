package com.ehgmrr.ui.viewPatient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.ehgmrr.R;
import com.ehgmrr.db.AppDatabase;
import com.ehgmrr.db.UserDao;
import com.ehgmrr.pojo.User;
import com.ehgmrr.utils.AppMain;
import com.ehgmrr.utils.SharedPref;

public class ViewPatientActivity extends AppCompatActivity {

    /**
     * Empty
     */
    private TextView mName;
    /**
     * Empty
     */
    private TextView mForce;
    /**
     * Empty
     */
    private TextView mFlex;
    /**
     * Empty
     */
    private TextView mBp;
    /**
     * Empty
     */
    private TextView mHr;
    /**
     * View patient information
     */
    private Button mViewPatInfo;

    User user;
    AppDatabase database;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);

        database = Room.databaseBuilder(this, AppDatabase.class, "db-app")
                .allowMainThreadQueries()
                .build();

        userDao = database.getUserDao();

        user = new User();

        initView();

        final String username = getIntent().getStringExtra("username");

        mViewPatInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfoUsername(username);
            }
        });

    }

    private void initView() {
        mName = (TextView) findViewById(R.id.name);
        mForce = (TextView) findViewById(R.id.force);
        mFlex = (TextView) findViewById(R.id.flex);
        mBp = (TextView) findViewById(R.id.bp);
        mHr = (TextView) findViewById(R.id.hr);
        mViewPatInfo = (Button) findViewById(R.id.view_pat_info);

    }

    private void getUserInfoUsername (String username) {

        User info = userDao.findByUsername(username);

        if (info !=null) {

            mName.setText(info.getUsername());

        } else {

            Toast.makeText(this, "Username not found !", Toast.LENGTH_SHORT).show();

        }


    }

    private void getUserInfoPhone (String phone) {

        User info = userDao.findByPhone(phone);

        if (info !=null) {

            mName.setText(info.getUsername());

        } else {

            Toast.makeText(this, "Phone not found !", Toast.LENGTH_SHORT).show();

        }


    }
}
