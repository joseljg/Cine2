package es.joseljg.cine2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.TimeZone;

import es.joseljg.cine2.clases.Entrada;

public class Ventana2Activity extends AppCompatActivity {

    private Entrada e1;
    private TextView txt_tipo = null;
    private TextView txt_precio_entrada = null;
    private TextView txt_cantidad = null;
    private TextView txt_total;
    private CheckBox ck_condiciones;
    private EditText edt_dia;
    private EditText edt_hora;

    private double precio_general;
    private double precio_infantil;
    private double precio_jubilado;
    private double precio_entrada;
    private double precio_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana2);
        txt_tipo = (TextView) findViewById(R.id.txt_tipo);
        txt_precio_entrada = (TextView) findViewById(R.id.txt_precio_entrada);
        txt_cantidad = (TextView) findViewById(R.id.txt_cantidad);
        txt_total = (TextView) findViewById(R.id.txt_total);
        edt_dia = (EditText) findViewById(R.id.edt_dia);
        edt_hora = (EditText) findViewById(R.id.edt_hora);
//--------------------------------------------------------
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        crearFecha(year, mes, dia);
//-------------------------------------------------------
        Calendar calendario1 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
        int hora = calendario1.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario1.get(Calendar.MINUTE);
        crearHora(hora,minutos);
        //----------------------------------------------
        ck_condiciones = (CheckBox) findViewById(R.id.ck_condiciones);
        Intent intent = getIntent();
        if(intent != null)
        {
            e1 = (Entrada) intent.getSerializableExtra(MainActivity.EXTRA_ENTRADA);
        }
        else{
            e1 = new Entrada();
        }
        txt_tipo.setText(e1.getTipo());
        // Toast.makeText(this,e1.toString(),Toast.LENGTH_LONG).show();

        precio_infantil = 3.0;
        precio_general= 5.0;
        precio_jubilado = 4.0;
        //------------------------------------------------------------
        if(e1.getTipo().equalsIgnoreCase("infantil"))
        {
            precio_entrada = precio_infantil;
        }
        else if(e1.getTipo().equalsIgnoreCase("general"))
        {
            precio_entrada = precio_general;
        }
        else if(e1.getTipo().equalsIgnoreCase("jubilado"))
        {
            precio_entrada = precio_jubilado;
        }
         //-------------------------------
        txt_precio_entrada.setText(String.valueOf(precio_entrada));
        txt_cantidad.setText(String.valueOf(e1.getCantidad()));
        precio_total = precio_entrada * e1.getCantidad();
        txt_total.setText(String.valueOf(precio_total));
    }

    public void ir_a_pago(View view)
    {
        if(ck_condiciones.isChecked())
        {
            Toast.makeText(this, " se está redirigiendo a paypal", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "debe aceptar las condiciones", Toast.LENGTH_LONG).show();
        }
    }

    public void mostrar_calendario(View view)
    {
        DatePickerFragment2 calendario = new DatePickerFragment2();
        calendario.show(getSupportFragmentManager(),"DatePicker");
    }

    public void mostrar_reloj1(View view)
    {
        TimePicketFragment2 reloj = new TimePicketFragment2();
        reloj.show(getSupportFragmentManager(),"TimePicker");
    }

    public void crearFecha(int year, int mes, int dia) {
        String textoyear = String.valueOf(year);
        String textomes = String.valueOf(mes+1);
        String textodia = String.valueOf(dia);
        String texto_final = textodia+"-"+textomes+"-"+textoyear;
        edt_dia.setText(texto_final);
    }

    public void crearHora(int hora, int minutos) {
        String textoHora = String.valueOf(hora);
        String textoMinutos = String.valueOf(minutos);
        String textofinal = textoHora+":"+textoMinutos;
        edt_hora.setText(textofinal);
    }
}