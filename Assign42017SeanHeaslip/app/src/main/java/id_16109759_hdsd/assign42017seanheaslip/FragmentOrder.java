package id_16109759_hdsd.assign42017seanheaslip;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by seanh on 27/12/2017.
 */


public class FragmentOrder extends Fragment
{
    private static String emailTo, emailSubject, emailName, emailMsgPart1, emailMsgPart2, emailRegards;
    private static final String TAG = "Assign3";
    private static final int REQUEST_SHARE = 39714;
    private static final int REQUSET_EMAIL = 1;
    private File m_ImageFile;
    private Uri m_ImageUri;
    private String imageFileName = "myImage.jpg";
    private Spinner m_SpinnerClick;
    private TextView mSpinnerTxtV;
    private EditText edtText_Name, edtTxt_OtherInstruc, edtPhoneNum;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState )
    {
        /**
         * Replace Toolbar
         * Code modified from:
         * Referenece: https://stackoverflow.com/questions/26987503
         * Date: 15/01/2018
         */
        //
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment3_order, container, false);


        edtText_Name = (EditText) rootView.findViewById(R.id.edtTextFrom);
        edtTxt_OtherInstruc = (EditText) rootView.findViewById(R.id.edTxtDescription);
        edtPhoneNum = (EditText) rootView.findViewById(R.id.edtPhoneNum);

        //Initialising Strings from resource - To email address
        emailTo = getResources().getString(R.string.ToEmail);
        emailSubject = getResources().getString(R.string.Msg_Subject); //Subject
        emailName = getResources().getString(R.string.Msg_Name); //Message details
        emailMsgPart1 = getResources().getString(R.string.Msg_Part1); //Message details
        emailMsgPart2 = getResources().getString(R.string.Msg_Part2); //Message details
        emailRegards = getResources().getString(R.string.Msg_Part3); //Message details
        //Spinner - selection Array Adapater
        m_SpinnerClick = (Spinner) rootView.findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.collectionTimes,
                android.R.layout.simple_spinner_dropdown_item);
        m_SpinnerClick.setAdapter(adapter);
        //Spinner setting on item selected listener, have tested this code both inside onCreate and outside onCreate.
        // Both worked fine but could use guidance on which is best practice.
        m_SpinnerClick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            //Implement OnItemSelectedListener Methods
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                mSpinnerTxtV = (TextView) view;

                // m_SpinnerStr = mSpinnerTxtV.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //Not required for this app
            }
        });
        /**
         * Citation:
         * Between 27/11/2017 and 11/12/2017
         * Licence include above
         * MediaStore reference: Constants: ACTION_IMAGE_CAPTURE
         * https://developer.android.com/reference/android/provider/MediaStore.html
         * https://developer.android.com/reference/android/provider/MediaStore.html#ACTION_IMAGE_CAPTURE
         * Method used to open Camera and capture image.
         *
         * @param view
         */
        //onClickListener for ImageButton in Order Fragment
        ImageButton imgBtn = (ImageButton) rootView.findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG, "Camera has been activated.");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                m_ImageFile = new File(Environment.getExternalStorageDirectory(), imageFileName);
                m_ImageUri = Uri.fromFile(m_ImageFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, m_ImageUri);
                startActivityForResult(intent, REQUEST_SHARE);//note number of request_share
            }
        });

        /**
         * Method checks that name and time are entered and the prescription receipt is taken
         * prior to allowing open Email intent proceeding. This is the minimum required information.
         *
         * @param view // open Intent email
         */


    /*
        code adapted from method "process"
        Found on YouTube - SlideNerd Android Tutorials - Videos 29/30
        https://www.youtube.com/watch?v=nj-STGrL7Zc&index=29&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
     */
        //onClickListener for Sending Email in Order Fragment
        /* REMOVED AND REPLACED
        Button emailBtn = (Button) rootView.findViewById(R.id.sendOrderBtn);
        emailBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //If statement to prevent user opening email without first entering a name
                if (edtText_Name.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "You must enter your NAME to proceed!", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Name not entered.");
                } else if (m_ImageUri == null)
                {
                    Toast.makeText(getActivity(), "The prescription photo has not been taken!", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Image not taken.");
                } else if (mSpinnerTxtV.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "Oops! You forgot the collection time!", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Collection Time not selected.");
                } else
                {
                    Log.i(TAG, "Entering Intent to start email activity.");
                    //Procced with Intent to open email and send details including image, From and time
                    Intent openEmailIntent = new Intent(Intent.ACTION_SEND);
                    openEmailIntent.setType("image/*"); // lets app know image will be attached
                    //Sunject Line from EditText - edtText_Subject
                    openEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
                    //Email TO: field populated by String Resource
                    openEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailTo});
                    //openEmailIntent.setType("text/html");
                    openEmailIntent.putExtra(Intent.EXTRA_TEXT,
                            emailName + " " + edtText_Name.getText().toString() +
                                    "\n\n" + emailMsgPart1 + " " +
                                    mSpinnerTxtV.getText().toString() +
                                    " " + emailMsgPart2 +
                                    " " + edtTxt_OtherInstruc.getText().toString() +
                                    "\n\n" + emailRegards +
                                    "\n\n " + edtText_Name.getText().toString() +
                                    "\n\n" + edtPhoneNum.getText().toString()
                    );
                    openEmailIntent.putExtra(Intent.EXTRA_STREAM, m_ImageUri);
                    //MIME allowing exchange of different types of data through email
                    openEmailIntent.setType("message/rfc822");
                    //Start Activity - open chooser to send email
                    startActivity(openEmailIntent.createChooser(openEmailIntent, "Send Email"));
                }
            }
        });
*/
        return rootView;
    }



    /**
     * Replace Toolbar
     * Code modified from:
     * Referenece: https://stackoverflow.com/questions/26987503
     * Date: 15/01/2018
     * Added menu.clear() on 18/01/2018 from:
     * https://stackoverflow.com/questions/30721664
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
       // menu.clear(); //Empty old menu - originally
        inflater.inflate(R.menu.menu_order,menu);
        super.onCreateOptionsMenu(menu, inflater);
}

    /**
     * Method used to run code based on menu item selected.
     * Email chooser selected via share icon.
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

        //If Share icon selected - run code to share oder details.
        if (id == R.id.action_send2)
        {
            //If statement to prevent user opening email without first entering a name
            if (edtText_Name.getText().toString().isEmpty())
            {
                Toast.makeText(getActivity(), "You must enter your NAME to proceed!", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Name not entered.");
            } else if (m_ImageUri == null)
            {
                Toast.makeText(getActivity(), "The prescription photo has not been taken!", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Image not taken.");
            } else if (mSpinnerTxtV.getText().toString().isEmpty())
            {
                Toast.makeText(getActivity(), "Oops! You forgot the collection time!", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Collection Time not selected.");
            } else
            {
                Log.i(TAG, "Entering Intent to start email activity.");
                //Procced with Intent to open email and send details including image, From and time
                Intent openEmailIntent = new Intent(Intent.ACTION_SEND);
                openEmailIntent.setType("image/*"); // lets app know image will be attached
                //Sunject Line from EditText - edtText_Subject
                openEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
                //Email TO: field populated by String Resource
                openEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailTo});
                //openEmailIntent.setType("text/html");
                openEmailIntent.putExtra(Intent.EXTRA_TEXT,
                        emailName + " " + edtText_Name.getText().toString() +
                                "\n\n" + emailMsgPart1 + " " +
                                mSpinnerTxtV.getText().toString() +
                                " " + emailMsgPart2 +
                                " " + edtTxt_OtherInstruc.getText().toString() +
                                "\n\n" + emailRegards +
                                "\n\n " + edtText_Name.getText().toString() +
                                "\n\n" + edtPhoneNum.getText().toString()
                );
                openEmailIntent.putExtra(Intent.EXTRA_STREAM, m_ImageUri);
                //MIME allowing exchange of different types of data through email
                openEmailIntent.setType("message/rfc822");
                //Start Activity - open chooser to send email
                startActivity(openEmailIntent.createChooser(openEmailIntent, "Send Email"));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Entering onActivityResult
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        Log.i(TAG, "Entering onActivityResult()");
        //Checking request for message:
        if (requestCode == REQUEST_SHARE)
        {
            if (resultCode == RESULT_OK)
            {
                Log.i(TAG, "Image saved to filepath.");
                //Toast.makeText(this, "ImageFilePath is: " + m_ImageFile, Toast.LENGTH_LONG).show();
            } else
            {
                Toast.makeText(getActivity(), "There was an error saving your accessing your image", Toast.LENGTH_SHORT).show();
            }
        }
    }

}





//
//public class FragmentOrder extends Fragment
//{
//    private static String emailTo, emailSubject, emailName, emailMsgPart1, emailMsgPart2, emailRegards;
//    private static final String TAG = "Assign3";
//    private static final int REQUEST_SHARE = 39714;
//    private static final int REQUSET_EMAIL = 1;
//    private File m_ImageFile;
//    private Uri m_ImageUri;
//    private String imageFileName = "myImage.jpg";
//    private Spinner m_SpinnerClick;
//    private TextView mSpinnerTxtV;
//    private EditText edtText_Name, edtTxt_OtherInstruc, edtPhoneNum;
//
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState )
//    {
//        /**
//         * Replace Toolbar
//         * Code modified from:
//         * Referenece: https://stackoverflow.com/questions/26987503
//         * Date: 15/01/2018
//         */
//        //
//        setHasOptionsMenu(true);
//
//        View rootView = inflater.inflate(R.layout.fragment3_order, container, false);
//
//
//        edtText_Name = (EditText) rootView.findViewById(R.id.edtTextFrom);
//        edtTxt_OtherInstruc = (EditText) rootView.findViewById(R.id.edTxtDescription);
//        edtPhoneNum = (EditText) rootView.findViewById(R.id.edtPhoneNum);
//
//        //Initialising Strings from resource - To email address
//        emailTo = getResources().getString(R.string.ToEmail);
//        emailSubject = getResources().getString(R.string.Msg_Subject); //Subject
//        emailName = getResources().getString(R.string.Msg_Name); //Message details
//        emailMsgPart1 = getResources().getString(R.string.Msg_Part1); //Message details
//        emailMsgPart2 = getResources().getString(R.string.Msg_Part2); //Message details
//        emailRegards = getResources().getString(R.string.Msg_Part3); //Message details
//        //Spinner - selection Array Adapater
//        m_SpinnerClick = (Spinner) rootView.findViewById(R.id.spinner);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.collectionTimes,
//                android.R.layout.simple_spinner_dropdown_item);
//        m_SpinnerClick.setAdapter(adapter);
//        //Spinner setting on item selected listener, have tested this code both inside onCreate and outside onCreate.
//        // Both worked fine but could use guidance on which is best practice.
//        m_SpinnerClick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            //Implement OnItemSelectedListener Methods
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
//            {
//                mSpinnerTxtV = (TextView) view;
//
//                // m_SpinnerStr = mSpinnerTxtV.getText().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView)
//            {
//                //Not required for this app
//            }
//        });
//
//
//        /**
//         * Citation:
//         * Between 27/11/2017 and 11/12/2017
//         * Licence include above
//         * MediaStore reference: Constants: ACTION_IMAGE_CAPTURE
//         * https://developer.android.com/reference/android/provider/MediaStore.html
//         * https://developer.android.com/reference/android/provider/MediaStore.html#ACTION_IMAGE_CAPTURE
//         * Method used to open Camera and capture image.
//         *
//         * @param view
//         */
//        //onClickListener for ImageButton in Order Fragment
//        ImageButton imgBtn = (ImageButton) rootView.findViewById(R.id.imageButton);
//        imgBtn.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Log.i(TAG, "Camera has been activated.");
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                m_ImageFile = new File(Environment.getExternalStorageDirectory(), imageFileName);
//                m_ImageUri = Uri.fromFile(m_ImageFile);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, m_ImageUri);
//                startActivityForResult(intent, REQUEST_SHARE);//note number of request_share
//            }
//        });
//
//
//        /**
//         * Method checks that name and time are entered and the prescription receipt is taken
//         * prior to allowing open Email intent proceeding. This is the minimum required information.
//         *
//         * @param view // open Intent email
//         */
//    /*
//        code adapted from method "process"
//        Found on YouTube - SlideNerd Android Tutorials - Videos 29/30
//        https://www.youtube.com/watch?v=nj-STGrL7Zc&index=29&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
//     */
//        //onClickListener for Sending Email in Order Fragment
//        Button emailBtn = (Button) rootView.findViewById(R.id.sendOrderBtn);
//        emailBtn.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                //If statement to prevent user opening email without first entering a name
//                if (edtText_Name.getText().toString().isEmpty())
//                {
//                    Toast.makeText(getActivity(), "You must enter your NAME to proceed!", Toast.LENGTH_SHORT).show();
//                    Log.i(TAG, "Name not entered.");
//                } else if (m_ImageUri == null)
//                {
//                    Toast.makeText(getActivity(), "The prescription photo has not been taken!", Toast.LENGTH_SHORT).show();
//                    Log.i(TAG, "Image not taken.");
//                } else if (mSpinnerTxtV.getText().toString().isEmpty())
//                {
//                    Toast.makeText(getActivity(), "Oops! You forgot the collection time!", Toast.LENGTH_SHORT).show();
//                    Log.i(TAG, "Collection Time not selected.");
//                } else
//                {
//                    Log.i(TAG, "Entering Intent to start email activity.");
//                    //Procced with Intent to open email and send details including image, From and time
//                    Intent openEmailIntent = new Intent(Intent.ACTION_SEND);
//                    openEmailIntent.setType("image/*"); // lets app know image will be attached
//                    //Sunject Line from EditText - edtText_Subject
//                    openEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
//                    //Email TO: field populated by String Resource
//                    openEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailTo});
//                    //openEmailIntent.setType("text/html");
//                    openEmailIntent.putExtra(Intent.EXTRA_TEXT,
//                            emailName + " " + edtText_Name.getText().toString() +
//                                    "\n\n" + emailMsgPart1 + " " +
//                                    mSpinnerTxtV.getText().toString() +
//                                    " " + emailMsgPart2 +
//                                    " " + edtTxt_OtherInstruc.getText().toString() +
//                                    "\n\n" + emailRegards +
//                                    "\n\n " + edtText_Name.getText().toString() +
//                                    "\n\n" + edtPhoneNum.getText().toString()
//                    );
//                    openEmailIntent.putExtra(Intent.EXTRA_STREAM, m_ImageUri);
//                    //MIME allowing exchange of different types of data through email
//                    openEmailIntent.setType("message/rfc822");
//                    //Start Activity - open chooser to send email
//                    startActivity(openEmailIntent.createChooser(openEmailIntent, "Send Email"));
//                }
//            }
//        });
//
//        return rootView;
//    }
//
//
//    /**
//     * Replace Toolbar
//     * Code modified from:
//     * Referenece: https://stackoverflow.com/questions/26987503
//     * Date: 15/01/2018
//     * @param menu
//     * @param inflater
//     */
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        inflater.inflate(R.menu.menu_order,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_send2)
//        {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * Entering onActivityResult
//     *
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//
//        Log.i(TAG, "Entering onActivityResult()");
//        //Checking request for message:
//        if (requestCode == REQUEST_SHARE)
//        {
//            if (resultCode == RESULT_OK)
//            {
//                Log.i(TAG, "Image saved to filepath.");
//                //Toast.makeText(this, "ImageFilePath is: " + m_ImageFile, Toast.LENGTH_LONG).show();
//            } else
//            {
//                Toast.makeText(getActivity(), "There was an error saving your accessing your image", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//}
//
//
