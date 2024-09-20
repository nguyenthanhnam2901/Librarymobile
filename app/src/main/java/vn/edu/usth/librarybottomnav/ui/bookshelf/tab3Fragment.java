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

public class tab3Fragment extends Fragment {

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
        Book.add(new ChildModelClass(R.drawable.book11, "Title", "Author", "Description", "X"));
        Book.add(new ChildModelClass(R.drawable.book12, "Title", "Author", "Description", "X"));
        Book.add(new ChildModelClass(R.drawable.book13, "Title", "Author", "Description", "X"));
        Book.add(new ChildModelClass(R.drawable.book14, "Title", "Author", "Description", "X"));
        Book.add(new ChildModelClass(R.drawable.book15, "Title", "Author", "Description", "X"));
        Book.add(new ChildModelClass(R.drawable.book16, "Title", "Author", "Description", "X"));
        Book.add(new ChildModelClass(R.drawable.book17, "Title", "Author", "Description", "X"));
        Book.add(new ChildModelClass(R.drawable.book18, "Title", "Author", "Description", "X"));
        Book.add(new ChildModelClass(R.drawable.book19, "Title", "Author", "Description", "X"));
        Book.add(new ChildModelClass(R.drawable.book20, "Title", "Author", "Description", "X"));

        parentModelClassArrayList.add(new ParentModelClass("", Book));

        parentAdapterVertical = new ParentAdapterVertical(parentModelClassArrayList, requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(parentAdapterVertical);
        parentAdapterVertical.notifyDataSetChanged();

        return view;
    }
}
