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
        searchResultsList.clear();

        // Split the query into parts by the "#" symbol
        String[] queryParts = query.split("#");

        // Initialize variables to store the parts of the query
        String titleQuery = "";
        String authorQuery = "";
        String categoryQuery = "";

        // Assign each part to the corresponding variable if it exists
        if (queryParts.length > 0) {
            titleQuery = queryParts[0].trim().toLowerCase();
        }
        if (queryParts.length > 1) {
            authorQuery = queryParts[1].trim().toLowerCase();
        }
        if (queryParts.length > 2) {
            categoryQuery = queryParts[2].trim().toLowerCase();
        }

        // If the query is empty, show all books
        if (titleQuery.isEmpty() && authorQuery.isEmpty() && categoryQuery.isEmpty()) {
            searchResultsList.addAll(allBooksList);
        } else {
            // Filter books based on the title, author, and category
            for (ChildModelClass book : allBooksList) {
                boolean matchesTitle = titleQuery.isEmpty() || book.getTitle().toLowerCase().contains(titleQuery);
                boolean matchesAuthor = authorQuery.isEmpty() || book.getAuthor().toLowerCase().contains(authorQuery);
                boolean matchesCategory = categoryQuery.isEmpty() || book.getCategory().toLowerCase().contains(categoryQuery);

                // Add book to the results if it matches all the non-empty query parts
                if (matchesTitle && matchesAuthor && matchesCategory) {
                    searchResultsList.add(book);
                }
            }
        }

        // Update the parent model with the filtered search results
        parentModelClassArrayList.clear();
        parentModelClassArrayList.add(new ParentModelClass("Search Results", searchResultsList));
        parentAdapterVertical.notifyDataSetChanged();
    }


    private void setupInitialData() {
        // Add initial books to the list
        allBooksList.add(new ChildModelClass(R.drawable.book3, "book3", "A", "Description", "L"));
        allBooksList.add(new ChildModelClass(R.drawable.book9, "book9", "B", "Description", "Q"));
        allBooksList.add(new ChildModelClass(R.drawable.book5, "book5", "C", "Description", "J"));
        allBooksList.add(new ChildModelClass(R.drawable.book6, "book6", "D", "Description", "H"));
        allBooksList.add(new ChildModelClass(R.drawable.book7, "book7", "E", "Description", "G"));
        allBooksList.add(new ChildModelClass(R.drawable.book8, "book8", "F", "Description", "R"));
        allBooksList.add(new ChildModelClass(R.drawable.book1, "book1", "G", "Description", "I"));
        allBooksList.add(new ChildModelClass(R.drawable.book19, "book19", "H", "Description", "Y"));
        allBooksList.add(new ChildModelClass(R.drawable.book2, "book2", "J", "Description", "A"));
        allBooksList.add(new ChildModelClass(R.drawable.book18, "book18", "K", "Description", "B"));
        allBooksList.add(new ChildModelClass(R.drawable.book11, "book11", "L", "Description", "C"));

        parentModelClassArrayList.add(new ParentModelClass("", allBooksList));
    }
}
