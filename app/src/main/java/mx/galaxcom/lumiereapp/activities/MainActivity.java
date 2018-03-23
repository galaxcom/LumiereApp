package mx.galaxcom.lumiereapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.activities.inventario.CategoriasProductoActivity;
import mx.galaxcom.lumiereapp.activities.pedidos.PedidosOpcionesActivity;
import mx.galaxcom.lumiereapp.vista.MainActivityView;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void inventarioOnClick(View v) {
        Intent intent = new Intent(MainActivity.this, CategoriasProductoActivity.class);
        startActivity(intent);
    }

    @Override
    public void pedidosOnclick(View v) {
        Intent intent = new Intent(MainActivity.this, PedidosOpcionesActivity.class);
        startActivity(intent);
    }
}
