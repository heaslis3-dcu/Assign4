package id_16109759_hdsd.assign42017seanheaslip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by seanh on 27/12/2017.
 */

public class FragmentListChemView extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.chemist_list_activ_main, container, false);


        // Create an ArrayList of AndroidFlavor objects
        final ArrayList<AndroidFlavor> androidFlavors = new ArrayList<AndroidFlavor>();
        androidFlavors.add(new AndroidFlavor("Cosmetics", "From €1.00!", R.drawable.cosmetics));
        androidFlavors.add(new AndroidFlavor("Candles", "Candles on discount at 50% off!", R.drawable.candles));
        androidFlavors.add(new AndroidFlavor("Meditation", "Checkout our mediation zone.", R.drawable.lavender));
        androidFlavors.add(new AndroidFlavor("Mens", "Enjoy a selection of mens products.", R.drawable.mens));
        androidFlavors.add(new AndroidFlavor("Makeup", "We have a wide range of makeup on offer", R.drawable.makeup));
        androidFlavors.add(new AndroidFlavor("Lipstick", "Lipsticks on sale 25% off!", R.drawable.lipstick));
        androidFlavors.add(new AndroidFlavor("Perfumes", "We have a wide range of perfumes on offer", R.drawable.cosmetics));
        androidFlavors.add(new AndroidFlavor("Woman", "Enjoy a selection of products for her!", R.drawable.mens));
        androidFlavors.add(new AndroidFlavor("Decorations", "Handmade decoration 10% off!", R.drawable.decorations));
        androidFlavors.add(new AndroidFlavor("For Her", "Checkout our mediation zone", R.drawable.makeup));

        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        AndroidFlavorAdapter flavorAdapter = new AndroidFlavorAdapter(getActivity(),androidFlavors);
        // Get a reference to the ListView, and attach the adapter to the listView.
       /** ImageView imgView = (ImageView) rootView.findViewById(R.id.list_item_icon);
        TextView txtView1 = (TextView) rootView.findViewById(R.id.version_name);
        TextView txtView2 = (TextView) rootView.findViewById(R.id.version_number); */
        ListView listView = (ListView) rootView.findViewById(R.id.listview_flavor);
        listView.setAdapter(flavorAdapter);

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
                String item = androidFlavors.get(position).getVersionName();
                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }
}
