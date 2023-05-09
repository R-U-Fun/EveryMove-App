package everymoveapp.prototype;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Manage_Profile_Activity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView profileName,profileEmail,profileUsername,profilePassword,signupUsernameforDB;
    TextView titleName,titleUsername;

    Button Edit;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_profile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileUsername = findViewById(R.id.profileUsername);
        profilePassword = findViewById(R.id.profilePassword);
        titleName = findViewById(R.id.titleName);
        titleUsername = findViewById(R.id.titleUserName);
        Edit = findViewById(R.id.edit_button);
        signupUsernameforDB = findViewById(R.id.signup_username);


        showUserData();

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manage_Profile_Activity.this,Profile_Edit_Activity.class);
                startActivity(intent);
            }
        });

    }

    public void showUserData() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child("sara");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String nameUser = dataSnapshot.child("name").getValue().toString();
                String emailUser = dataSnapshot.child("email").getValue().toString();
                String usernameUser = dataSnapshot.child("username").getValue().toString();
                String passwordUser = dataSnapshot.child("password").getValue().toString();

                titleName.setText(nameUser);
                titleUsername.setText(usernameUser);

                profileEmail.setText(emailUser);
                profileName.setText(nameUser);
                profileUsername.setText(usernameUser);
                profilePassword.setText(passwordUser);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Manage_Profile_Activity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });







    }
}
