package mx.galaxcom.lumiereapp.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.pojo.CategoriaInventario;
import mx.galaxcom.lumiereapp.pojo.Cliente;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;
import mx.galaxcom.lumiereapp.pojo.SubCategoriaInventario;

/**
 * Created by david on 19/02/2018.
 */

public class ConstructorBaseDatos {
    private Context context;

    public ConstructorBaseDatos(Context context){
        this.context = context;
    }

    public ArrayList<CategoriaInventario> obtenerCategorias(){
        BaseDatos db = new BaseDatos(context);
        insertarCategorias(db);

        return db.obtenerCategorias();
    }


    //CATEGORÍAS
    public void insertarCategorias(BaseDatos db){
        /*MASCARILLAS*/
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLA_CATEGORIAS_FOTO, R.drawable.ic_launcher_background);
        contentValues.put(ConstantesBaseDatos.TABLA_CATEGORIAS_NOMBRE, "Mascarillas");

        db.insertarCategorias(contentValues);
    }


    //SUB-CATEGORÍAS
    public ArrayList<SubCategoriaInventario> obtenerSubCategorias(){
        BaseDatos db = new BaseDatos(context);
        insertarSubCategorias(db);

        return db.obtenerSubCategorias();
    }

    public void insertarSubCategorias(BaseDatos db){
        /*VASO*/
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_CATEGORIA, "Mascarillas");
        contentValues.put(ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_FOTO, R.drawable.ic_launcher_background);
        contentValues.put(ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_NOMBRE, "Vaso");

        db.insertarSubCategorias(contentValues);
        /*SOBRE*/
        ContentValues contentValues2 = new ContentValues();

        contentValues2.put(ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_CATEGORIA, "Mascarillas");
        contentValues2.put(ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_FOTO, R.drawable.ic_launcher_background);
        contentValues2.put(ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_NOMBRE, "Sobre");

        db.insertarSubCategorias(contentValues2);
    }

    //PRODUCTOS INVENTARIO
    public ArrayList<ProductoInventario> obtenerProductosInventario(){
        BaseDatos db = new BaseDatos(context);
        insertarProductosInventario(db);

        return db.obtenerProductosInventario();
    }

    public HashMap<String, List<String>> obtenerProductosInventarioMapa(List<String> grupos, String subCategoriaElegida){
        BaseDatos db = new BaseDatos(context);
        insertarProductosInventario(db);

        return db.obtenerProductosInventarioMapa(grupos, subCategoriaElegida);
    }

    public HashMap<String, List<String>> obtenerProductosInventarioNecesariosMapa(List<String> grupos, Activity activity){
        BaseDatos db = new BaseDatos(context);
        insertarProductosInventario(db);

        return db.obtenerProductosInventarioNecesariosMapa(grupos, activity);
    }

    public void insertarProductosInventario(BaseDatos db){
        /*VASO*/
        //Esmeralda
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        contentValues.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Vaso");
        contentValues.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Esmeralda");
        contentValues.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        contentValues.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(contentValues);

        //Perla
        ContentValues contentValues2 = new ContentValues();

        contentValues2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        contentValues2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Vaso");
        contentValues2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Perla");
        contentValues2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        contentValues2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(contentValues2);

        //Oro
        ContentValues contentValues3 = new ContentValues();

        contentValues3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        contentValues3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Vaso");
        contentValues3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Oro");
        contentValues3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        contentValues3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(contentValues3);

        //Coco
        ContentValues contentValues4 = new ContentValues();

        contentValues4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        contentValues4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Vaso");
        contentValues4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Coco");
        contentValues4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        contentValues4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(contentValues4);

        //Cocoa
        ContentValues contentValues5 = new ContentValues();

        contentValues5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        contentValues5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Vaso");
        contentValues5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Cocoa");
        contentValues5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        contentValues5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(contentValues5);

        //Frutos rojos
        ContentValues contentValues6 = new ContentValues();

        contentValues6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        contentValues6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Vaso");
        contentValues6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Frutos rojos");
        contentValues6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        contentValues6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(contentValues6);

        //Frutas Exóticas
        ContentValues contentValues7 = new ContentValues();

        contentValues7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        contentValues7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Vaso");
        contentValues7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Frutas Exóticas");
        contentValues7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        contentValues7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(contentValues7);

        /*SOBRE*/
        //BlackBerry
        ContentValues cv1 = new ContentValues();

        cv1.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        cv1.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Sobre");
        cv1.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "BlackBerry");
        cv1.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        cv1.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(cv1);

        //Colágeno
        ContentValues cv2 = new ContentValues();

        cv2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        cv2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Sobre");
        cv2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Colágeno");
        cv2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        cv2.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(cv2);

        //TeaTree
        ContentValues cv3 = new ContentValues();

        cv3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        cv3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Sobre");
        cv3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "TeaTree - Calmante");
        cv3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        cv3.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(cv3);

        //Arroz
        ContentValues cv4 = new ContentValues();

        cv4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        cv4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Sobre");
        cv4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Arroz - Aclarante");
        cv4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        cv4.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(cv4);

        //Barro
        ContentValues cv5 = new ContentValues();

        cv5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        cv5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Sobre");
        cv5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Barro");
        cv5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        cv5.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(cv5);

        //Oro
        ContentValues cv6 = new ContentValues();

        cv6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        cv6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Sobre");
        cv6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "Oro");
        cv6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        cv6.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(cv6);

        //StrawBerry
        ContentValues cv7 = new ContentValues();

        cv7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA, "Mascarillas");
        cv7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA, "Sobre");
        cv7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE, "StrawBerry");
        cv7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD, 0);
        cv7.put(ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA, 1.0F);

        db.insertarProductosInventario(cv7);
    }

    public void actualizarCantidadProducto(String productoSeleccionado, int cantidadAgregar){
        BaseDatos db = new BaseDatos(context);

        db.actualizarCantidadProducto(productoSeleccionado, cantidadAgregar);
    }

    public Cursor obtenerCursorProductosLV(String nombre){
        BaseDatos db = new BaseDatos(context);

        return db.obtenerCursorProductosLV(nombre);
    }

    public HashMap<String, List<String>> obtenerMapaProductos(List<String> categorias){
        BaseDatos db = new BaseDatos(context);

        return db.obtenerMapaProductos(categorias);
    }

    public ArrayList<ProductoInventario> obtenerProductosAgregados(Activity activity){
        BaseDatos db = new BaseDatos(context);

        return db.obtenerProductosAgregados(activity);
    }

    public ArrayList<ProductoInventario> obtenerProductosCategoria(String categoria){
        BaseDatos db = new BaseDatos(context);

        return db.obtenerProductosCategoria(categoria);
    }

    public void actualizarInventarioPedido(HashMap<String, Integer> productosAgregados){
        BaseDatos db = new BaseDatos(context);

        db.actualizarInventarioPedido(productosAgregados);
    }
    /*CLIENTES*/
    public ArrayList<Cliente> obtenerClientes(){
        BaseDatos db = new BaseDatos(context);

        insertarClientes();
        return db.obtenerClientes();
    }

    public HashMap<String, List<String>> obtenerMapaClientes(List<String> grupos){
        BaseDatos db = new BaseDatos(context);

        return db.obtenerMapaClientes(grupos);
    }

    public void insertarClientes(){
        BaseDatos db = new BaseDatos(context);

        ContentValues cv = new ContentValues();

        cv.put(ConstantesBaseDatos.TABLA_CLIENTES_NOMBRE, "Juan");
        cv.put(ConstantesBaseDatos.TABLA_CLIENTES_CORREO, "juan@gmail.com");

        db.insertarClientes(cv);
    }

}
