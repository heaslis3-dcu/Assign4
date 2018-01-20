package id_16109759_hdsd.assign42017seanheaslip;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;


/**
 * Created by seanh on 27/12/2017.
 */
/*
public class FragmentAccount extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment4_account, container, false);
        return rootView;

    }
}*/

/**
 * Code modified from:
 * Reference: https://stackoverflow.com/questions/29974539/
 * https://github.com/afarber/android-newbie/blob/master/
 * MyPrefs/src/de/afarber/myprefs/PrefFragment.java
 * Date:15/01/2018
 */

public class FragmentAccount extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener
{
    EditTextPreference mCustName;
    EditTextPreference mCustPW;
    EditTextPreference mCustEmail;
    CheckBoxPreference mHideCustPW;


    //Initialised to ids in preference.xml
    public static final String BOOLEAN_LOYALTY_CARD = "boolean_1";
    public static final String HIDE_CUSTOMER_PASSWORD = "hidePW";
    public static final String CUSTOMER_NAME = "custName";
    public static final String CUSTOMER_PASSWORD = "custPassword";
    public static final String CUSTOMER_EMAIL = "custEmail";

   public void onCreatePreferences(Bundle bundle, String s) {

       //Load the preferences from an XML Resource
       addPreferencesFromResource(R.xml.preferences);

       mCustName = (EditTextPreference) findPreference(CUSTOMER_NAME);
       mCustPW = (EditTextPreference) findPreference(CUSTOMER_PASSWORD);
       mCustEmail = (EditTextPreference) findPreference(CUSTOMER_EMAIL);
       mHideCustPW = (CheckBoxPreference) findPreference(HIDE_CUSTOMER_PASSWORD);

       getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
   }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences shrPref, String key)
    {
       // if statement to change preference summary text
        if(CUSTOMER_NAME.equals(key)) {
            //String strName = shrPref.getString(key, "");
            mCustName.setSummary(shrPref.getString(key, ""));
        }else if (CUSTOMER_EMAIL.equals(key)) {
           // String strEmail = shrPref.getString(key, "");
            mCustEmail.setSummary(shrPref.getString(key, ""));
        }
    }
    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = getPreferenceManager().getSharedPreferences();

        // CHANGE 1: load saved values to set the summaries
        onSharedPreferenceChanged(prefs, BOOLEAN_LOYALTY_CARD);
        onSharedPreferenceChanged(prefs, CUSTOMER_NAME);
        onSharedPreferenceChanged(prefs, CUSTOMER_PASSWORD);
        onSharedPreferenceChanged(prefs, CUSTOMER_EMAIL);

        // CHANGE 2: register shared prefs listener in onResume
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = getPreferenceManager().getSharedPreferences();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }


}
