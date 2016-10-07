package com.example.a1473031.assignment3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class NoteFragment extends Fragment {

    private Switch reminderSwitch;
    private TextView dateTextView;
    private TextView timeTextView;
    private CircleView category1;
    private CircleView category2;
    private CircleView category3;
    private CircleView category4;
    private CircleView category5;
    private CircleView category6;
    private CircleView category7;
    private CircleView category8;


    public NoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_note, container, false);


        reminderSwitch = (Switch) root.findViewById(R.id.reminder_switch);
        dateTextView = (TextView) root.findViewById(R.id.date_textView);
        timeTextView = (TextView) root.findViewById(R.id.time_textView);


        //Display or hide the reminder
        reminderSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dateTextView.getVisibility() == View.VISIBLE) {
                    dateTextView.setVisibility(View.GONE);
                    timeTextView.setVisibility(View.GONE);
                }else{
                    dateTextView.setVisibility(View.VISIBLE);
                    timeTextView.setVisibility(View.VISIBLE);
                }

            }
        });

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date initial = new Date();
                // create and show the TimePicker with starting time
                DialogFragment dialogFragment = DatePickerDialogFragment.createDatePicker(
                        initial,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //Get the date from the datePicker fragment
                                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
                                Date newDate = new Date();
                                String date = year + "/" +  month + "/" + dayOfMonth;
                                try {
                                    newDate = dateFormat.parse(date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                //set the text to the selected date
                                dateTextView.setText(newDate.toString());
                            }
                        });

                dialogFragment.show(getFragmentManager(), "datePicker");
            }
        });


        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date initial = new Date();
                // create and show the TimePicker with starting time
                DialogFragment dialogFragment = TimePickerDialogFragment.create(
                        initial,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Get the time from the timePicker fragment
                                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aaa");
                                Date newTime = new Date();
                                String date = hourOfDay + ":" + minute;
                                try {
                                    newTime = timeFormat.parse(date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                //Set the text to the selected time
                                timeTextView.setText(newTime.toString());
                            }
                        });
                dialogFragment.show(getFragmentManager(), "timePicker");
            }
        });


        return root;
    }


}
