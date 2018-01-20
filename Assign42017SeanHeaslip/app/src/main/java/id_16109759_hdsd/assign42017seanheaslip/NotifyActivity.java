package id_16109759_hdsd.assign42017seanheaslip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by seanh on 20/01/2018.
 */

public class NotifyActivity extends AppCompatActivity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuItem buttonStartService = (MenuItem) findViewById(R.id.action_send);

        //buttonStartService.setOnMenuItemClickListener(setOnMenuItemClickListener(){

        //    });
        // buttonStartService.setOnClickListener(new MenuItem().OnClickListener(){

//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent intent = new Intent(NotifyActivity.this, NotifyService.class);
//                NotifyActivity.this.startService(intent);
//            }});

//    }

    }
}
