package id_16109759_hdsd.assign42017seanheaslip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;

//Created by seanh on 20/01/2018.
/**
 * Included but not in use in app,
 * Class is used to start Calendar intent,
 */

public class OpenCalendar extends Activity
{
    String mtitle = "Refill Prescription";
    String mlocation = "McCabes Pharmacy Santry";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Calendar Intent
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

