package id_16109759_hdsd.assign42017seanheaslip;
/* Copyright (C) 2016 The Android Open Source Project

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
        */
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// * Created by seanh on 27/12/2017.
/**
 * Contains onClickListener to start a Timer,
 * Contains basic information including
 */

public class FragmentInfo extends Fragment
{
    String msg = "I can set a timer!";
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
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message);
            startActivity(intent);
    }
}
