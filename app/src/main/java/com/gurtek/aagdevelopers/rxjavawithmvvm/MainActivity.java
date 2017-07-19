package com.gurtek.aagdevelopers.rxjavawithmvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.gurtek.aagdevelopers.rxjavawithmvvm.databinding.ActivityMainBinding;
import com.gurtek.aagdevelopers.rxjavawithmvvm.viewmodel.PeopleViewModel;

public class MainActivity extends AppCompatActivity {


    PeopleViewModel peopleViewModel;
    private ActivityMainBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();

        viewDataBinding.findIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Patterns.EMAIL_ADDRESS.matcher(viewDataBinding.emailBox.getText().toString()).matches()){
                    peopleViewModel.findUserByemail(viewDataBinding.emailBox.getText().toString());
                }else {
                    viewDataBinding.emailBox.setError("Enter valid email");
                }
            }
        });



    }

    private void initBinding() {
        viewDataBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);

        peopleViewModel = new PeopleViewModel(this);

        viewDataBinding.setPeopleviewmodel(peopleViewModel);
    }
}
