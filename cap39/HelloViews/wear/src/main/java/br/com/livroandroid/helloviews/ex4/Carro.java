package br.com.livroandroid.helloviews.ex4;

import java.io.Serializable;

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
}
