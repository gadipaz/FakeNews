package gadi.fake.news.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gadi.fake.news.R;

public class Article extends BaseObservable {
    @SerializedName("author")
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage;
    @SerializedName("publishedAt")
    private String publishedAt;

    //Didn't succeed to implement the progressbar
    private boolean hideProgress;

    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    @BindingAdapter({"urlToImage"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter({ "loadUrl" })
    public static void loadUrl(WebView view, String url) {
        view.loadUrl(url);
    }

    @BindingAdapter("serverDateToDate")
    public static void serverDateToDate(@NonNull TextView textView, String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (sourceDate != null) {
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            textView.setText(String.format("%s, %s, %s", dayFormat.format(sourceDate),
                    DateFormat.getDateInstance(DateFormat.MEDIUM).format(sourceDate),
                    DateFormat.getTimeInstance(DateFormat.SHORT).format(sourceDate)));
        }
    }

    //TODO: implement ProgressBar
    /*@BindingAdapter({ "setWebViewClient" })
    public static void setWebViewClient(WebView view, WebViewClient client) {
        view.setWebViewClient(client);
    }
    private class Client extends WebViewClient {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request,
                                    WebResourceError error) {
            super.onReceivedError(view, request, error);
            setHideProgress(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            setHideProgress(true);
        }
    }

    public WebViewClient getWebViewClient() {
        return new Client();
    }

    @Bindable
    public boolean isHideProgress() {
        return hideProgress;
    }

    private void setHideProgress(boolean hideProgress) {
        this.hideProgress = hideProgress;
        notifyChange();
    }*/

    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    public String getUrlToImage() {
        return urlToImage;
    }
    public String getPublishedAt() {
        return publishedAt;
    }

}
