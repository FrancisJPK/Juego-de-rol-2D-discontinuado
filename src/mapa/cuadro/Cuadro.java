package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
	public int x;
	public int y;

	public Sprite sprite;

	public static final int LADO = 32;

	// coleccion de cuadros
	public static final Cuadro ASFALTO = new Cuadro(Sprite.asfalto);
	public static final Cuadro ARENA = new Cuadro(Sprite.arena);
	public static final Cuadro BORDECARRETERA = new Cuadro(Sprite.bordeCarretera);
	public static final Cuadro LINEACARRETERA = new Cuadro(Sprite.lineaCarretera);
	public static final Cuadro ESQUINACARRETERA = new Cuadro(Sprite.esquinaCarretera);
	public static final Cuadro PARED = new Cuadro(Sprite.pared);
	public static final Cuadro FINPARED = new Cuadro(Sprite.finPared);
	public static final Cuadro PAREDPUERTAARENA = new Cuadro(Sprite.paredPuertaArena);
	public static final Cuadro PUERTASI = new Cuadro(Sprite.puertaSI);
	public static final Cuadro PUERTAI = new Cuadro(Sprite.puertaI);
	public static final Cuadro PUERTAMAR = new Cuadro(Sprite.puertaMAR);
	public static final Cuadro PUERTAMAB = new Cuadro(Sprite.puertaMAB);
	public static final Cuadro OXIDO = new Cuadro(Sprite.oxido);
	public static final Cuadro VACIO = new Cuadro(Sprite.vacio);
	// fin de la coleccion de cuadros

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-CONSTRUCTOR
	// DE CUADRO
	public Cuadro(Sprite sprite) {
		this.sprite = sprite;
	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// MOSTRAR
	public void mostrar(int x, int y, Pantalla pantalla) {
		pantalla.mostrarCuadro(x << 5, y << 5, this);
	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// SOLIDO
	public boolean solido() {
		return false;
	}

}
