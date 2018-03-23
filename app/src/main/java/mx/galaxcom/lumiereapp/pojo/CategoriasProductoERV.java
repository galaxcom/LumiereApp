package mx.galaxcom.lumiereapp.pojo;

import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablecheckrecyclerview.models.MultiCheckExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by david on 13/03/2018.
 */

public class CategoriasProductoERV extends MultiCheckExpandableGroup {
    private int fotoProducto;
    private String nombreCategoria;
    private int id;

    public CategoriasProductoERV(String title, List<ProductoInventario> items) {
        super(title, items);
    }

    public int getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(int fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    public String getNombreCategoria() {
        return super.getTitle();
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
