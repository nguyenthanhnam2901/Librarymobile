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

public class tab1Fragment extends Fragment {

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

            Book.add(new ChildModelClass(R.drawable.book1, 1, "Title", "Author", "Page x", "X", "Description"));
            Book.add(new ChildModelClass(R.drawable.book2, 2, "Title", "Author", "Page x", "X", "Description"));
            Book.add(new ChildModelClass(R.drawable.book3, 3, "Title", "Author", "Page x", "X", "Description"));
            Book.add(new ChildModelClass(R.drawable.book4, 4, "Title", "Author", "Page x", "X", "Description"));
            Book.add(new ChildModelClass(R.drawable.book5, 5, "Title", "Author", "Page x", "X", "Description"));
            Book.add(new ChildModelClass(R.drawable.book6, 6, "Title", "Author", "Page x", "X", "Description"));
            Book.add(new ChildModelClass(R.drawable.book7, 7, "Title", "Author", "Page x", "X", "Description"));
            Book.add(new ChildModelClass(R.drawable.book8, 8, "Title", "Author", "Page x", "X", "Description"));
            Book.add(new ChildModelClass(R.drawable.book9, 9, "Title", "Author", "Page x", "X", "Description"));
            Book.add(new ChildModelClass(R.drawable.book10, 10, "Title", "Author", "Page x", "X", "Description"));


            parentModelClassArrayList.add(new ParentModelClass("", Book));

            parentAdapterVertical = new ParentAdapterVertical(parentModelClassArrayList, requireContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            recyclerView.setAdapter(parentAdapterVertical);
            parentAdapterVertical.notifyDataSetChanged();

            return view;
        }
    }
