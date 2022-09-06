package com.kma.bai1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userList.get(position);

        holder.id.setText(user.getId() + "");
        holder.name.setText(user.getName());
        Picasso.get().load(user.getAvatarUrl()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        if(userList != null) {
            return userList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView id, name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.ivAvatar);
            id = itemView.findViewById(R.id.tvId);
            name = itemView.findViewById(R.id.tvName);
        }
    }
}
