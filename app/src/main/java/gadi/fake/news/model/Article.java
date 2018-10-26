package gadi.fake.news.model;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Article extends BaseObservable {
    //TODO: not in use yet
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

    //TODO: implement ProgressBar
    private boolean hideProgress;

    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    //Load image on background
    @BindingAdapter({"urlToImage"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    //Load url on background
    @BindingAdapter({ "loadUrl" })
    public static void loadUrl(WebView view, String url) {
        view.loadUrl(url);
    }

    //Convert date from "yyyy-MM-dd'T'HH:mm:ss'Z'" to "Day, MEDIUM date SHORT time"
    @BindingAdapter("serverDateToDate")
    public static void serverDateToDate(@NonNull TextView textView, String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Display the date as "Friday, Oct 26, 2018 4:50 PM"
        if (sourceDate != null) {
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            textView.setText(String.format("%s, %s %s", dayFormat.format(sourceDate),
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

    //TODO: not in use yet
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
