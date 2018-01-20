package id_16109759_hdsd.assign42017seanheaslip;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
  /* Turned off 20:09 - 20/01/2018
    String mtitle = "Refill Prescription";
    String mlocation = "McCabes Pharmacy Santry";

    final static String ACTION = "NotifyServiceAction";
    final static String STOP_SERVICE_BROADCAST_KEY="StopServiceBroadcastKey";
    final static int RQS_STOP_SERVICE = 1;


    // Notification Sound and Vibration on Arrival
    private long[] mVibratePattern = { 0, 200, 200, 300 };
    NotifyService.NotifyServiceReceiver notifyServiceReceiver;
    // MP3 file obtained from:
    //https://notificationsounds.com/animals/bullfrog-477
    private Uri soundURI = Uri
            .parse("android.resource://"
                    + R.raw.alarm_bullfrog);
*/
  //Notification Variables
    String mNotificationTitle = "Demo of Notification!";
    private Uri soundURI = Uri
            .parse("android.resource://"
                    + R.raw.alarm_bullfrog);
    private long[] mVibratePattern = { 0, 200, 200, 300 };
    String mNotificationText = "Notification  and Feedback Unit: DCU Web Site";
    String mtitle = "Refill Prescription";
    String mlocation = "McCabes Pharmacy Santry";


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

        //Using String-array in resource file:
       // String[] pageTitleText = getResources().getStringArray(R.array.page_titles);

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
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/


    }


    //@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

        notification.setSmallIcon(R.drawable.ic_heart);
            notification.setTicker("Notification Started");
            notification.setWhen(System.currentTimeMillis());
            notification.setContentTitle(mNotificationTitle);
            notification.setContentText("Test");
                // .setLights(Color.RED,0,1)
            /*    notification.setLights(Color.BLUE, 5000, 5000);
                notification.setAutoCancel(true);
                notification.setSound(soundURI); */
               // notification.setVibrate(mVibratePattern);

                //notification.setContentText(mNotificationText);

            //Calendar Intent
            Intent myCalendarIntent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, mtitle)
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, mlocation);
            if(myCalendarIntent.resolveActivity(getPackageManager()) !=null){
                startActivity(myCalendarIntent);
            }

            //Opens Calendar Intent when notification clicked
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, myCalendarIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);

            //Builds notification and issues it
            NotificationManager notificationMngr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationMngr.notify(UNIQUE_ID, notification.build());


/* Turned off 20:09 - 20/01/2018
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ACTION);
            registerReceiver(notifyServiceReceiver, intentFilter);

            // Send Notification
            Context context = getApplicationContext();
            String notificationTitle = "Demo of Notification!";
            String notificationText = "Notification  and Feedback Unit: DCU Web Site";
*/

//            String title = "Refill Prescription";
//            String location = "McCabes Pharmacy Santry";

//            Intent myCalendarIntent = new Intent(Intent.ACTION_INSERT)
//                    .setData(CalendarContract.Events.CONTENT_URI)
//                    .putExtra(CalendarContract.Events.TITLE, title)
//                    .putExtra(CalendarContract.Events.EVENT_LOCATION, location);
//            if(myCalendarIntent.resolveActivity(getPackageManager()) !=null){
//                startActivity(myCalendarIntent);
//            }
  /* Turned off 20:09 - 20/01/2018
            PendingIntent pendingIntent
                    = PendingIntent.getActivity(getBaseContext(),
                    0, new Intent(this, MainActivity.class),0);
*/


            /**
             * PendingIntent pendingIntent
             = PendingIntent.getActivity(getBaseContext(),
             0, myCalendarIntent,
             Intent.FLAG_ACTIVITY_NEW_TASK);
             */
/*
            Intent myIntent = new Intent(Intent.ACTION_VIEW);
            PendingIntent pendingIntent
                    = PendingIntent.getActivity(getBaseContext(),
                    0, myIntent,
                    Intent.FLAG_ACTIVITY_NEW_TASK);
*/



/* Turned off 20:09 - 20/01/2018
            Notification notification = new Notification.Builder(this)
                    .setTicker("Service Started")
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(notificationTitle)
                    // .setLights(Color.RED,0,1)
                    .setLights(Color.BLUE, 5000, 5000)
                    .setAutoCancel(true)
                    .setSound(soundURI)
                    .setVibrate(mVibratePattern)
                    .setContentText(notificationText)
                    .setSmallIcon(R.drawable.ic_heart)
                    .setContentIntent(pendingIntent).build();


            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.flags = notification.flags
                    | Notification.FLAG_ONGOING_EVENT | Notification.FLAG_SHOW_LIGHTS;
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(0, notification);
*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Code obtained from Notify Demo
     *
     */
    /*
    public class NotifyServiceReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            int rqs = arg1.getIntExtra(STOP_SERVICE_BROADCAST_KEY, 0);

            if (rqs == RQS_STOP_SERVICE){

                ((NotificationManager) getSystemService(NOTIFICATION_SERVICE))
                        .cancelAll();
            }
        }
    }
    */
    public void myCalIntent(View view){
    Intent myCalendarIntent = new Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.Events.TITLE, mtitle)
            .putExtra(CalendarContract.Events.EVENT_LOCATION, mlocation);
    if(myCalendarIntent.resolveActivity(getPackageManager()) !=null){
        startActivity(myCalendarIntent);
    }
}

}
