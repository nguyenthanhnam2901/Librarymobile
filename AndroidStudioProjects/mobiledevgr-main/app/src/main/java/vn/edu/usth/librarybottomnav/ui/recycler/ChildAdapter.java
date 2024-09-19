package vn.edu.usth.librarybottomnav.ui.recycler;

import android.app.appsearch.SearchResult;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.ui.home.BookDetail;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    List<ChildModelClass> childModelClassList;
    Context context;

    public ChildAdapter(List<ChildModelClass> childModelClassList, Context context) {
        this.childModelClassList = childModelClassList;
        this.context = context;
    }
    public void updateSearchResults(List<ChildModelClass> newResults) {
        childModelClassList.clear();
        if (newResults != null) {
            childModelClassList.addAll(newResults);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder holder, int position) {

        ChildModelClass currentItem = childModelClassList.get(position);

        holder.iv_child_image.setImageResource(currentItem.getImage());
        holder.tv_title.setText(currentItem.getTitle());
        holder.tv_author.setText(currentItem.getAuthor());
        holder.tv_description.setText(currentItem.getDescription());
                                                holder.cv_child_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetail.class);
                intent.putExtra("image",childModelClassList.get(position).getImage());
                intent.putExtra("title",childModelClassList.get(position).getTitle());
                intent.putExtra("author",childModelClassList.get(position).getAuthor());
                intent.putExtra("description",childModelClassList.get(position).getDescription());
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_child_image;
        TextView tv_title, tv_author, tv_description;
        LinearLayout cv_child_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_child_image = itemView.findViewById(R.id.iv_child_item);
            tv_title = itemView.findViewById(R.id.tv_child_title);
            tv_author = itemView.findViewById(R.id.tv_child_author);
            tv_description = itemView.findViewById(R.id.tv_child_description);
            cv_child_item =itemView.findViewById(R.id.cv_child_item);
        }
    }
}


