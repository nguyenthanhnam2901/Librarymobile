package vn.edu.usth.librarybottomnav.ui.recycler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.ui.BookDetail;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    private List<ChildModelClass> childModelClassList;
    private Context context;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChildModelClass currentItem = childModelClassList.get(position);

        // Set the data for the view holder
        holder.iv_child_image.setImageResource(currentItem.getImage());
        holder.tv_title.setText(currentItem.getTitle());
        holder.tv_author.setText(currentItem.getAuthor());
        holder.tv_category.setText(currentItem.getCategory());
        holder.tv_description.setText(currentItem.getDescription());

        // Adding a Log to check the ID value
        int bookId = currentItem.getId(); // Assuming getId() returns the correct book ID
        System.out.println("DEBUG: Book ID passed to BookDetail: " + bookId); // Log to check ID

        // Set onClickListener to navigate to BookDetail
        holder.cv_child_item.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookDetail.class);

            // Pass all relevant data
            intent.putExtra("id", bookId); // Ensure this key matches in BookDetail
            intent.putExtra("image", currentItem.getImage());
            intent.putExtra("title", currentItem.getTitle());
            intent.putExtra("author", currentItem.getAuthor());
            intent.putExtra("description", currentItem.getDescription());
            intent.putExtra("category", currentItem.getCategory());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_child_image;
        TextView tv_title, tv_author, tv_category, tv_description;
        View cv_child_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_child_image = itemView.findViewById(R.id.iv_child_item);
            tv_title = itemView.findViewById(R.id.tv_child_title);
            tv_author = itemView.findViewById(R.id.tv_child_author);
            tv_category = itemView.findViewById(R.id.tv_child_category);
            tv_description = itemView.findViewById(R.id.tv_child_description);
            cv_child_item = itemView.findViewById(R.id.cv_child_item);
        }
    }
}
