package com.example.today_news;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Appliction_adapter extends RecyclerView.Adapter<Appliction_adapter.viewholder> {
    public Appliction_adapter(List<Article> list) {
        this.list = list;
    }

    List<Article> list;


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_layout,parent,false);

        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Article article = list.get(position);
        holder.title.setText(article.getTitle());
        holder.body.setText(article.getSource().getName());
        Picasso.get().load(article.getUrlToImage()).error(R.drawable.news).into(holder.img);
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(v.getContext(), web_activity.class)
                         .putExtra("uri",article.getUrlToImage())
                         .putExtra("title",article.getTitle())

                                 .putExtra("author",article.getAuthor())
                                         .putExtra("content",article.getContent());
                 v.getContext().startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    void update_Data(List<Article> data)
    {
        list.clear();
        list.addAll(data);
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView title,body;
        ImageView img;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}
