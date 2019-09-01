package com.example.michelleliu.uwu2;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    public String username;
    public String email;
    public int points;
    public int omo;
    public int colon3;
    public int odoto;
    public int caretsmile;
    public int hewwo;
    public int owo;
    public int uwu;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
        this.points = points;
        this.omo = omo;
        this.colon3 = colon3;
        this.odoto = odoto;
        this.caretsmile = caretsmile;
        this.hewwo = hewwo;
        this.owo = owo;
        this.uwu = uwu;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Get a reference to our users
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("points");
}
