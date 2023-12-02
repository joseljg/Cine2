package es.joseljg.cine2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import es.joseljg.cine2.clases.Entrada;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_ENTRADA = "es.joseljg.cine2.mainactivity.entrada" ;
    EditText edt_nombre;
    EditText edt_cantidad;
    ImageView img_persona;
    String tipo_usuario;
    String pelicula;
    Spinner sp_peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    // enlazo todas las variables
        edt_nombre = (EditText) findViewById(R.id.edt_nombre);
        edt_cantidad = (EditText) findViewById(R.id.edt_cantidad);
        img_persona = (ImageView) findViewById(R.id.img_persona);
        sp_peliculas = (Spinner) findViewById(R.id.sp_pelicula);
        tipo_usuario = "general";
        pelicula = "pelicula1";
        //--------------------------------------
        String[] peliculas = {"pelicula1","pelicula2","pelicula3","pelicula4"};
        if(sp_peliculas != null)
        {
           // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.peliculas, R.layout.item_lista_peliculas);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.item_lista_peliculas,peliculas);
            adapter.setDropDownViewResource(R.layout.item_lista_peliculas2);
            sp_peliculas.setAdapter(adapter);
            sp_peliculas.setOnItemSelectedListener(this);
        }
    }

    public void elegirTipoPersona(View view)
    {
        RadioButton rb = (RadioButton) view;
        if(rb.isChecked())
        {
            switch (rb.getId())
            {
                case R.id.rb_infantil:
                    tipo_usuario ="infantil";
                    break;
                case R.id.rb_adulto:
                    tipo_usuario="general";
                    break;
                case R.id.rb_jubilado:
                    tipo_usuario="jubilado";
                    break;
                default:
                    tipo_usuario="general";
            }
        }
        Toast.makeText(this,"tipo->" + tipo_usuario,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.sp_pelicula:
                pelicula = String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(this, "pelicula->" + pelicula, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        switch (adapterView.getId())
        {
            case R.id.sp_pelicula:
                pelicula = String.valueOf(adapterView.getItemAtPosition(0));
                Toast.makeText(this,"pelicula->" + pelicula, Toast.LENGTH_LONG).show();
                break;

        }

    }

    public void irAPantalla2(View view)
    {
        String nombre = String.valueOf(edt_nombre.getText());
        boolean errores = false;
        if(nombre.isEmpty())
        {
            edt_nombre.setError("debes escribir un nombre ");
            errores = true;
        }
        String textoCantidad = String.valueOf(edt_cantidad.getText());
        if(textoCantidad.isEmpty())
        {
            edt_cantidad.setError("debes escribir una cantidad");
            errores = true;
        }
        if(errores == true)
        {
            return;
        }
        int cantidad = Integer.valueOf(textoCantidad);
        String fecha = "19/10/2023";
        String hora = "8:52";
        Entrada e1 = new Entrada(nombre,tipo_usuario,cantidad,pelicula,fecha,hora);
        Intent intent = new Intent(this,Ventana2Activity.class);
        intent.putExtra(EXTRA_ENTRADA,e1);
        startActivity(intent);
    }

}