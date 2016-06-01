package com.project.cibertec.finalproject.cliente;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.cibertec.finalproject.R;
import com.project.cibertec.finalproject.entities.Cliente;


public class ClientesDetalleActivity extends AppCompatActivity {

    private TextView tvClienteDetNombre, tvClienteDetApellido, tvClienteDetCorreo, tvClienteDetTelefono,
            tvClienteDetDireccion, tvClienteDetDistrito, tvClienteDetReferencia;
    private Button btnCliDetNuevoPedido;
    private Cliente mCliente = null;
    private ImageView ivListCliItemLlamada, ivListCliItemPuntero;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes_detalle_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvClienteDetNombre = (TextView) findViewById(R.id.tvClienteDetNombre);
        tvClienteDetApellido = (TextView) findViewById(R.id.tvClienteDetApellido);
        tvClienteDetCorreo = (TextView) findViewById(R.id.tvClienteDetCorreo);
        tvClienteDetTelefono = (TextView) findViewById(R.id.tvClienteDetTelefono);
        tvClienteDetDireccion = (TextView) findViewById(R.id.tvClienteDetDireccion);
        tvClienteDetDistrito = (TextView) findViewById(R.id.tvClienteDetDistrito);
        tvClienteDetReferencia = (TextView) findViewById(R.id.tvClienteDetReferencia);

        ivListCliItemLlamada = (ImageView) findViewById(R.id.ivListCliItemLlamada);
        ivListCliItemLlamada.setOnClickListener(ivListCliItemLlamadaOnClickListener);
        ivListCliItemPuntero = (ImageView) findViewById(R.id.ivListCliItemPuntero);
        ivListCliItemPuntero.setOnClickListener(ivListCliItemPunteroOnClickListener);

        btnCliDetNuevoPedido = (Button) findViewById(R.id.btnCliDetNuevoPedido);

        if (getIntent().getExtras().containsKey("cliente")) {
            mCliente = (Cliente) getIntent().getExtras().get("cliente");
            setTitle(mCliente.getEmpresa());
            fillData();
        }

    }

    View.OnClickListener ivListCliItemLlamadaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Intent callIntent = new Intent(Intent.ACTION_CALL, null);
                callIntent.setData(Uri.parse("tel:" + mCliente.getTelefono()));
                if (ActivityCompat.checkSelfPermission(ClientesDetalleActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            } catch (ActivityNotFoundException activityException) {
                Log.d("Calling a Phone Number", "Call failed"
                        + activityException);
            }
        }
    };


    View.OnClickListener ivListCliItemPunteroOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mapa = new Intent(ClientesDetalleActivity.this, ClientesMapActivity.class);
            mapa.putExtra("cliente",mCliente);
            startActivity(mapa);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void fillData(){
        tvClienteDetNombre.setText(mCliente.getNombre());
        tvClienteDetApellido.setText(mCliente.getApellido());
        tvClienteDetCorreo.setText(mCliente.getCorreo());
        tvClienteDetTelefono.setText(mCliente.getTelefono());
        tvClienteDetDireccion.setText(mCliente.getDireccion());
        tvClienteDetDistrito.setText(mCliente.getDistrito());
        tvClienteDetReferencia.setText(mCliente.getReferencia());
    }

}
