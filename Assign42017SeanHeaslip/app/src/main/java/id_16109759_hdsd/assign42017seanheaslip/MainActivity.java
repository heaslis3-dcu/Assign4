package id_16109759_hdsd.assign42017seanheaslip;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Main activity containing Toolbar and Adapter,
 * link to menu item send, which activates notification (not working)
 * and launch Calendar intent (working)
 */
public class MainActivity extends AppCompatActivity
{

    /**
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     */

    //Notification Variables
    String mNotificationTitle = "Demo of Notification!";
    private Uri soundURI = Uri
            .parse("android.resource://"
                    + R.raw.alarm_bullfrog);
    private long[] mVibratePattern = {0, 200, 200, 300};
    String mNotificationText = "Notification  and Feedback Unit: DCU Web Site";
    String mtitle = "Refill Prescription";
    String mlocation = "McCabes Pharmacy, Northwood Park, Santry, Dublin, Ireland";

    private static final String TAG = "Assign4";
    private MyPagerAdapter mSectionsPagerAdapter;
    NotificationCompat.Builder notification;
    private static final int UNIQUE_ID = 78952;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //Notification Builder
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
    }


    //@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Notification and Calendar Intent handled here.
     * Note the code for Notification does not work - Ment Item clicked
     * results in Calendar opening.
     * Code
     *
     * @param item
     * @return
     */


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send)
        {
            Log.d(TAG, "Notification started"); //Notification Log message
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_heart)
                    .setTicker(getString(R.string.mNotificationTicker))
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(mNotificationTitle)
                    .setContentText(mNotificationText)
                    .setLights(Color.BLUE, 5000, 5000)
                    .setSound(soundURI)
                    .setVibrate(mVibratePattern)
                    .setAutoCancel(true);

            notificationBuilder.setContentText(mNotificationText);

            Log.d(TAG, "Calendar started"); //Calendar Log message
            //Calendar Intent
            Intent myCalendarIntent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, mtitle)
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, mlocation);
            if (myCalendarIntent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(myCalendarIntent);
            }

            //Opens Calendar Intent when notification clicked
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, myCalendarIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(pendingIntent);

            //Builds notification and issues it
            NotificationManager notificationMngr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationMngr.notify(UNIQUE_ID, notificationBuilder.build());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void myCalIntent(View view)
    {
        Intent myCalendarIntent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, mtitle)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, mlocation);
        if (myCalendarIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(myCalendarIntent);
        }
    }

}
