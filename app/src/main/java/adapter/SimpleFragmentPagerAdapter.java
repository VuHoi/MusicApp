package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vukhachoi.muisicapp.FragmentLayout_3;
import com.example.vukhachoi.muisicapp.layoutFragment;
import com.example.vukhachoi.muisicapp.layoutFragment_2;

/**
 * Created by Vu Khac Hoi on 9/20/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
  private   layoutFragment fragment;
    private Context mContext;
    public SimpleFragmentPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        fragment = new layoutFragment();
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {


            return fragment;
        } else if (position == 1) {
            return new layoutFragment_2();
        } else if (position == 2) {
            return new FragmentLayout_3();

        }
        return null;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Bài hát";
            case 1:
                return "Ca sĩ";
            case 2:
                return "Album";

            default:
                return null;
        }
    }
}
