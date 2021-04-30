package com.accoumeh.rachad.homeautomation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    // String IO = "ERROR";
    TextView tog1, tog2, tog3, tog4, pus1, pus2, pus3, pus4, valu1, valu2, valu3, valu4;
    ToggleButton but_one, but_two, but_three, but_four;
    Button pushbut1, pushbut2, pushbut3, pushbut4;
    TextView txtval1, txtval2, txtval3, txtval4;
    ImageButton imageButton;
    TextView textView;
    int myint = 0, comint = 0;
    ProgressDialog dialog;
    /*String toggle1 = "Data/user_one/but_one", toggle2 = "Data/user_one/but_two", toggle3 = "Data/user_one/but_three", toggle4 = "Data/user_one/but_four";
      String push1 = "Data/user_one/but_one", push2 = "Data/user_one/but_two", push3 = "Data/user_one/but_three", push4 = "Data/user_one/but_four";
      String val1 = "Data/user_one/but_one", val2 = "Data/user_one/but_two", val3 = "Data/user_one/but_three", val4 = "Data/user_one/but_four";               */
    String toggle1 = "null", toggle2 = "null", toggle3 = "null", toggle4 = "null";
    String push1 = "null", push2 = "null", push3 = "null", push4 = "null";
    String val1 = "null", val2 = "null", val3 = "null", val4 = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Home Automation", 0); // 0 - for private mode
        toggle1 = pref.getString("toggle1", "null"); // getting String
        toggle2 = pref.getString("toggle2", "null"); // getting String
        toggle3 = pref.getString("toggle3", "null"); // getting String
        toggle4 = pref.getString("toggle4", "null"); // getting String
        push1 = pref.getString("push1", "null"); // getting String
        push2 = pref.getString("push2", "null"); // getting String
        push3 = pref.getString("push3", "null"); // getting String
        push4 = pref.getString("push4", "null"); // getting String
        val1 = pref.getString("value1", "null"); // getting String
        val2 = pref.getString("value2", "null"); // getting String
        val3 = pref.getString("value3", "null"); // getting String
        val4 = pref.getString("value4", "null"); // getting String
        //  pref.getInt("key_name", -1); // getting Integer
        //  pref.getFloat("key_name", null); // getting Float
        //  pref.getLong("key_name", null); // getting Long
        //  pref.getBoolean("key_name", null); // getting boolean

        textView = findViewById(R.id.logout2);
        imageButton=findViewById(R.id.logout1);
        but_one = findViewById(R.id.but_one);
        but_two = findViewById(R.id.but_two);
        but_three = findViewById(R.id.but_three);
        but_four = findViewById(R.id.but_four);
        pushbut1 = findViewById(R.id.button1);
        pushbut2 = findViewById(R.id.button2);
        pushbut3 = findViewById(R.id.button3);
        pushbut4 = findViewById(R.id.button4);
        txtval1 = findViewById(R.id.value1);
        txtval2 = findViewById(R.id.value2);
        txtval3 = findViewById(R.id.value3);
        txtval4 = findViewById(R.id.value4);
        tog1 = findViewById(R.id.tg1);
        tog2 = findViewById(R.id.tg2);
        tog3 = findViewById(R.id.tg3);
        tog4 = findViewById(R.id.tg4);
        pus1 = findViewById(R.id.push1);
        pus2 = findViewById(R.id.push2);
        pus3 = findViewById(R.id.push3);
        pus4 = findViewById(R.id.push4);
        valu1 = findViewById(R.id.val1);
        valu2 = findViewById(R.id.val2);
        valu3 = findViewById(R.id.val3);
        valu4 = findViewById(R.id.val4);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    /*    if (IO.equals("ERROR")) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            IO = "811110000000000000111100000000000011110000000";
            //    012345678911111111112222222222333333333334444
            //              01234567890123456789012345657890123
            //     |               |               |
            //      Toggle button    push button     value
            //1to 17 Button
            // first char number of value must download it (read button / text view value) in default max 8
            //second char on/off first button 0=off 1toogle button 2=push button
            // third   //    //   second //
            // fourth  //    //    third //
            //fifth    //    //     fourth
            //18 first push button
            //34 first value
        }*/
        //comint = Integer.parseInt(String.valueOf(IO.charAt(0)));
        if (!toggle1.equals("null")) {
            comint++;
        }
        if (!toggle2.equals("null")) {
            comint++;
        }
        if (!toggle3.equals("null")) {
            comint++;
        }
        if (!toggle4.equals("null")) {
            comint++;
        }
        if (!val1.equals("null")) {
            comint++;
        }
        if (!val2.equals("null")) {
            comint++;
        }
        if (!val3.equals("null")) {
            comint++;
        }
        if (!val4.equals("null")) {
            comint++;
        }


        if (!toggle1.equals("null")) {
            read1();
            ToggleButton(but_one, 1);
        } else {
            but_one.setVisibility(View.GONE);
            tog1.setVisibility(View.GONE);
        }
        if (!toggle2.equals("null")) {
            read2();
            ToggleButton(but_two, 2);
        } else {
            but_two.setVisibility(View.GONE);
            tog2.setVisibility(View.GONE);
        }
        if (!toggle3.equals("null")) {
            read3();
            ToggleButton(but_three, 3);
        } else {
            but_three.setVisibility(View.GONE);
            tog3.setVisibility(View.GONE);
        }
        if (!toggle4.equals("null")) {
            read4();
            ToggleButton(but_four, 4);
        } else {
            but_four.setVisibility(View.GONE);
            tog4.setVisibility(View.GONE);
        }
        if (!push1.equals("null")) {
            pushbutton(pushbut1, 1);
        } else {
            pushbut1.setVisibility(View.GONE);
            pus1.setVisibility(View.GONE);
        }
        if (!push2.equals("null")) {
            pushbutton(pushbut2, 2);
        } else {
            pushbut2.setVisibility(View.GONE);
            pus2.setVisibility(View.GONE);
        }
        if (!push3.equals("null")) {
            pushbutton(pushbut3, 3);
        } else {
            pushbut3.setVisibility(View.GONE);
            pus3.setVisibility(View.GONE);
        }
        if (!push4.equals("null")) {
            pushbutton(pushbut4, 4);
        } else {
            pushbut4.setVisibility(View.GONE);
            pus4.setVisibility(View.GONE);
        }
        if (!val1.equals("null")) {
            readval1();
        } else {
            txtval1.setVisibility(View.GONE);
            valu1.setVisibility(View.GONE);
        }
        if (!val2.equals("null")) {
            readval2();
        } else {
            txtval2.setVisibility(View.GONE);
            valu2.setVisibility(View.GONE);
        }
        if (!val3.equals("null")) {
            readval3();
        } else {
            txtval3.setVisibility(View.GONE);
            valu3.setVisibility(View.GONE);
        }
        if (!val4.equals("null")) {
            readval4();
        } else {
            txtval4.setVisibility(View.GONE);
            valu4.setVisibility(View.GONE);
        }
        if (comint != 0) {
            dialog = ProgressDialog.show(MainActivity.this, "",
                    "Loading. Please wait...", true);
        }
    }


    public void pushbutton(Button b, int i) {
        b.setOnTouchListener((v, event) -> {
            String thePath = "";
            if (i == 1) {
                thePath = push1;
            } else if (i == 2) {
                thePath = push2;
            } else if (i == 3) {
                thePath = push3;
            } else if (i == 4) {
                thePath = push4;
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                basicWrite(1, thePath);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                basicWrite(0, thePath);
            }
            return false;
        });
    }

    //"Data/user_one/but_one"
    public void basicWrite(int value, String path) {
        // [START write_message]
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(path);
        myRef.setValue(value);
        // [END write_message]
    }


    public void ToggleButton(ToggleButton toggleButton, int i) {
        String thePath = "";
        if (i == 1) {
            thePath = toggle1;
        } else if (i == 2) {
            thePath = toggle2;
        } else if (i == 3) {
            thePath = toggle3;
        } else if (i == 4) {
            thePath = toggle4;
        }

        String finalThePath = thePath;
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The toggle is enabled
                basicWrite(1, finalThePath);
            } else {
                // The toggle is disabled
                basicWrite(0, finalThePath);
            }
        });

    }


    public void read1() {

        // [START read_message]
        // Read from the database
        DatabaseReference database1 = FirebaseDatabase.getInstance().getReference().child(toggle1);
        database1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String string = snapshot.getValue().toString();
                    myint++;
                    if (myint == comint) {
                        dialog.cancel();
                    }
                    if (string.equals("0")) {
                        but_one.setChecked(false);
                    } else if (string.equals("1")) {
                        but_one.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // [END read_message]
    }

    public void read2() {
        // [START read_message]
        // Read from the database
        DatabaseReference database2 = FirebaseDatabase.getInstance().getReference().child(toggle2);
        database2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String string = snapshot.getValue().toString();
                    myint++;
                    if (myint == comint) {
                        dialog.cancel();
                    }
                    if (string.equals("0")) {
                        but_two.setChecked(false);
                    } else if (string.equals("1")) {
                        but_two.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // [END read_message]
    }

    public void read3() {
        // [START read_message]
        // Read from the database
        DatabaseReference database3 = FirebaseDatabase.getInstance().getReference().child(toggle3);
        database3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String string = snapshot.getValue().toString();
                    myint++;
                    if (myint == comint) {
                        dialog.cancel();
                    }
                    if (string.equals("0")) {
                        but_three.setChecked(false);
                    } else if (string.equals("1")) {
                        but_three.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // [END read_message]
    }

    public void read4() {
        // [START read_message]
        // Read from the database
        DatabaseReference database4 = FirebaseDatabase.getInstance().getReference().child(toggle4);
        database4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String string = snapshot.getValue().toString();
                    myint++;
                    if (myint == comint) {
                        dialog.cancel();
                    }
                    if (string.equals("0")) {
                        but_four.setChecked(false);
                    } else if (string.equals("1")) {
                        but_four.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // [END read_message]

    }


    public void readval1() {

        // [START read_message]
        // Read from the database
        DatabaseReference database1 = FirebaseDatabase.getInstance().getReference().child(val1);
        database1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String string = snapshot.getValue().toString();
                    myint++;
                    if (myint == comint) {
                        dialog.cancel();
                    }
                    txtval1.setText(string);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // [END read_message]
    }

    public void readval2() {
        // [START read_message]
        // Read from the database
        DatabaseReference database2 = FirebaseDatabase.getInstance().getReference().child(val2);
        database2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String string = snapshot.getValue().toString();
                    myint++;
                    if (myint == comint) {
                        dialog.cancel();
                    }
                    txtval2.setText(string);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // [END read_message]
    }

    public void readval3() {
        // [START read_message]
        // Read from the database
        DatabaseReference database3 = FirebaseDatabase.getInstance().getReference().child(val3);
        database3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String string = snapshot.getValue().toString();
                    myint++;
                    if (myint == comint) {
                        dialog.cancel();
                    }
                    txtval3.setText(string);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // [END read_message]
    }

    public void readval4() {
        // [START read_message]
        // Read from the database
        DatabaseReference database4 = FirebaseDatabase.getInstance().getReference().child(val4);
        database4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String string = snapshot.getValue().toString();
                    myint++;
                    if (myint == comint) {
                        dialog.cancel();
                    }
                    txtval4.setText(string);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // [END read_message]

    }
    public void logout(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Home Automation", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("signedin", false); // Storing boolean - true/false
        editor.apply();
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        MainActivity.super.finish();

    }
}

