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
import com.google.firebase.auth.SignInMethodQueryResult;

public class JoinFragment extends Fragment implements View.OnClickListener {

    TextView txt_join_email;
    TextView txt_join_password;

    FirebaseAuth mAuth;

    public JoinFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//        return inflater.inflate(R.layout.join_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();

        View view =  inflater.inflate(R.layout.join_fragment, container, false);

        txt_join_email = view.findViewById(R.id.join_txt_email);
        txt_join_password = view.findViewById(R.id.join_txt_password);

        Button btn_join_ok = view.findViewById(R.id.join_btn_ok);
        btn_join_ok.setOnClickListener(this);

//        btn_join_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "회원가입 버튼 클릭", Toast.LENGTH_LONG).show();
//            }
//        });

        return view;
    }

    @Override
    public void onClick(View v) {

        String strEmail = txt_join_email.getText().toString();
        String strPassword = txt_join_password.getText().toString();

        if(strEmail.isEmpty()){
            Toast.makeText(getContext(),"E-mail은 반드시 입력해야 합니다.", Toast.LENGTH_LONG).show();
            return;
        }

        if(strPassword.isEmpty()){
            Toast.makeText(getContext(),"비밀번호는 반드시 입력해야 합니다.", Toast.LENGTH_LONG).show();
            return;
        }

        // firebase에 같은 Email이 이미 등록되어있는지 검사
//        if(isNewUser(strEmail)){
//            return;
//        }

        // 없으면 firebase에 Email과 비밀번호를 등록
        mAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(), "회원 가입 성공", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "회원 가입 실패", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // return값이 true이면 아직 등록되지 않은 email이므로 등록가능
    boolean isNewUser = false;
    private boolean isNewUser(String email){

        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                // 이미 등록된 Email이 있으면
                if(task.isComplete()) {
                    Toast.makeText(getContext(),"이미 등록된 E-mail 입니다.", Toast.LENGTH_LONG).show();
                    isNewUser = false;
                    return;
                }
                isNewUser = true;
            }
        });
        return isNewUser;
    }

}
