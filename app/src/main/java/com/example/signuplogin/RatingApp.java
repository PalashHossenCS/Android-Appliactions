package com.example.signuplogin;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class RatingApp extends AppCompatActivity {

    RatingBar ratingBar ;
    TextView CurrRating ;
    String ratingValue ;
    Button submit ;
    String userNameGlobal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences mSharedPref;
        mSharedPref=getSharedPreferences("SharedPref",MODE_PRIVATE);
        userNameGlobal = mSharedPref.getString("userName", "null");

        setContentView(R.layout.activity_rating_app) ;
        submit = findViewById(R.id.submitRating) ;
        ratingBar = findViewById(R.id.rating) ;
        CurrRating = findViewById(R.id.currRating) ;


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingValue = String.valueOf(ratingBar.getRating())  ;
                Float NewRating  = Float.parseFloat(ratingValue) ;
                // Have to change the Rating value of this user in realtime databse .
                DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("ratings");


                referenceProfile.child(userNameGlobal).setValue(NewRating).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RatingApp.this," Rating has been updated ! ",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RatingApp.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }) ;
                // for ever click //

//                // Rating part with Firebase
//                referenceProfile = FirebaseDatabase.getInstance().getReference("ratings") ;
//                Query query = referenceProfile.orderByChild("ratingApp")  ;
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                    Float[] totalRating1 = {0.00F};
//                    Float[] totalUserGivenRating1 = {0.00F};
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                            Float Rating = userSnapshot.child("ratingApp").getValue(Float.class);
//                            if(Rating>=0.00F) {
//                                totalRating1[0] += Rating ;
//                                totalUserGivenRating1[0] += 1.0F ;
//                            }
//                        }
//                        Float RATING = 0.0F ;
//                        if(totalUserGivenRating1[0]>0.00F) {
//                            RATING += totalRating1[0] / totalUserGivenRating1[0] ;
//                        }
//                        CurrRating.setText("Current Rating :  " + Float.max(RATING,0.00F) );
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Toast.makeText(RatingApp.this,"Something Wrong .\n Try again ! ",Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });


        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("ratings") ;
        referenceProfile = FirebaseDatabase.getInstance().getReference("ratings");
        referenceProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double totalRating = 0.0;
                double count = 0.0;
                int dount=0;
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    totalRating += userSnapshot.getValue(double.class);
                    count+=1;
                    dount+=1;
                }


                DecimalFormat decimalFormat = new DecimalFormat("#.##");



                CurrRating.setText("Rating : and Number  " + Double.parseDouble(decimalFormat.format(totalRating/count ))+"  "+dount );

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RatingApp.this,"Something Wrong .\n Try again ! ",Toast.LENGTH_SHORT).show();
            }
        });


    }
}