package mapa;

import java.util.Random;

public class mapaGenerado extends Mapa {

	private static final Random aleatorio = new Random();

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-CONSTRUCTOR DE MAPA
	// GENERADO
	public mapaGenerado(int ancho, int alto) {
		super(ancho, alto);
	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO GENERAR MAPA
	protected void generarMapa() {
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				cuadros[y + x * ancho] = aleatorio.nextInt(12);
			}
		}
	}

}
