package br.com.livroandroid.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardo on 19/04/15.
 */
public class Carro implements Serializable{
    public String nome;
    public int img;

    public Carro(String nome,int img) {
        this.nome = nome;
        this.img = img;
    }

    public static List<Carro> getListEsportivos() {
        List<Carro> list = new ArrayList<Carro>();
        list.add(new Carro("Ferrari FF",R.drawable.ferrari_ff));
        list.add(new Carro("AUDI GT Spyder",R.drawable.audi_spyder));
        list.add(new Carro("Porsche Panamera",R.drawable.porsche_panamera));
        list.add(new Carro("Lamborghini Aventador",R.drawable.lamborghini_aventador));
        list.add(new Carro("Chevrolet Corvette Z06",R.drawable.chevrolet_corvette_z06));
        list.add(new Carro("BMW M5",R.drawable.bmw));
        list.add(new Carro("Renault Megane",R.drawable.renault_megane_trophy));
        list.add(new Carro("Maserati Grancabrio Sport",R.drawable.maserati_grancabrio_sport));
        list.add(new Carro("McLAREN MP4-12C",R.drawable.mclaren));
        list.add(new Carro("MERCEDES-BENZ C63 AMG",R.drawable.mercedes_bens));
        return list;
    }
}
