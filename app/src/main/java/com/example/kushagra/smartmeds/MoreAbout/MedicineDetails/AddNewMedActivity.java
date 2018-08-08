package com.example.kushagra.smartmeds.MoreAbout.MedicineDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kushagra.smartmeds.MoreAbout.MedicineDetails.Class.Medicine;
import com.example.kushagra.smartmeds.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewMedActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private DatabaseReference mDatabaseMedicine;


    EditText mMedTitle;
    EditText mMedDes;
    Button mMedBtnAdd;

    Button mAddNewMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_med);


        //fields
        mMedDes = (EditText) findViewById(R.id.et_addMedDes);
        mMedTitle = (EditText) findViewById(R.id.et_addMedTitle);
        mMedBtnAdd = (Button) findViewById(R.id.btn_addMedSave);


        //Firebase
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current_user.getUid();

        mDatabaseMedicine = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("medicine");

        mMedBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hide keyboard
                hideKeyboard();


                //pass function to add to database
                addMedicineToDatabase();

                //reset views
                mMedTitle.setText("");
                mMedDes.setText("");



            }
        });


    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    }

    private void addMedicineToDatabase() {


        String medName = mMedTitle.getText().toString();
        String medDesc = mMedDes.getText().toString();


        if (!TextUtils.isEmpty(medName)||!TextUtils.isEmpty(medDesc)) {


            //making id to make unique id everytime we add medine details
            //is used the id value itself it will overwrite previous data
            String id = mDatabaseMedicine.push().getKey();

            //making obj of class model we made and giving it 2 paremeters
            Medicine medicine = new Medicine(medName, medDesc);

            //in medsdatabase inside a unique medicine id we setvalues of medicine details
            mDatabaseMedicine.child(id).setValue(medicine);


            Toast.makeText(this, "Medicine Details Added", Toast.LENGTH_SHORT).show();

        }
        else {

            Toast.makeText(this, "Give full details", Toast.LENGTH_SHORT).show();
        }



    }
}
