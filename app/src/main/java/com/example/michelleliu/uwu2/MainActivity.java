package com.example.michelleliu.uwu2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.res.ColorStateList;
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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference totalScoreRef = database.getReference("total_score");
    DatabaseReference myScoreRef = database.getReference("my_score");
//    DatabaseReference[] buttonRefs = new DatabaseReference[7];

    String TAG = "HELPMEIDK";
    int myScore;
    int totalScore;

    int[] pointValues = new int[]{8, 6, 10, 1, 15, 4, 3};
    String[] buttonPaths = new String[]{
            "hewwo", "caretsmile", "owo", "omo", "uwu", "odoto", "colon3"};
    String[] buttonNames = new String[]{
            "hewwo", "^_^", "owo", "omo", "uwu", "o.o", ":3"};
    int[] count = new int[7];
    Button[] buttons = new Button[8];

    TextView myPointsText;
    TextView totalPointsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);

        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(0);
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(1);
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(2);
            }
        });
        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(3);
            }
        });
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(4);
            }
        });
        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(5);
            }
        });
        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(6);
            }
        });
        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myScore = 0;
                myScoreRef.setValue(myScore);
                updateView();
                clearAllCounts();
            }
        });

        myPointsText = findViewById(R.id.pointsnum);
        totalPointsText = findViewById(R.id.globalpointsnum);

        totalScoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                totalScore = dataSnapshot.getValue(Integer.class) != null
                        ? dataSnapshot.getValue(Integer.class) : 0;
                updateView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
        clearAllCounts();

    }

    public ValueAnimator buildButtonAnimation(final int buttonNum) {
        final int colorFrom = getResources().getColor(R.color.colorPrimary);
        int colorTo = getResources().getColor(R.color.white);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(5000); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                if (android.os.Build.VERSION.SDK_INT > 21)
                    buttons[buttonNum].setBackgroundTintList(ColorStateList.valueOf((int) animator.getAnimatedValue()));
            }

        });
        colorAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                buttons[buttonNum].setClickable(true);
                if (android.os.Build.VERSION.SDK_INT > 21) {
                    buttons[buttonNum].setBackgroundTintList(ColorStateList.valueOf(colorFrom));
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                buttons[buttonNum].setClickable(false);
            }
        });
        return colorAnimation;
    }

    public void clickButton(final int buttonNum) {
        ValueAnimator colorAnimation = buildButtonAnimation(buttonNum);
        colorAnimation.start();
        int initialTotalScore = totalScore;
        int initialMyScore = myScore;
        totalScore += pointValues[buttonNum];
        myScore += pointValues[buttonNum];
        count[buttonNum]++;
        updateView();
        database.getReference(buttonPaths[buttonNum]).setValue(count[buttonNum]);
        totalScoreRef.setValue(totalScore);
        myScoreRef.setValue(myScore);

        if (count[buttonNum] % 5 == 0) {
            showSingleButtonDialog(buttonNum, count[buttonNum]);
        } else if (initialMyScore % 100 > myScore % 100) {
            showPersonalScoreDialog(myScore);
        } else if (initialTotalScore % 150 > totalScore % 150) {
            showGlobalScoreDialog(totalScore);
        }
    }

    public void updateView() {
        myPointsText.setText(Integer.toString(myScore));
        totalPointsText.setText(Integer.toString(totalScore));
    }

    private void clearAllCounts() {
        for (String path : buttonPaths) {
            database.getReference(path).setValue(0);
        }
    }

    private void showSingleButtonDialog(int buttonNum, int count) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("you've " + buttonNames[buttonNum] + "'d " + count + " times")
                .setTitle(buttonNames[buttonNum] + " overload!!");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showPersonalScoreDialog(int myScore) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("woah-- u just hit " + myScore + " uwu points!")
                .setTitle("on the up");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showGlobalScoreDialog(int totalScore) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("u collected the " + totalScore / 100 * 100 + "th uwu point!")
                .setTitle("team player");
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
