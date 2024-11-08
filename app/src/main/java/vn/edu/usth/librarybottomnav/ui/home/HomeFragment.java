package vn.edu.usth.librarybottomnav.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.ui.recycler.ChildModelClass;
import vn.edu.usth.librarybottomnav.ui.recycler.ParentAdapterHorizontal;
import vn.edu.usth.librarybottomnav.ui.recycler.ParentModelClass;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ParentModelClass> parentModelClassArrayList;
    ArrayList<ChildModelClass> childModelClassArrayList;
    ArrayList<ChildModelClass> favouriteList;
    ArrayList<ChildModelClass> recentlyWatchedList;
    ArrayList<ChildModelClass> latestList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_parent);
        childModelClassArrayList = new ArrayList<>();
        favouriteList = new ArrayList<>();
        recentlyWatchedList = new ArrayList<>();
        latestList = new ArrayList<>();
        parentModelClassArrayList = new ArrayList<>();

        ParentAdapterHorizontal parentAdapterHorizontal;

        // Add items to latestList
        latestList.add(new ChildModelClass(R.drawable.book1, 1, "Title", "Author", "Description", "H", "Detailed Description"));
        latestList.add(new ChildModelClass(R.drawable.book2, 2, "Title", "Author", "Description", "H", "Detailed Description"));
        latestList.add(new ChildModelClass(R.drawable.book3, 3, "Title", "Author", "Description", "H", "Detailed Description"));
        latestList.add(new ChildModelClass(R.drawable.book4, 4, "Title", "Author", "Description", "H", "Detailed Description"));
        latestList.add(new ChildModelClass(R.drawable.book5, 5, "Title", "Author", "Description", "H", "Detailed Description"));
        latestList.add(new ChildModelClass(R.drawable.book6, 6, "Title", "Author", "Description", "H", "Detailed Description"));
        latestList.add(new ChildModelClass(R.drawable.book7, 7, "Title", "Author", "Description", "H", "Detailed Description"));
        latestList.add(new ChildModelClass(R.drawable.book8, 8, "Title", "Author", "Description", "H", "Detailed Description"));
        latestList.add(new ChildModelClass(R.drawable.book9, 9, "Title", "Author", "Description", "H", "Detailed Description"));


        parentModelClassArrayList.add(new ParentModelClass("Latest Books", latestList));

        // Add items to recentlyWatchedList
        recentlyWatchedList.add(new ChildModelClass(R.drawable.book11, 11, "Title", "Author", "Description", "H", "Detailed Description"));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.book12, 12, "Title", "Author", "Description", "H", "Detailed Description"));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.book13, 13, "Title", "Author", "Description", "H", "Detailed Description"));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.book14, 14, "Title", "Author", "Description", "H", "Detailed Description"));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.book15, 15, "Title", "Author", "Description", "H", "Detailed Description"));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.book16, 16, "Title", "Author", "Description", "H", "Detailed Description"));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.book17, 17, "Title", "Author", "Description", "H", "Detailed Description"));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.book18, 18, "Title", "Author", "Description", "H", "Detailed Description"));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.book19, 19, "Title", "Author", "Description", "H", "Detailed Description"));

        parentModelClassArrayList.add(new ParentModelClass("Recently Read", recentlyWatchedList));

        // Add items to favouriteList
        favouriteList.add(new ChildModelClass(R.drawable.book21, 21, "Title", "Author", "Description", "H", "Detailed Description"));
        favouriteList.add(new ChildModelClass(R.drawable.book22, 22, "Title", "Author", "Description", "H", "Detailed Description"));
        favouriteList.add(new ChildModelClass(R.drawable.book23, 23, "Title", "Author", "Description", "H", "Detailed Description"));
        favouriteList.add(new ChildModelClass(R.drawable.book24, 24, "Title", "Author", "Description", "H", "Detailed Description"));
        favouriteList.add(new ChildModelClass(R.drawable.book25, 25, "Title", "Author", "Description", "H", "Detailed Description"));
        favouriteList.add(new ChildModelClass(R.drawable.book26, 26, "Title", "Author", "Description", "H", "Detailed Description"));
        favouriteList.add(new ChildModelClass(R.drawable.book27, 27, "Title", "Author", "Description", "H", "Detailed Description"));
        favouriteList.add(new ChildModelClass(R.drawable.book28, 28, "Title", "Author", "Description", "H", "Detailed Description"));
        favouriteList.add(new ChildModelClass(R.drawable.book29, 29, "Title", "Author", "Description", "H", "Detailed Description"));

        parentModelClassArrayList.add(new ParentModelClass("Favorites", favouriteList));

        // Add items to childModelClassArrayList
        childModelClassArrayList.clear();
        childModelClassArrayList.add(new ChildModelClass(R.drawable.book30, 30, "Title", "Author", "Description", "H", "Detailed Description"));
        childModelClassArrayList.add(new ChildModelClass(R.drawable.book21, 21, "Title", "Author", "Description", "H", "Detailed Description"));
        childModelClassArrayList.add(new ChildModelClass(R.drawable.book13, 13, "Title", "Author", "Description", "H", "Detailed Description"));
        childModelClassArrayList.add(new ChildModelClass(R.drawable.book4, 4, "Title", "Author", "Description", "H", "Detailed Description"));
        childModelClassArrayList.add(new ChildModelClass(R.drawable.book25, 25, "Title", "Author", "Description", "H", "Detailed Description"));
        childModelClassArrayList.add(new ChildModelClass(R.drawable.book6, 16, "Title", "Author", "Description", "H", "Detailed Description"));
        childModelClassArrayList.add(new ChildModelClass(R.drawable.book17, 17, "Title", "Author", "Description", "H", "Detailed Description"));
        childModelClassArrayList.add(new ChildModelClass(R.drawable.book8, 8, "Title", "Author", "Description", "H", "Detailed Description"));
        childModelClassArrayList.add(new ChildModelClass(R.drawable.book9, 9, "Title", "Author", "Description", "H", "Detailed Description"));

        parentModelClassArrayList.add(new ParentModelClass("Recommended", childModelClassArrayList));
//        parentModelClassArrayList.add(new ParentModelClass("Recently", childModelClassArrayList));
//        parentModelClassArrayList.add(new ParentModelClass("Great", childModelClassArrayList));


        // Set up the adapter
        parentAdapterHorizontal = new ParentAdapterHorizontal(parentModelClassArrayList, requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(parentAdapterHorizontal);
        parentAdapterHorizontal.notifyDataSetChanged();

        return view;
    }
}
