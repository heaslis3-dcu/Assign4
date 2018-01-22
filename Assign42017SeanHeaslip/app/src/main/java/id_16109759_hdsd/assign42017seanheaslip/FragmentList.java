package id_16109759_hdsd.assign42017seanheaslip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//Created by seanh on 27/12/2017.
/**
 * Class extends Fragment, contains ArrayList and onClickListener
 * to produce Toast containing item selected from list.
 */

public class FragmentList extends Fragment
{
    private static final String TAG = "Assign4";
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.chemist_list_activ_main, container, false);

        // Create an ArrayList of ChemistList objects
        final ArrayList<ChemistList> chemistARList = new ArrayList<ChemistList>();
        chemistARList.add(new ChemistList("Cosmetics", "From €1.00!", R.drawable.cosmetics));
        chemistARList.add(new ChemistList("Candles", "Candles on discount at 50% off!", R.drawable.candles));
        chemistARList.add(new ChemistList("Meditation", "Checkout our mediation zone.", R.drawable.lavender));
        chemistARList.add(new ChemistList("Mens", "Enjoy a selection of mens products.", R.drawable.mens));
        chemistARList.add(new ChemistList("Makeup", "We have a wide range of makeup on offer", R.drawable.makeup));
        chemistARList.add(new ChemistList("Lipstick", "Lipsticks on sale 25% off!", R.drawable.lipstick));
        chemistARList.add(new ChemistList("Perfumes", "We have a wide range of perfumes on offer", R.drawable.cosmetics));
        chemistARList.add(new ChemistList("Woman", "Enjoy a selection of products for her!", R.drawable.mens));
        chemistARList.add(new ChemistList("Decorations", "Handmade decoration 10% off!", R.drawable.decorations));
        chemistARList.add(new ChemistList("For Her", "Checkout our mediation zone", R.drawable.makeup));

        // Create an {@link ChemistListAdapter}, whose data source is a list of
        // {@link ChemistList}s. The adapter knows how to create list item views for each item
        // in the list.
        ChemistListAdapter chemistListAdapter = new ChemistListAdapter(getActivity(), chemistARList);
        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_flavor);
        listView.setAdapter(chemistListAdapter);

    /*
    * Date 30/11/2017
    * Citation - code modified from:
    * http://www.vogella.com/tutorials/AndroidListView/article.html#exercise_listsactivity_simple
    * Section 2.6 Listener,
    * Section 17. Tutorial: Miscellaneous
    */
   /* sets listener
    *
    * Does not require clickable added to xml
    */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Log.d(TAG, "List item clicked");
                String item = chemistARList.get(position).getVersionName();
                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
