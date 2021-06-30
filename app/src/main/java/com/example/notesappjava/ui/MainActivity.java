package com.example.notesappjava.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.notesappjava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements RouterHolder {

    private MainRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        router = new MainRouter(getSupportFragmentManager());

        if (savedInstanceState == null) {
            router.showAuth();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_notes) {
                    router.showAuth();
                }

                if (item.getItemId() == R.id.action_info) {
                    router.showInfo();
                }
                return true;
            }
        });

        getSupportFragmentManager().setFragmentResultListener(AuthFragment.AUTH_RESULT, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull  String requestKey, @NonNull  Bundle result) {
                router.showNotes();
            }
        });

    }

    @Override
    public MainRouter getMainRouter() {
        return router;
    }
}