package id_16109759_hdsd.assign42017seanheaslip;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by seanh on 27/12/2017.
 */

public class FragmentInfo extends Fragment
{
    String msg = "Hello!";
    int secs = 10;
    FloatingActionButton timer;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment1_info, container, false);
        //Floating Action Button - Timer
        FloatingActionButton timerFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        timerFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startTimer(msg, secs);
            }
        });

        return rootView;
    }
    public void startTimer(String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds);
            startActivity(intent);
    }
}
