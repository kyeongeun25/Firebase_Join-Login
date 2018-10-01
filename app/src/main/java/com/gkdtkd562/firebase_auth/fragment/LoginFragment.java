package com.gkdtkd562.firebase_auth.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gkdtkd562.firebase_auth.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment implements View.OnClickListener {

    TextView txt_email;
    TextView txt_password;

    public LoginFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.login_fragment, container, false);

        txt_email = view.findViewById(R.id.login_txt_email);
        txt_password = view.findViewById(R.id.login_txt_password);
        Button btn_ok = view.findViewById(R.id.login_btn_ok);
        btn_ok.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View v) {

        String strEmail = txt_email.getText().toString();
        String strPassword = txt_password.getText().toString();

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    // 로그인 된 사용자 정보 가져오기
                    FirebaseUser user = mAuth.getCurrentUser();
                    String message = "Email : " + user.getEmail();
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "로그인 실패", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
