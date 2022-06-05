package rob.myappcompany.myapplication.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import rob.myappcompany.myapplication.Frag1;
import rob.myappcompany.myapplication.Frag2;
import rob.myappcompany.myapplication.Frag3;
import rob.myappcompany.myapplication.Frag4;
import rob.myappcompany.myapplication.Frag5;
import rob.myappcompany.myapplication.Frag6;
import rob.myappcompany.myapplication.MainActivity;
import rob.myappcompany.myapplication.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter<mainView> extends FragmentPagerAdapter {


    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new Frag1();
                break;
            case 1:
                fragment = new Frag2();
                break;
            case 2:
                fragment = new Frag3();
                break;
            case 3:
                fragment = new Frag4();
                break;
            case 4:
                fragment = new Frag5();
                break;
            case 5:
                fragment = new Frag6();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Light 1";
            case 1:
                return "Light 2";
            case 2:
                return "Light 3";
            case 3:
                return "Light 4";
            case 4:
                return "Fan 1";
            case 5:
                return "Fan 2";
        }
        return null;
    }



    @Override
    public int getCount() {
        // Show 6 total pages.
        return 6;
    }
}