package com.gerardogonzalez.gerardo_gonzalez_event_tracking;

import androidx.appcompat.app.AppCompatActivity;

// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOptions;
    private List<String> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        recyclerViewOptions = findViewById(R.id.recyclerViewOptions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(layoutManager);


        options = new ArrayList<>();
        options.add("Login Screen");
        options.add("SMS Request Screen");
        options.add("Database Screen");

        navigation adapter;
        adapter = new navigation(options, new navigation.OnItemClickListener() {
            @Override
            public void onItemClick(String option) {
                switch (option) {
                    case "Login Screen":
                        startActivity(new Intent(MainActivity.this, loginActivity.class));
                        break;
                    case "SMS Request Screen":
                        startActivity(new Intent(MainActivity.this, sms_request_activity.class));
                        break;
                    case "Database Screen":
                        startActivity(new Intent(MainActivity.this, display_database.class));
                        break;

                }
            }
        });
        recyclerViewOptions.setAdapter(adapter);

    }
}