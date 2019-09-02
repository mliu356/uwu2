package com.example.michelleliu.uwu2;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    public String id;
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

    }

    public User(String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.points = points = 0;
        this.omo = omo = 0;
        this.colon3 = colon3 = 0;
        this.odoto = odoto = 0;
        this.caretsmile = caretsmile = 0;
        this.hewwo = hewwo = 0;
        this.owo = owo = 0;
        this.uwu = uwu = 0;
    }


    // Get a reference to our users
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("users");
}
