package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vukhachoi.muisicapp.Fragment_List;

/**
 * Created by Vu Khac Hoi on 9/20/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    public SimpleFragmentPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
           // return  new food_fragment();
        } else if (position == 1){
           // return new juice_fragment();
        } else if (position == 2){
           // return new cream_fragment();

        } else {
           // return new food_fragment();
        }
        return new Fragment_List();
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
                return "Thể loại";

            default:
                return null;
        }
    }
}
