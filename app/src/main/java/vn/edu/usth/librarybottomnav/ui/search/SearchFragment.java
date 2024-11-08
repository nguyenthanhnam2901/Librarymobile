package vn.edu.usth.librarybottomnav.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.librarybottomnav.MyDatabaseHelper;
import vn.edu.usth.librarybottomnav.R;
import vn.edu.usth.librarybottomnav.ui.recycler.ChildModelClass;
import vn.edu.usth.librarybottomnav.ui.recycler.ParentAdapterVertical;
import vn.edu.usth.librarybottomnav.ui.recycler.ParentModelClass;

public class SearchFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private ParentAdapterVertical parentAdapterVertical;
    private List<ParentModelClass> parentModelClassArrayList;
    private List<ChildModelClass> allBooksList;
    private List<ChildModelClass> searchResultsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.search_view);

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Title#Author#Category");

        recyclerView = view.findViewById(R.id.search_results_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        allBooksList = new ArrayList<>();
        searchResultsList = new ArrayList<>();
        parentModelClassArrayList = new ArrayList<>();

        // Set up the initial data
        setupInitialData();

        // Set up the parent adapter with an empty search result list
        parentAdapterVertical = new ParentAdapterVertical(parentModelClassArrayList, getContext());
        recyclerView.setAdapter(parentAdapterVertical);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });
    }

    private void performSearch(String query) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getContext());

        // Split the query into title, author, and category
        String[] queryParts = query.split("#");
        String titleQuery = queryParts.length > 0 ? queryParts[0].trim() : "";
        String authorQuery = queryParts.length > 1 ? queryParts[1].trim() : "";
        String categoryQuery = queryParts.length > 2 ? queryParts[2].trim() : "";

        // Get search results from database
        searchResultsList = dbHelper.searchBooks(titleQuery, authorQuery, categoryQuery);

        // Update RecyclerView with new results
        parentModelClassArrayList.clear();
        parentModelClassArrayList.add(new ParentModelClass("Search Results", searchResultsList));
        parentAdapterVertical.notifyDataSetChanged();
    }



    private void setupInitialData() {
        // Add initial books to the list
        allBooksList.add(new ChildModelClass(R.drawable.book3, 3, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book9, 9, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book5, 5, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book6, 6, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book7, 7, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book8, 8, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book1, 1, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book19, 19, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book2, 2, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book18, 18, "Title", "Author", "Description", "H", "Detailed Description"));
        allBooksList.add(new ChildModelClass(R.drawable.book11, 11, "Title", "Author", "Description", "H", "Detailed Description"));

        parentModelClassArrayList.add(new ParentModelClass("", allBooksList));
    }
}
