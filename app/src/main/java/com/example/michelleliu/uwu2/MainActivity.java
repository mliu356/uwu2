package com.example.michelleliu.uwu2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user1");

    String TAG = "HELPMEIDK";
    String message = "tester message";
    int score;
    int[] pointValues = new int[]{8, 6, 10, 1, 15, 4, 3};
    int[] count = new int[7];

    Button button0; // hewwo - 8
    Button button1; // ^_^ - 6
    Button button2; // owo - 10
    Button button3; // omo - 1
    Button button4; // uwu - 15
    Button button5; // o.o - 4
    Button button6; // :3 - 3
    Button button7; // .___. clear

    TextView pointsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(5);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(6);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                myRef.setValue(score);
                updateView();
            }
        });

        pointsText = findViewById(R.id.pointsnum);

        updateView();

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                message = dataSnapshot.getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", databaseError.toException());
//
//            }
//        });
//        myRef.setValue("Hello, World!");
//        myRef.setValue("idk what i'm doing omo");
//
//        Log.d(TAG, "what is going on???");
//        Log.d(TAG, message);

    }

    public void clickButton(int buttonNum) {
        score += pointValues[buttonNum];
        count[buttonNum]++;
        updateView();

        myRef.setValue(score);
    }

    public void updateView() {
        pointsText.setText(Integer.toString(score));
    }
}
