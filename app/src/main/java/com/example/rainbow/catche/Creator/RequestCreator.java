package com.example.rainbow.catche.Creator;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.rainbow.bean.ImageBean;
import com.example.rainbow.catche.catcheObservable.DiskCacheObservable;
import com.example.rainbow.catche.catcheObservable.MemoryCacheObservable;
import com.example.rainbow.catche.catcheObservable.NetworkCacheObservable;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class RequestCreator {

    public MemoryCacheObservable memoryCacheObservable;
    public DiskCacheObservable diskCacheObservable;
    public NetworkCacheObservable networkCacheObservable;

    public RequestCreator(Context context) {
        memoryCacheObservable = new MemoryCacheObservable();
        diskCacheObservable = new DiskCacheObservable(context);
        networkCacheObservable = new NetworkCacheObservable();
    }

    public Observable<ImageBean> getImageFromMemory(String url) {
        return memoryCacheObservable.getImage(url)
                .filter(new Predicate<ImageBean>() {
                    @Override
                    public boolean test(@NonNull ImageBean imageBean) throws Exception {
                        Bitmap bitmap = imageBean.getBitmap();

                        return bitmap != null;
                    }
                });
    }

    public Observable<ImageBean> getImageFromDisk(String url) {

        return diskCacheObservable.getImage(url)
                .filter(new Predicate<ImageBean>() {
                    @Override
                    public boolean test(@NonNull ImageBean imageBean) throws Exception {
                        Bitmap bitmap = imageBean.getBitmap();
                        return bitmap != null;
                    }
                }).doOnNext(new Consumer<ImageBean>() {
                    @Override
                    public void accept(@NonNull ImageBean imageBean) throws Exception {
                        //缓存内存
                        memoryCacheObservable.putDataToCache(imageBean);
                    }
                });
    }

    public Observable<ImageBean> getImageFromNetwork(String url) {

        return networkCacheObservable.getImage(url)
                .filter(new Predicate<ImageBean>() {
                    @Override
                    public boolean test(@NonNull ImageBean imageBean) throws Exception {
                        Bitmap bitmap = imageBean.getBitmap();
                        return bitmap != null;
                    }
                })
                .doOnNext(new Consumer<ImageBean>() {
                    @Override
                    public void accept(@NonNull ImageBean imageBean) throws Exception {
                        //缓存文件和内存
                        diskCacheObservable.putDataToCache(imageBean);
                        memoryCacheObservable.putDataToCache(imageBean);
                    }
                });
    }
}
