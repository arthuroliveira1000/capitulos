package br.com.livroandroid.helloviews.ex6;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.livroandroid.helloviews.R;
import br.com.livroandroid.shared.Carro;
import livroandroid.lib.utils.ImageResizeUtils;

/**
 * Created by ricardo on 19/04/15.
 */
public class CarrosGridPagerAdapter extends FragmentGridPagerAdapter {
    private final LayoutInflater mInflater;
    private Context context;
    private List<Carro> classicos;
    private List<Carro> esportivos;
    private List<Carro> luxo;

    public CarrosGridPagerAdapter(Context context,FragmentManager fm, List<Carro> classicos,List<Carro> esportivos,List<Carro> luxo) {
        super(fm);
        this.context = context;
        this.classicos = classicos;
        this.esportivos = esportivos;
        this.luxo = luxo;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public Fragment getFragment(int row, int col) {
        Carro c = classicos.get(row);
        if(col == 1) {
            c = esportivos.get(row);
        } else if(col == 2) {
            c = luxo.get(row);
        }
        /*CarroCardFragment card = new CarroCardFragment();
        Bundle args = new Bundle();
        args.putString("nome",c.nome);
        args.putInt("img",c.img);
        card.setArguments(args);*/
        CardFragment card = CardFragment.create("Carro", c.nome, c.img);
        return card;
    }

    public static Uri ResourceToUri (Context context,int resID) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                context.getResources().getResourcePackageName(resID) + '/' +
                context.getResources().getResourceTypeName(resID) + '/' +
                context.getResources().getResourceEntryName(resID) );
    }

    @Override
    public int getRowCount() {
        return classicos.size();
    }

    @Override
    public int getColumnCount(int i) {
        return 3;
    }
}
