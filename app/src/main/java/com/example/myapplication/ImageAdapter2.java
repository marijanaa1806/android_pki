package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class ImageAdapter2 extends RecyclerView.Adapter<ImageAdapter2.ImageViewHolder> {

    private Context mContext;
    private Integer[] mImageResources;
    private OnImageClickListener mListener;

    public ImageAdapter2(Context context, Integer[] imageResources) {
        mContext = context;
        mImageResources = imageResources;
    }

    public void setOnImageClickListener(OnImageClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter2.ImageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(mImageResources[position]);

        // Set the click listener for the image view
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onImageClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageResources.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    // Define the interface for the click listener
    public interface OnImageClickListener {
        void onImageClick(int position);
    }
}
