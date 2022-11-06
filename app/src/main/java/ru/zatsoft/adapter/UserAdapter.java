package ru.zatsoft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import ru.zatsoft.pojo.User;
import ru.zatsoft.sitecj.databinding.RecyclerviewRowBinding;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> itemList;
    private LayoutInflater myInflater;
    private ItemClickListener myClickListener;
    private RecyclerviewRowBinding binding;


    public UserAdapter(Context context, List<User> users) {
        this.myInflater = LayoutInflater.from(context);
        this.itemList = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = RecyclerviewRowBinding.inflate(myInflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = itemList.get(position);
        holder.myTextView.setText(user.getName());
        holder.myUID.setText(user.getUid());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.myClickListener = itemClickListener;
    }

    public User getItem(int position) {
        return itemList.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView myUID;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = binding.tvName;
            myUID = binding.tvUID;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (myClickListener != null) myClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
