package com.example.minusnervi;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockApplication;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    List<News> newsList;
    ListView newsListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsListView = findViewById(R.id.LayoutNewsListView);
        newsList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("news");

        navigationView = findViewById(R.id.navigationViewId);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.Menu_auth:
                        Intent toAuth = new Intent(MainActivity.this,AuthActivity.class);
                        startActivity(toAuth);
                        break;
                    case R.id.Menu_main:
                        break;
                }
                return false;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (IntConn()==true){
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newsList.clear();
                    for (DataSnapshot newsSnapshot:dataSnapshot.getChildren()){
                        News news = newsSnapshot.getValue(News.class);
                        newsList.add(news);
                    }
                    ArrayAdapter adapter = new NewsListAdapter(MainActivity.this,newsList);
                    newsListView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            toastMessage("Отсутствует интернет-соединение");
        }

    }

    public boolean IntConn(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null&& networkInfo.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
