package com.example.kushagra.smartmeds.MoreAbout.MedicineDetails;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kushagra.smartmeds.MoreAbout.MedicineDetails.Class.Medicine;
import com.example.kushagra.smartmeds.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedicineDetailsActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private Context mContext;
    private DatabaseReference mDatabaseMedicine;

   private FloatingActionButton mFloatAdd;

      RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);


        mAuth = FirebaseAuth.getInstance();


        //floating button
        mFloatAdd = (FloatingActionButton)findViewById(R.id.float_add_meds);
        mFloatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToAddMeds();

            }
        });
        //


        //Recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.meds_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseUser current_user = mAuth.getCurrentUser();
        String uid = current_user.getUid();

        mDatabaseMedicine = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("medicine");


    }


    private void sendToAddMeds() {

        Intent intentToAddMeds = new Intent(MedicineDetailsActivity.this, AddNewMedActivity.class);
        startActivity(intentToAddMeds);
    }


    //----------------------RECYCLER VIEW--------------------------------//

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Medicine,MedicineViewHolder>
                firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medicine, MedicineViewHolder>(
                        Medicine.class,
                        R.layout.single_meds_layout,
                        MedicineViewHolder.class,
                        mDatabaseMedicine
        ) {
            @Override
            protected void populateViewHolder(MedicineViewHolder viewHolder, final Medicine medicine, int position) {

                viewHolder.setName(medicine.getMedName());
                viewHolder.setDesc(medicine.getMedDesc());


                //getting the position values
                final String med_id = getRef(position).getKey();
                //

                //one view
                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent medIntent = new Intent(MedicineDetailsActivity.this,MedicineDetailsActivity.class);

                    }
                });

//

            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MedicineViewHolder extends RecyclerView.ViewHolder{

        View view;

        public MedicineViewHolder(View itemView) {
            super(itemView);

            view = itemView;
        }

        public void setName(String medName){
            TextView medsNameView = view.findViewById(R.id.title_single_med);
            medsNameView.setText(medName);
        }

        public void setDesc(String medDes){
            TextView medsDescView = view.findViewById(R.id.desc_single_med);
            medsDescView.setText(medDes);


        }
    }

}
