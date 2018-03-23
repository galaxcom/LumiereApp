package mx.galaxcom.lumiereapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.adapters.viewHolders.CategoriaViewHolder;
import mx.galaxcom.lumiereapp.adapters.viewHolders.ProductoViewHolder;
import mx.galaxcom.lumiereapp.pojo.CategoriasProductoERV;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;

/**
 * Created by david on 15/03/2018.
 */

public class ProductoAgregarPedidoERVAdapter extends CheckableChildRecyclerViewAdapter<CategoriaViewHolder, ProductoViewHolder> {
    CategoriaViewHolder categoriaViewHolder;
    ProductoViewHolder productoViewHolder;


    public ProductoAgregarPedidoERVAdapter(List<CategoriasProductoERV> groups) {
        super(groups);
    }

    @Override
    public ProductoViewHolder onCreateCheckChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_agregar_pedido, parent, false);
        productoViewHolder = new ProductoViewHolder(view);
        return productoViewHolder;
    }

    @Override
    public void onBindCheckChildViewHolder(ProductoViewHolder holder, int flatPosition, CheckedExpandableGroup group, int childIndex) {
        final ProductoInventario producto = (ProductoInventario) group.getItems().get(childIndex);
        holder.setNombreProducto(producto.getNombre());
    }

    @Override
    public CategoriaViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grupo_producto_agregar_pedido, parent, false);
        categoriaViewHolder = new CategoriaViewHolder(view);
        return categoriaViewHolder;
    }

    @Override
    public void onBindGroupViewHolder(CategoriaViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setNombreCategoria(group);
    }

    public ProductoViewHolder obtenerProductoViewHolder() {
        return productoViewHolder;
    }

    public String obtenerCantidadET(){
        return obtenerProductoViewHolder().getEtCantidad().getText().toString();
    }

    public String obtenerPrecioUnitarioET(){
        return obtenerProductoViewHolder().getEtPrecioUnitario().getText().toString();
    }

}
