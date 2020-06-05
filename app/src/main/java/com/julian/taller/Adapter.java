package com.julian.taller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private Context context;
    private List<Book> books;

    public Adapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {

        viewHolder.bindNews(context, pos);

    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder
            extends RecyclerView.ViewHolder {

        private ConstraintLayout parent;
        private ImageView imageView;
        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);

            parent = (ConstraintLayout) itemView;

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

        }

        void bindNews(Context context, final int pos) {

            final Book book = books.get(pos);
            textView.setText(book.getVolumeInfo().getTitle());
            Glide.with(context).load(book.getVolumeInfo().getImageLinks().getThumbnail())
                    .into(imageView);
        }
    }
}
