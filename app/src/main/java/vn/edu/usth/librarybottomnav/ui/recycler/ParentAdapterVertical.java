package vn.edu.usth.librarybottomnav.ui.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.librarybottomnav.R;

public class ParentAdapterVertical extends RecyclerView.Adapter<ParentAdapterVertical.ViewHolder> {

    List<ParentModelClass> parentModelClassList;
    Context context;

    public ParentAdapterVertical(List<ParentModelClass> parentModelClassList, Context context) {
        this.parentModelClassList = parentModelClassList;
        this.context = context;
    }


    @NonNull
    @Override
    public ParentAdapterVertical.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_rv_layout, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapterVertical.ViewHolder holder, int position) {
        holder.tv_parent_title.setText(parentModelClassList.get(position).title);

        ChildAdapter childAdapter;
        childAdapter = new ChildAdapter(parentModelClassList.get(position).childModelClassList, context);
        holder.rv_child.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.rv_child.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rv_child;
        TextView tv_parent_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_child = itemView.findViewById(R.id.rv_child);
            tv_parent_title = itemView.findViewById(R.id.tv_parent_title);

        }
    }
}

