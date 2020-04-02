package com.example.rainbow.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.example.rainbow.R;
import com.example.rainbow.catche.Loader.RxImageLoader;
import com.example.rainbow.util.ImagUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoGraphWindowAdpter extends RecyclerView.Adapter<PhotoGraphWindowAdpter.VrItemViewHolder> {
    private Context context;
    private String[] pathArray;

    public PhotoGraphWindowAdpter(Context context, String[] pathArray) {
        this.context = context;
        this.pathArray = pathArray;
    }

    @Override
    public VrItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_item_photo, viewGroup, false);
        VrItemViewHolder vrItemViewHolder = new VrItemViewHolder(itemView);
        return vrItemViewHolder;
    }

    private int mSelectedPos;

    @Override
    public void onBindViewHolder(VrItemViewHolder viewHolder, int i) {
        viewHolder.setPosition(i);
        String picture = pathArray[i];
        String url = ImagUtil.handleUrl(picture);
        if (!TextUtils.isEmpty(url)) {
            RxImageLoader.with(context).getBitmap(url).subscribe(
                    imageBean -> {
                        viewHolder.loading.setVisibility(View.GONE);
                        Bitmap bitmap = imageBean.getBitmap();
                        viewHolder.img.setImageDrawable(new BitmapDrawable(bitmap));
                    }
            );
        }
    }

    @Override
    public int getItemCount() {
        return pathArray.length;
    }

    public class VrItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private int position;
        private Bitmap bitmap;
        private ProgressBar loading;

        public void setPosition(int position) {
            this.position = position;
        }

        public VrItemViewHolder(View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.ivvr);
            this.loading = itemView.findViewById(R.id.img_loading);
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }
}
