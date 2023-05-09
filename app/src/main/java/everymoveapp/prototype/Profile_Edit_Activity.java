package everymoveapp.prototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_Edit_Activity extends AppCompatActivity {

    EditText editName,editUsername,editEmail,editPassword;
    Button editBtn;
    String nameUser,emailUser,usernameUser,passwordUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editName = findViewById(R.id.edit_name);
        editUsername = findViewById(R.id.edit_username);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_Password);
        editBtn=findViewById(R.id.edit_button);

        showData();

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isEmailChanged() || isPasswordChanged()){
                    Toast.makeText(Profile_Edit_Activity.this, "Saved", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Profile_Edit_Activity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isNameChanged(){
        if(!nameUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isEmailChanged(){
        if(!emailUser.equals(editEmail.getText().toString())){
            reference.child(usernameUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isPasswordChanged(){
        if(!passwordUser.equals(editPassword.getText().toString())){
            reference.child(usernameUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    public void showData(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child("sara");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String nameUser = dataSnapshot.child("name").getValue().toString();
                String emailUser = dataSnapshot.child("email").getValue().toString();
                String usernameUser = dataSnapshot.child("username").getValue().toString();
                String passwordUser = dataSnapshot.child("password").getValue().toString();



                editEmail.setText(emailUser);
                editName.setText(nameUser);
                editUsername.setText(usernameUser);
                editPassword.setText(passwordUser);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Profile_Edit_Activity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}