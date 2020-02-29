package com.ehgmrr.ui.filePatient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.ehgmrr.R;
import com.ehgmrr.adapter.ViewPatentAdapter;
import com.ehgmrr.db.AppDatabase;
import com.ehgmrr.db.UserDao;
import com.ehgmrr.pojo.User;
import com.ehgmrr.ui.googleMap.MapsActivity;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class FilePatientFamilyActivity extends AppCompatActivity {

    private RecyclerView mRvUsers;
    /**
     * View patient status
     */
    private Button mViewStatus;
    /**
     * find location
     */
    private Button mFindLocation;

    private ViewPatentAdapter viewPatentAdapter;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private AlphaInAnimationAdapter alphaAdapter;

    User user;
    AppDatabase database;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_patient);
        initView();
    }

    private void initView() {
        mRvUsers = (RecyclerView) findViewById(R.id.rv_users);
        mViewStatus = (Button) findViewById(R.id.view_status);
        mFindLocation = (Button) findViewById(R.id.find_location);

        mViewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        mFindLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilePatientFamilyActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getData() {

        userArrayList.clear();

        database = Room.databaseBuilder(this, AppDatabase.class, "db-app")
                .allowMainThreadQueries()
                .build();

        userDao = database.getUserDao();

        user = new User();

        userArrayList.addAll(userDao.getAll());

        mRvUsers.setLayoutManager(new LinearLayoutManager(FilePatientFamilyActivity.this, RecyclerView.VERTICAL, false));
        viewPatentAdapter = new ViewPatentAdapter(FilePatientFamilyActivity.this, userArrayList);
        alphaAdapter = new AlphaInAnimationAdapter(viewPatentAdapter);
        mRvUsers.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
        viewPatentAdapter.notifyDataSetChanged();

    }

}
