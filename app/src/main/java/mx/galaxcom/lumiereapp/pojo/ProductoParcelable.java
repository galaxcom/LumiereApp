package mx.galaxcom.lumiereapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by david on 02/03/2018.
 */

public class ProductoParcelable implements Parcelable {
    /*private int id;
    private String categoria;*/
    private String subCategoria;
    private String nombre;
    /*private int cantidad;
    private float precioVenta;*/

    public ProductoParcelable(String subCategoria, String nombre){
        this.subCategoria = subCategoria;
        this.nombre = nombre;

    }

    protected ProductoParcelable(Parcel in) {
        this.subCategoria = in.readString();
        this.nombre = in.readString();
    }


    public static final Creator<ProductoParcelable> CREATOR = new Creator<ProductoParcelable>() {
        @Override
        public ProductoParcelable createFromParcel(Parcel in) {
            return new ProductoParcelable(in);
        }

        @Override
        public ProductoParcelable[] newArray(int size) {
            return new ProductoParcelable[0/*size*/];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(subCategoria);
        parcel.writeString(nombre);

    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
