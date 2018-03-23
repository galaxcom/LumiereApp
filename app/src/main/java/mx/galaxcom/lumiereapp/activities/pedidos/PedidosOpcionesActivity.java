package mx.galaxcom.lumiereapp.activities.pedidos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.vista.pedidos.PedidosOpcionesActivityView;

public class PedidosOpcionesActivity extends AppCompatActivity implements PedidosOpcionesActivityView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_opciones);
    }

    @Override
    public void agregarPedidoOnClick(View view) {
        Intent intent = new Intent(PedidosOpcionesActivity.this, AgregarPedidoActivity.class);
        startActivity(intent);
    }
}
