package everymoveapp.prototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class LoginActivity extends AppCompatActivity {
    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;

    FirebaseAuth database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        database = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userUsername = loginUsername.getText().toString();
                String userPassword = loginPassword.getText().toString();
                if (TextUtils.isEmpty(userUsername)) {
                    loginUsername.setError("Username cannot be empty");
                    return;
                } else if (TextUtils.isEmpty(userPassword)){
                    loginPassword.setError("Password cannot be empty");
                    return;
                }
                else {
                    database.signInWithEmailAndPassword(userUsername,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Successful...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = database.getCurrentUser();

        if (user != null) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}