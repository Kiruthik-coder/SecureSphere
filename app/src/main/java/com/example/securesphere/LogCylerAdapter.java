package com.example.securesphere;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;

public class LogCylerAdapter extends RecyclerView.Adapter<LogCylerAdapter.MyViewHolder>{

    Context context;
    ArrayList<logItem> list;

    public LogCylerAdapter(Context context, ArrayList<logItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.log_item_view,parent,false);
        return new MyViewHolder (v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        logItem logItem = list.get(position);

        holder.head.setText(logItem.headId);
        String temp1 = logItem.headId;

        holder.rating.setText(logItem.rating);
        String temp2 = logItem.rating;

        holder.info.setText(logItem.info);
        String temp3 = logItem.info;

        holder.date.setText(logItem.date);
        String temp4 = logItem.info;

        String org = logItem.provider;

        if (Objects.equals(org, "certin")){
            holder.imageView.setImageResource(R.drawable.certlogo);
        }

        if (Objects.equals(org, "nist")){
            holder.imageView.getLayoutParams().height = 50;
            holder.imageView.getLayoutParams().width = 259;
            holder.imageView.setImageResource(R.drawable.nistlogo);
        }

        String Link = logItem.link;

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(Link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView head, rating, info, date;
        Button button;
        ImageView imageView;

        private ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            head = itemView.findViewById(R.id.head_id);
            rating = itemView.findViewById(R.id.rating);
            info = itemView.findViewById(R.id.infoView);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.provider_img);
            button = itemView.findViewById(R.id.infoBt);

        }
    }
}

