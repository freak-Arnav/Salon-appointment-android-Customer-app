package com.example.pro_q;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.concurrent.TimeUnit;

public class PhoneVerificationActivity extends AppCompatActivity
{
    //for carousel
    private int[] mImages = new int[] {
            R.drawable.carousel1, R.drawable.carousel2, R.drawable.carousel3, R.drawable.carousel4,
            R.drawable.carousel5, R.drawable.carousel6, R.drawable.carousel7
    };
    //for carousel

    private TextView country_code, resendtxt;
    private EditText get_phone, enter_otp;
    private Button generate, verify_btn, changeno, resendotp;
    String codeSent;
    String phone_no;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        resendtxt = (TextView) findViewById(R.id.resend_text);
        country_code = (TextView) findViewById(R.id.country_code);
        get_phone = (EditText) findViewById(R.id.get_phone);
        enter_otp = (EditText) findViewById(R.id.otp);
        generate = (Button) findViewById(R.id.otp_generate_btn);
        verify_btn = (Button) findViewById(R.id.verify_btn);
        changeno = (Button) findViewById(R.id.change_no);
        resendotp = (Button) findViewById(R.id.resend_otp);
        loadingBar = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        CarouselView carouselView = findViewById(R.id.slide);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });

        changeno.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(PhoneVerificationActivity.this,PhoneVerificationActivity.class);
                startActivity(intent);
            }
        });

        resendotp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                enter_otp.setVisibility(view.VISIBLE);
                generate.setVisibility(view.INVISIBLE);
                verify_btn.setVisibility(view.VISIBLE);
                get_phone.setVisibility(view.INVISIBLE);
                country_code.setVisibility(view.INVISIBLE);
                resendtxt.setVisibility(view.VISIBLE);
                changeno.setVisibility(view.VISIBLE);
                resendotp.setVisibility(view.VISIBLE);
                SendOTP();
            }
        });

        generate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                phone_no = "+91"+get_phone.getText().toString();

                if (TextUtils.getTrimmedLength(phone_no)!=13)
                {
                    Toast.makeText(PhoneVerificationActivity.this, "Please write your Phone number...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    enter_otp.setVisibility(view.VISIBLE);
                    generate.setVisibility(view.INVISIBLE);
                    verify_btn.setVisibility(view.VISIBLE);
                    get_phone.setVisibility(view.INVISIBLE);
                    country_code.setVisibility(view.INVISIBLE);
                    resendtxt.setVisibility(view.VISIBLE);
                    changeno.setVisibility(view.VISIBLE);
                    resendotp.setVisibility(view.VISIBLE);
                    SendOTP();
                }
            }
        });

        verify_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                VerifyPhone();
            }
        });
    }

    private void SendOTP()
    {
        phone_no = "+91"+get_phone.getText().toString();

//        if (!TextUtils.isDigitsOnly(phone_no))
//        {
//            Toast.makeText(this, "Please try again with a Phone number...", Toast.LENGTH_SHORT).show();
//        }
        if (TextUtils.isEmpty(phone_no))
        {
            Toast.makeText(this,"Please write your Phone number...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.getTrimmedLength(phone_no)!=13)
        {
            Toast.makeText(this, "Please try again with a valid Phone number...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phone_no,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    PhoneVerificationActivity.this,               // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacks
        }
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
        {

        }

        @Override
        public void onVerificationFailed(FirebaseException e)
        {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
        {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
    };

    private void VerifyPhone()
    {
        String code = enter_otp.getText().toString();
        if(TextUtils.isEmpty(code))
        {
            Toast.makeText(this, "Please enter your OTP here...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent , code );
            loadingBar.setTitle("Verifying Phone");
            loadingBar.setMessage("Please wait while your OTP gets verified...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            SignInWithPhoneAuthCredential(credential);
        }
    }

    private void SignInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {

                    Toast.makeText(PhoneVerificationActivity.this, "Phone number verified", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent intent = new Intent(PhoneVerificationActivity.this,RegisterActivity.class);
                    intent.putExtra("phone",phone_no);
                    startActivity(intent);
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                    {
                        loadingBar.dismiss();
                        Toast.makeText(PhoneVerificationActivity.this, "Enter a valid OTP", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
