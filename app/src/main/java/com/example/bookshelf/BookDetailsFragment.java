package com.example.bookshelf;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Objects;


public class BookDetailsFragment extends Fragment {

    private static final String BOOK_KEY = "book";
    private final String DOWNLOAD_URL = "https://kamorris.com/lab/audlib/download.php?id=";
    private Book book;
    //ConstraintLayout bookDetailsLayout;//added layout for the fragment
    TextView titleTextView, authorTextView;
    ImageView coverImageView;
    private BookInterface parentActivity;
    public BookDetailsFragment() {}

    public static BookDetailsFragment newInstance(Book book) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();

        /*
         Our Book class implements the Parcelable interface
         therefore we can place one inside a bundle
         by using that put() method.
         */
        args.putParcelable(BOOK_KEY, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            book = (Book) getArguments().getParcelable(BOOK_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_details, container, false);

        titleTextView = v.findViewById(R.id.titleTextView);
        authorTextView = v.findViewById(R.id.authorTextView);
        coverImageView = v.findViewById(R.id.coverImageView);
        v.findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.play_pressed(book);
            }
        });
        /*
        Because this fragment can be created with or without
        a book to display when attached, we need to make sure
        we don't try to display a book if one isn't provided
         */
        if (book != null)
            displayBook(book);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){//since app crashes sometimes in onCreateView, ive made it nullable so we always findViewByID WHEN IT IS FULLY CREATED (THE VIEW)
        super.onViewCreated(view, savedInstanceState);
        coverImageView = Objects.requireNonNull(getView()).findViewById(R.id.coverImageView);
        titleTextView = Objects.requireNonNull(getView()).findViewById(R.id.titleTextView);
        authorTextView = getView().findViewById(R.id.authorTextView);
        if (book != null) {
            displayBook(book);
        }
    }
    public int getIdOfBook(){
        if(book!=null){
            return book.getId();//get books id
        }else{
            return 0;//no book
        }
    }
    public boolean UserInterfaceReady(){
        return (coverImageView!=null);//returns true or false whether we got a coverImage or not
    }
    /*
    This method is used both internally and externally (from the activity)
    to display a book
     */
    public void displayBook(Book book) {//Adding the setText for author and title
        this.book = book;
        titleTextView.setText(book.getTitle());
        titleTextView.setGravity(Gravity.CENTER);

        authorTextView.setText(book.getAuthor());
        authorTextView.setGravity(Gravity.CENTER);
        // Picasso simplifies image loading from the web.
        // No need to download separately.
        Picasso.get().load(book.getCoverUrl()).into(coverImageView);
    }
    interface BookInterface{
        void play_pressed(Book book);
    }
}
