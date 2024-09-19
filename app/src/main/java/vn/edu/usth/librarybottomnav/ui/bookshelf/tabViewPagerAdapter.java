package vn.edu.usth.librarybottomnav.ui.bookshelf;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class tabViewPagerAdapter extends FragmentStatePagerAdapter {

    public tabViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new tab1Fragment();
            case 1:
                return new tab2Fragment();
            case 2:
                return new tab3Fragment();
            case 3:
                return new tab4Fragment();
            default:
                return new tab1Fragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "History";
            case 1:
                return "Reading";
            case 2:
                return "Complete";
            case 3:
                return "Plan on read";

            default:
                return "History";
        }
    }
}
