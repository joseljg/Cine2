package es.joseljg.cine2;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.TimeZone;


public class TimePicketFragment2 extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minutos = c.get(Calendar.MINUTE);
        TimePickerDialog tpd1 = new TimePickerDialog(getActivity(),this,hora,minutos,true);
        return tpd1;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hora, int minutos) {
        Ventana2Activity v2 = (Ventana2Activity) getActivity();
        v2.crearHora(hora,minutos);
    }
}