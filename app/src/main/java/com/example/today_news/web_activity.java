package com.example.today_news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.net.URL;

public class web_activity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    ImageView img = findViewById(R.id.image);
                    TextView authorname=findViewById(R.id.author_name);
                    TextView content = findViewById(R.id.content);
                        Picasso.get()
                                .load(getIntent().getStringExtra("uri"))
                                .into(img);
                         TextView title = findViewById(R.id.title);
                         title.setText(getIntent().getStringExtra("title"));




                    content.setText(getIntent().getStringExtra("content"));
                    authorname.setText(getIntent().getStringExtra("author"));

            return insets;
        }

        );

    }


}