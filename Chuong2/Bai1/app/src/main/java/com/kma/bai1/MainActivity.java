package com.kma.bai1;

import static com.kma.bai1.AppConstants.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements OnTaskCompleted{

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<User> userList;
    ProgressBar progressBar;
    boolean isLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvUsers);
        progressBar = findViewById(R.id.pb);
        progressBar.setVisibility(View.VISIBLE);
        userList = new ArrayList<>();

        MyAsyncTask myAsyncTask = new MyAsyncTask(this);
        myAsyncTask.execute(BASE_URL);

    }

    private void initData() {
        userAdapter = new UserAdapter(MainActivity.this, userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void parseData(String result) {
        try {
            Log.d("TAG", result);
            JSONArray jsonArray = new JSONArray(result);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("login");
                String url = jsonObject.getString("avatar_url");

                User user = new User(id, name, url);
                userList.add(user);
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            }, 500);

            initData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}