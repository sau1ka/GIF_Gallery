package com.example.gif_app.main.RV_Adapter;


import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.Object.Datum;
import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gif_app.R;


import java.util.List;


import pl.droidsonroids.gif.GifImageView;

import static android.widget.Toast.LENGTH_SHORT;


public class Gif_Adapter
        extends RecyclerView.Adapter <Gif_Adapter.ViewHolder>{
    private final Activity Parent;
    private final List<Datum> values;
    private OnInsertListener onInsertListener;

    public Gif_Adapter(Activity parent, List<Datum> items) {
        Parent = parent;
        values = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String s = values
                .get(position)
                .getImages()
                .getDownsized()
                .getUrl();
        Glide
                .with(holder.itemView)
                .asGif()
                .load(s)
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.image);
        holder.itemView.setTag(values.get(position));

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public interface OnInsertListener {
        void onInsert(Datum datum);
    }

    public void setOnInsertListener(OnInsertListener onInsertListener) {
        this.onInsertListener = onInsertListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        GifImageView image;
        ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.gif_view);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onInsertListener.onInsert(values.get(ViewHolder.this.getAdapterPosition()));
                    Toast toast = Toast.makeText(view.getContext(), "Загружено в БД", LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }

    public List<Datum> getitems() {
        return values;
    }

}