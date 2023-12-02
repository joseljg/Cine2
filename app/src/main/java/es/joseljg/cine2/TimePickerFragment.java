package es.joseljg.cine2;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.TimeZone;


public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Calendar calendario1 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
        int hora = calendario1.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario1.get(Calendar.MINUTE);
        TimePickerDialog tpd1 = new TimePickerDialog(getActivity(),this,hora,minutos,true);
        return tpd1;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hora, int minutos) {
        MainActivity2 actividad2 = (MainActivity2) getActivity();
        actividad2.crearHora(hora,minutos);
    }
}