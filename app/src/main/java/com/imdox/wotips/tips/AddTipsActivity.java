package com.imdox.wotips.tips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imdox.wotips.R;
import com.imdox.wotips.support.AppController;

import java.util.Date;

public class AddTipsActivity extends AppCompatActivity {

    private static DatabaseReference databaseRefObject;
    private EditText edtUserMobile,edtUserEmail,edtUserName,edtDisplayName,edtTitle,edtContent;
    private Button btnSaveTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tips);
        FirebaseApp.initializeApp(AddTipsActivity.this);
        databaseRefObject = FirebaseDatabase.getInstance().getReference(getString(R.string.tagTipsNode));

        edtUserMobile = findViewById(R.id.edtUserMobile);
        edtUserEmail = findViewById(R.id.edtUserEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtDisplayName = findViewById(R.id.edtDisplayName);
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        btnSaveTips = findViewById(R.id.btnSaveTips);


        edtUserMobile.setTypeface(AppController.getDefaultFont(AddTipsActivity.this));
        edtUserEmail.setTypeface(AppController.getDefaultFont(AddTipsActivity.this));
        edtUserName.setTypeface(AppController.getDefaultFont(AddTipsActivity.this));
        edtDisplayName.setTypeface(AppController.getDefaultFont(AddTipsActivity.this));
        edtTitle.setTypeface(AppController.getDefaultFont(AddTipsActivity.this));
        edtContent.setTypeface(AppController.getDefaultFont(AddTipsActivity.this));
        btnSaveTips.setTypeface(AppController.getDefaultFont(AddTipsActivity.this));

        btnSaveTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFillAllDetails = false;
                View focusView = null;

                if (TextUtils.isEmpty(edtContent.getText().toString().trim())) {
                    edtContent.setError(getString(R.string.emptyString));
                    focusView = edtContent;
                    isFillAllDetails = true;
                } else if (edtContent.getText().toString().trim().length()<20) {
                    edtContent.setError(getString(R.string.emptyString));
                    focusView = edtContent;
                    isFillAllDetails = true;
                }

                if (TextUtils.isEmpty(edtTitle.getText().toString().trim())) {
                    edtTitle.setError(getString(R.string.emptyString));
                    focusView = edtTitle;
                    isFillAllDetails = true;
                }


                if (TextUtils.isEmpty(edtUserName.getText().toString().trim())) {
                    edtUserName.setError(getString(R.string.emptyString));
                    focusView = edtUserName;
                    isFillAllDetails = true;
                }

                if (TextUtils.isEmpty(edtDisplayName.getText().toString().trim())) {
                    edtDisplayName.setError(getString(R.string.emptyString));
                    focusView = edtDisplayName;
                    isFillAllDetails = true;
                }

                // Check for a valid email address.
                if (TextUtils.isEmpty(edtUserEmail.getText().toString().trim())) {
                    edtUserEmail.setError(getString(R.string.emptyString));
                    focusView = edtUserEmail;
                    isFillAllDetails = true;
                } else if (!isEmailValid(edtUserEmail.getText().toString().trim())) {
                    edtUserEmail.setError(getString(R.string.errorInvalidEmail));
                    focusView = edtUserEmail;
                    isFillAllDetails = true;
                }

                // Check for a valid Mobile
                if (TextUtils.isEmpty(edtUserMobile.getText().toString().trim())) {
                    edtUserMobile.setError(getString(R.string.emptyString));
                    focusView = edtUserMobile;
                    isFillAllDetails = true;
                } else if (!hasMobileText(edtUserMobile)) {
                    edtUserMobile.setError(getString(R.string.errorValidMobile));
                    focusView = edtUserMobile;
                    isFillAllDetails = true;
                }


                if (isFillAllDetails) {
                    focusView.requestFocus();
                } else {
                    addTipsToFireBase();
                }
            }
        });
    }

    private void addTipsToFireBase(){
        try{
            String tipsId = databaseRefObject.push().getKey();
            UserTips userTips=new UserTips(edtUserMobile.getText().toString().trim(),edtUserEmail.getText().toString().trim(),
                    edtUserName.getText().toString().trim(),edtDisplayName.getText().toString().trim(),"General",edtTitle.getText().toString().trim()
                    ,edtContent.getText().toString().trim(),"1",String.valueOf(new Date()));
            // pushing user to 'user_details' node using the userId
            databaseRefObject.child(tipsId).setValue(userTips);
            Toast.makeText(AddTipsActivity.this,"Tips Added successfully.",Toast.LENGTH_SHORT).show();
            finish();

        }catch (Exception e){}
    }

    // Check valid email address
    private static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    // Check valid mobile number
    public static boolean hasMobileText(EditText editText) {
        String text = editText.getText().toString().trim();
        editText.setError(null);
        if (text.length() == 0 || text.length()<10) {
            return false;
        }
        return true;
    }
}
