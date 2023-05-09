package everymoveapp.prototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupUsername ,signupPassword, passwordConfirmation;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseAuth database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighnup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        passwordConfirmation = findViewById(R.id.Confirmation_Password);
        signupButton = findViewById(R.id.sighnup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        database = FirebaseAuth.getInstance();


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                String ConfirmationPassword = passwordConfirmation.getText().toString();

                if (!password.equals(ConfirmationPassword)){
                    Toast.makeText(SignupActivity.this, "Please Check Both Password", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password) && TextUtils.isEmpty(ConfirmationPassword)) {
                    Toast.makeText(SignupActivity.this, "Please add your Credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    database.createUserWithEmailAndPassword(username , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignupActivity.this, "You have sign up successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(SignupActivity.this, "Failed to signup", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}