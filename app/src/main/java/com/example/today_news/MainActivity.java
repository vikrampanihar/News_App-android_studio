package com.example.today_news;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    List<Article> list = new ArrayList<>();
    LinearProgressIndicator linearProgressIndicator;
    Appliction_adapter adapter;
    SearchView sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            recyclerView = findViewById(R.id.recyclerView);
            linearProgressIndicator = findViewById(R.id.p);
            Button btn1 = findViewById(R.id.btn1);
            Button btn2 = findViewById(R.id.btn2);
            Button btn3 = findViewById(R.id.btn3);
            Button btn4 = findViewById(R.id.btn4);
            Button btn5 = findViewById(R.id.btn5);
            Button btn6 = findViewById(R.id.btn6);
            Button btn7 = findViewById(R.id.btn7);
            sc = findViewById(R.id.searchView);
            sc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    getnews("GENERAL", query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn3.setOnClickListener(this);
            btn4.setOnClickListener(this);
            btn5.setOnClickListener(this);
            btn6.setOnClickListener(this);
            btn7.setOnClickListener(this);


               getnews("GENERAL",null);
               setAdapter();



  return insets;
        });
    }

    private void setAdapter() {
        try {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new Appliction_adapter(list);
            recyclerView.setAdapter(adapter);

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Something wrong bro "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void getnews(String category , String query) {
        changinprogress(true);
        NewsApiClient newsApiClient = new NewsApiClient("6ab3f095453749efa0d4d4b5379a87dc");
        newsApiClient.getTopHeadlines(new TopHeadlinesRequest.Builder()
                .language("en")
                        .q(query)
                        .category(category)
                .build(), new NewsApiClient.ArticlesResponseCallback() {
            @Override
            public void onSuccess(ArticleResponse response) {
                runOnUiThread(()->{
                    changinprogress(false);
                    list = response.getArticles();
                    adapter.update_Data(list);
                   adapter.notifyDataSetChanged();

                });
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
    void changinprogress(Boolean a)
    {
        if (a)
            linearProgressIndicator.setVisibility(View.VISIBLE);
        else
            linearProgressIndicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        Button btn =(Button)v;
        String category = (String) btn.getText();
        getnews(category,null);

    }
}