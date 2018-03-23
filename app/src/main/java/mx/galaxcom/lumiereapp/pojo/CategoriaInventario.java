package mx.galaxcom.lumiereapp.pojo;

import com.thoughtbot.expandablecheckrecyclerview.models.MultiCheckExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by david on 19/02/2018.
 */

public class CategoriaInventario{
    private int fotoProducto;
    private String nombreCategoria;
    private int id;

    public CategoriaInventario(int fotoProducto, String nombreCategoria){
        this.fotoProducto = fotoProducto;
        this.nombreCategoria = nombreCategoria;
    }

    public CategoriaInventario(){}

    public int getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(int fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
