package com.example.testbundlefragmenstack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Stack;

import static com.example.testbundlefragmenstack.BusCmdsKt.getStack;

public class MainActivity extends AppCompatActivity {

    EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //We need to send this to create a new Stack on the system.
        bus.register(this);
        bus.postSticky(new FragmentStack(new Stack<Fragment>()));

        replaceFragment(new FooFragment());
    }

    @Subscribe
    public void onNewFragment(NewFragment o) {
        replaceFragment(new FooFragment());
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl, fragment);
        fragmentTransaction.commit();
    }

    @Subscribe
    public void goBack(GoBack o) {
        replaceFragment(getStack().pop());
    }
}
