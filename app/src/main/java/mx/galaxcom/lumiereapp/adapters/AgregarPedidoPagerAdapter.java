package mx.galaxcom.lumiereapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import mx.galaxcom.lumiereapp.activities.pedidos.ConfigurarPedidoFragment;
import mx.galaxcom.lumiereapp.activities.pedidos.ListaProductosPedidoFragment;

/**
 * Created by david on 12/03/2018.
 */

public class AgregarPedidoPagerAdapter extends FragmentStatePagerAdapter{
    final int PAGE_COUNT = 2;
    private Context context;
    private final SparseArray<WeakReference<Fragment>> fragments = new SparseArray<>();


    public AgregarPedidoPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ConfigurarPedidoFragment tab1 = new ConfigurarPedidoFragment();
                return tab1;
            case 1:
                ListaProductosPedidoFragment tab2 = new ListaProductosPedidoFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fragments.delete(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getFragment(final int i){
        final WeakReference<Fragment> fragment = fragments.get(i);
        if (fragment != null){
            return fragment.get();
        }else {
            return null;
        }
    }


}
