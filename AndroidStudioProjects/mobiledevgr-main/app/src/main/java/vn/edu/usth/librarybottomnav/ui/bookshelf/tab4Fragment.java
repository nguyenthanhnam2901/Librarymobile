package vn.edu.usth.librarybottomnav.ui.bookshelf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.ui.recycler.ChildModelClass;
import vn.edu.usth.librarybottomnav.ui.recycler.ParentAdapterVertical;
import vn.edu.usth.librarybottomnav.ui.recycler.ParentModelClass;

import java.util.ArrayList;

public class tab4Fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ParentModelClass> parentModelClassArrayList;
    ArrayList<ChildModelClass> Book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_parent);
        Book = new ArrayList<>();
        parentModelClassArrayList = new ArrayList<>();
        ParentAdapterVertical parentAdapterVertical;

        // Add items to latestList
        Book.add(new ChildModelClass(R.drawable.book21, "Title", "Author", "Description"));
        Book.add(new ChildModelClass(R.drawable.book22, "Title", "Author", "Description"));
        Book.add(new ChildModelClass(R.drawable.book23, "Title", "Author", "Description"));
        Book.add(new ChildModelClass(R.drawable.book24, "Title", "Author", "Description"));
        Book.add(new ChildModelClass(R.drawable.book25, "Title", "Author", "Description"));
        Book.add(new ChildModelClass(R.drawable.book26, "Title", "Author", "Description"));
        Book.add(new ChildModelClass(R.drawable.book27, "Title", "Author", "Description"));
        Book.add(new ChildModelClass(R.drawable.book28, "Title", "Author", "Description"));
        Book.add(new ChildModelClass(R.drawable.book29, "Title", "Author", "Description"));
        Book.add(new ChildModelClass(R.drawable.book30, "Title", "Author", "Description"));

        parentModelClassArrayList.add(new ParentModelClass("", Book));

        parentAdapterVertical = new ParentAdapterVertical(parentModelClassArrayList, requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(parentAdapterVertical);
        parentAdapterVertical.notifyDataSetChanged();

        return view;
    }
}
