package com.example.michelleliu.uwu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();


    @Override @Exclude
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseReference = mDatabase.getReference().child("name");
        mDatabaseReference.setValue("Donald Duck");
        Button btn = (Button)findViewById(R.id.temp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        //myRef.setValue("Hello, World!");
        //writeNewUser("0", "angi", "angi@angi.com");
    }

    /**
    private void setNewUser(String userId, String name, String email) {
        User user = new User(userId, name, email);
        myRef.child("users").child(userId).setValue(user);
    }**/

}
