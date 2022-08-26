package com.kma.bai5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemListener {

    private EditText edtId, edtName, edtAuthor;
    private Button btnAdd, btnDel, btnUpdate;
    private RecyclerView rvBook;
    private BookAdapter bookAdapter;
    private DbHelper dbHelper;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        //getDataFromDb();
        setOnClick();
    }

    private void init() {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAuthor = findViewById(R.id.edtAuthor);
        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        btnUpdate = findViewById(R.id.btnUpdate);
        rvBook = findViewById(R.id.rvBook);
        bookList = new ArrayList<>();
        dbHelper = new DbHelper(this);

//        rvBook.setAdapter(bookAdapter);
//        rvBook.setLayoutManager(new LinearLayoutManager(this));
        getDataFromDb();
        bookAdapter = new BookAdapter(this, bookList, this);
        rvBook.setAdapter(bookAdapter);
        rvBook.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDataFromDb() {
        bookList = dbHelper.getAllBooks();
    }

    private void setOnClick() {
        if(edtName.getText().equals("")) {
            return;
        }
        if(edtAuthor.getText().equals("")) {
            return;
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addBook(new Book(0, edtName.getText().toString(), edtAuthor.getText().toString()));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.updateBook(new Book(Integer.valueOf(edtId.getText().toString()), edtName.getText().toString(), edtAuthor.getText().toString()));
                bookList.clear();
                getDataFromDb();
                bookAdapter.bookList = bookList;
                bookAdapter.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteBook(Integer.valueOf(edtId.getText().toString()));
                bookList.clear();
                getDataFromDb();
                bookAdapter.bookList = bookList;
                bookAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(Book book) {
        edtId.setText(String.valueOf(book.getId()));
        edtName.setText(book.getName());
        edtAuthor.setText(book.getAuthor());
    }
}