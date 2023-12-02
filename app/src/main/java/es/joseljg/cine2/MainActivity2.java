package es.joseljg.cine2;

import static es.joseljg.cine2.R.id.edt_hora1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText edt_fecha;
    EditText edt_hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edt_fecha = (EditText) findViewById(R.id.edt_fecha1);
        edt_hora = (EditText) findViewById(edt_hora1);
    }


    public void mostrar_calendario(View view) {
        // cargar el datepicker
        DatePickerFragment calendario1 = new DatePickerFragment();
        // mostrar el datepicker
        calendario1.show(getSupportFragmentManager(),"DatePicker");
    }


    public void mostrar_hora(View view) {
        // cargar el datepicker
        TimePickerFragment hora1 = new TimePickerFragment();
        // mostrar el datepicker
        hora1.show(getSupportFragmentManager(),"TimePicker");
    }

    public void crearHora(int hora, int minutos) {
        String textoHora = "";
        String textoMinutos ="";
        if(hora>=10)
        {
            textoHora=String.valueOf(hora);
        }
        else{
            textoHora="0"+String.valueOf(hora);
        }
        if(minutos>=10)
        {
            textoMinutos = String.valueOf(minutos);
        }
        else{
            textoMinutos = "0"+String.valueOf(minutos);
        }
        String textoFinal = textoHora + ":" + textoMinutos;
        edt_hora.setText(textoFinal);
    }

    public void crearFecha(int year, int month, int day) {
        String textoYear = String.valueOf(year);
        String textoMonth = String.valueOf(month+1);
        String textoDay = String.valueOf(day);
        String textoFecha = textoDay + "/"+ textoMonth + "/" + textoYear;
        edt_fecha.setText(textoFecha);
    }

    public void accion_boton_prueba(View view)
    {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("titulo1");
        alerta1.setMessage("mensaje1");
        alerta1.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mostrarMensaje("has elegido SI");
            }
        });
        alerta1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mostrarMensaje("has elegido NO");
            }
        });
        alerta1.show();
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show();
    }
}