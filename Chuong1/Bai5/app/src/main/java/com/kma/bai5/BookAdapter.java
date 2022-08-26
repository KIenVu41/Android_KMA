package com.kma.bai5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>{

    private Context context;
    public List<Book> bookList;
    private OnItemListener onItemListener;

    public BookAdapter(Context context, List<Book> bookList, OnItemListener onItemListener) {
        this.context = context;
        this.bookList = bookList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_view, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book book = bookList.get(position);
        holder.tvInfor.setText(book.getId() + " - " + book.getName() + " - " + book.getAuthor());

    }

    @Override
    public int getItemCount() {
        if(bookList != null) {
            return bookList.size();
        }
        return 0;
    }

    public class BookHolder extends RecyclerView.ViewHolder {

        private TextView tvInfor;

        public BookHolder(@NonNull View itemView) {
            super(itemView);

            tvInfor = itemView.findViewById(R.id.tvInfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemListener.onClick(bookList.get(getAdapterPosition()));
                }
            });
        }
    }
}
