package vn.edu.usth.librarybottomnav.ui.bookshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import vn.edu.usth.librarybottomnav.R;

public class BookshelfFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View nView;

    public BookshelfFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BookshelfFragment newInstance(String param1, String param2) {
        BookshelfFragment fragment = new BookshelfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        nView= inflater.inflate(R.layout.fragment_bookshelf, container, false);
        tabLayout = nView.findViewById(R.id.tab_layout);
        viewPager = nView.findViewById(R.id.order_pager);

        tabViewPagerAdapter adapter= new tabViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);;
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return nView;
    }
}
