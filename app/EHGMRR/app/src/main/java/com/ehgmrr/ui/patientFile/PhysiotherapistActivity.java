package com.ehgmrr.ui.patientFile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ehgmrr.R;
import com.ehgmrr.ui.filePatient.FilePatientActivity;
import com.ehgmrr.ui.update.UpdateActivity;
import com.ehgmrr.ui.viewPatient.ViewPatientActivity;

public class PhysiotherapistActivity extends AppCompatActivity {

    /**
     * patinet name or phone
     */
    private EditText mPhysNameOrPhone;
    /**
     * View patient status
     */
    private Button mPatientStatus;
    /**
     * View all patients record
     */
    private Button mViewRecord;
    /**
     * Update Account
     */
    private Button mUpdateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physiotherapist_file);
        initView();
    }

    private void initView() {

        mPhysNameOrPhone = findViewById(R.id.phys_name_or_phone);
        mPatientStatus = findViewById(R.id.patient_status);
        mViewRecord = findViewById(R.id.view_record);
        mUpdateAccount = findViewById(R.id.update_account);

        mViewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysiotherapistActivity.this, FilePatientActivity.class);
                startActivity(intent);
            }
        });

        mPatientStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPhysNameOrPhone.getText().toString().isEmpty()) {

                    Toast.makeText(PhysiotherapistActivity.this, "patinet name is empty !", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(PhysiotherapistActivity.this, ViewPatientActivity.class);
                    intent.putExtra("username",mPhysNameOrPhone.getText().toString());
                    startActivity(intent);

                }


            }
        });

        mUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysiotherapistActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });

    }
}
