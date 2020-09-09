package com.example.myhealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UserProfile extends AppCompatActivity {

    // Variables used for this class:
    private static final String TAG = "UserProfile";
    private ListView listDetails;
    private String userID;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialising each variable according to their ID's on the XML page:
        listDetails = findViewById(R.id.list_user_details);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            UserInformation uInfo = new UserInformation();
            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());  //Sets the name
            uInfo.setSurname(ds.child(userID).getValue(UserInformation.class).getSurname()); //Sets the surname
            uInfo.setGender(ds.child(userID).getValue(UserInformation.class).getGender()); //Sets the gender
            uInfo.setContact(ds.child(userID).getValue(UserInformation.class).getContact()); //Sets the contact number
            uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail()); //Sets the email address
            uInfo.setPassword(ds.child(userID).getValue(UserInformation.class).getPassword()); //Sets the password

            Log.d(TAG, "showData name: " + uInfo.getName());
            Log.d(TAG, "showData surname: " + uInfo.getSurname());
            Log.d(TAG, "showData gender: " + uInfo.getGender());
            Log.d(TAG, "showData contact: " + uInfo.getContact());
            Log.d(TAG, "showData email: " + uInfo.getEmail());
            Log.d(TAG, "showData password: " + uInfo.getPassword());

            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(uInfo.getName());
            arrayList.add(uInfo.getSurname());
            arrayList.add(uInfo.getGender());
            arrayList.add(uInfo.getContact());
            arrayList.add(uInfo.getEmail());
            arrayList.add(uInfo.getPassword());

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
            listDetails.setAdapter(adapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.addAuthStateListener(listener);
    }
}
