package com.example.pro_q;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputEmailID, InputPassword;
    private ProgressDialog loadingBar;

    String name, phone, emailID, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        CreateAccountButton = (Button) findViewById(R.id.register_btn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputEmailID = (EditText) findViewById(R.id.register_emailid_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CreateAccount();
            }
        });

    }

    private void CreateAccount()
    {
         name = InputName.getText().toString();
         emailID = InputEmailID.getText().toString();
         password = InputPassword.getText().toString();
         String pass[] = TextUtils.split(password," ");
        String S1[] = TextUtils.split(emailID,"@");
        String S2="";
        if (S1.length==2)
        {
            S2 = S1[1].substring(0,S1[1].length()-4);
        }

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please write your name...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(emailID))
        {
            Toast.makeText(this,"Please write your emailID...",Toast.LENGTH_SHORT).show();
        }
        else if (S1.length!=2)
        {
            Toast.makeText(this, "Please try again with a valid emailID...", Toast.LENGTH_SHORT).show();
        }
        else if (S2.contains("."))
        {
            Toast.makeText(this, "Please try with a valid emailID...", Toast.LENGTH_SHORT).show();
        }
        else if (!S1[1].endsWith(".com"))
        {
            Toast.makeText(this, "Please try again with valid emailID...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please write your password...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.getTrimmedLength(password)<8)
        {
            Toast.makeText(this, "Invalid Password : Password should be of atleast 8 characters...", Toast.LENGTH_SHORT).show();
        }
        else if (pass.length>1)
        {
            Toast.makeText(this, "Invalid Password : Spaces are not allowed in passwords", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(name,phone,emailID,password);
        }
    }


    private void ValidatephoneNumber(final String name, final String phone, final String emailID, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String,Object> userdataMap = new HashMap<>();
                    userdataMap.put("name", name);
                    userdataMap.put("password", password);
                    userdataMap.put("phone", phone);
                    userdataMap.put("emailID", emailID);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this, "Congratulations your account has been created.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                loadingBar.dismiss();
                                Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Account with "+phone+" already exists.",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using different phone number", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
