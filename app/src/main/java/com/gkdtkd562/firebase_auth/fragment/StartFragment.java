package com.gkdtkd562.firebase_auth.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gkdtkd562.firebase_auth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartFragment extends Fragment {

    TextView txt_message;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    public StartFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//        return inflater.inflate(R.layout.start_fragment, container, false);

        View view = inflater.inflate(R.layout.start_fragment, container, false);

        txt_message = view.findViewById(R.id.start_message);

        mUser = mAuth.getCurrentUser();

        if(mUser != null){
            txt_message.setText("로그인 : " + mUser.getEmail());
            txt_message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                }
            });
        }

        return view;
    }
}
