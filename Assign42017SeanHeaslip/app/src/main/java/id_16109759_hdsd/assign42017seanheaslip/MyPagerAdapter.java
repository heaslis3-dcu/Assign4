package id_16109759_hdsd.assign42017seanheaslip;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by seanh on 27/12/2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter
{
//Using Context to call String resource for Page Title

    int pageTitleLength = 4;


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */


        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            // getItem is called to instantiate the fragment for the given page.
            // Return relevant Fragment (defined as a static inner class below).
            switch (position)
            {
                case 0:
                    return new FragmentInfo();
                case 1:
                    return new FragmentListChemView();
                case 2:
                    return new FragmentOrder();
                case 3:
                    return new FragmentAccount();
            }
            return null;
        }

        @Override
        public int getCount()
        {
            // Show 4 total pages.
            // note could not figure out how to determine length of resource String-array
            return pageTitleLength;
        }

    /**
     * Citation:
     * 27/12/2017 @15:20
     * Referenced use of 'Context' from:
     * https://stackoverflow.com/questions/18578567/
     */
        @Override
        public CharSequence getPageTitle(int position)
        {
            //Removed hardcoded Strings
            // Could try using Array and use position to return the value
            switch (position)
            {
                case 0:
                    return "INFO";
                case 1:
                    return "LIST";
                case 2:
                    return "ORDER";
                case 3:
                    return "ACCOUNT";
            }
            return null;
        }
}
