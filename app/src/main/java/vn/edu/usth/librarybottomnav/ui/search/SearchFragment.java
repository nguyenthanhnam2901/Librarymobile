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

        if (query.isEmpty()) {
            // Show all books when query is empty
            searchResultsList.addAll(allBooksList);
        } else {
            // Filter books based on the query
            for (ChildModelClass book : allBooksList) {
                if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
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
        allBooksList.add(new ChildModelClass(R.drawable.book1, "Spice and Wolf", "Isuna Hasekura", "memaybeo"));
        allBooksList.add(new ChildModelClass(R.drawable.book2, "One in a Million", "Fuse", "memaybeo"));
        allBooksList.add(new ChildModelClass(R.drawable.book3, "That time I got Reincarnated as a Slime", "Nishioishin", "memaybeo"));
        allBooksList.add(new ChildModelClass(R.drawable.book4, "Monogatari", "Jougi Shiraishi", "memaybeo"));
        allBooksList.add(new ChildModelClass(R.drawable.book5, "Wandering Witch", "Rifujin na Magonote", "memaybeo"));
        allBooksList.add(new ChildModelClass(R.drawable.book6, "Mushoku Tensei", "Natsume Akatsuki", "memaybeo"));

        // You can add more categories similar to "Latest Books" here
        List<ChildModelClass> latestBooksList = new ArrayList<>(allBooksList.subList(0, 3));
        List<ChildModelClass> recommendedBooksList = new ArrayList<>(allBooksList.subList(3, allBooksList.size()));

        parentModelClassArrayList.add(new ParentModelClass("Latest Books", latestBooksList));
        parentModelClassArrayList.add(new ParentModelClass("Recommended Books", recommendedBooksList));
    }
}
