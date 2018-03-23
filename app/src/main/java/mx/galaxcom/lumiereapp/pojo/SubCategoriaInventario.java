package mx.galaxcom.lumiereapp.pojo;

/**
 * Created by david on 20/02/2018.
 */

public class SubCategoriaInventario extends CategoriaInventario {
    private String nombreSubCategoria;

    public SubCategoriaInventario(int fotoProducto, String nombreCategoria, String nombreSubCategoria){
        super.setFotoProducto(fotoProducto);
        super.setNombreCategoria(nombreCategoria);
        this.nombreSubCategoria = nombreSubCategoria;

    }

    public SubCategoriaInventario(){}

    public String getNombreSubCategoria() {
        return nombreSubCategoria;
    }

    public void setNombreSubCategoria(String nombreSubCategoria) {
        this.nombreSubCategoria = nombreSubCategoria;
    }
}
