package com.example.bookshelf;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/*
Book class to represent a book. Implements the Parcelable interface
so that a book can be saved inside a bundle object
 */
public class Book implements Parcelable {

    public final static String JSON_ID = "book_id";
    public final static String JSON_TITLE = "title";
    public final static String JSON_AUTHOR = "author";
    public final static String JSON_COVER_URL = "cover_url";

    private int id,duration;
    private String title, author, coverUrl;//added duration
    public Book(int id, String title, String author, String coverUrl, int duration){//int duration) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.duration = this.duration;//added duration
    }
    public Book (JSONObject type)throws JSONException{//adding json objects for the fields
        this(type.getInt("book_id"),
                type.getString("title"),
                type.getString("author"),
                type.getString("cover_url"),
                type.getInt("duration"));
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        coverUrl = in.readString();
        duration = in.readInt();//added duration

    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(coverUrl);
        dest.writeInt(duration);//adding the one for duration
    }

    public int getDuration() { return duration; }//getter for duration

    public void setDuration(int duration) { this.duration = duration; }//setter for duration
}
