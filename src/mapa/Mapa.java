package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {

	protected int alto;
	protected int ancho;

	protected int[] cuadros;
	protected Cuadro[] cuadrosCatalogo;

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-CONSTRUCTOR
	// DE MAPA ALEATORIO
	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		cuadros = new int[ancho * alto];
		generarMapa();
	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-CONSTRUCTOR
	// DE MAPA PREDEFINIDO
	public Mapa(String ruta) {
		cargarMapa(ruta);
		generarMapa();

	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// CARGAR MAPA
	// modificamos el modificador de este metodo para poder extenderlo
	protected void cargarMapa(String ruta) {

	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// GENERAR MAPA
	protected void generarMapa() {

	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// ACTUALIZAR
	public void actualizar() {

	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// MOSTRAR
	public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla) {
		int oeste = compensacionX >> 5;
		int este = (compensacionX + pantalla.obtenerAncho() + Cuadro.LADO) >> 5;
		int norte = compensacionY >> 5;
		int sur = (compensacionY + pantalla.obtenerAlto() + Cuadro.LADO) >> 5;

		pantalla.estableceDiferencia(compensacionX, compensacionY);

		for (int y = norte; y < sur; y++) {
			for (int x = oeste; x < este; x++) {
				// antiguo metodo para generar mapa al azar
				// obtenerCuadro(x, y).mostrar(x, y, pantalla);

				// nuevo metodo para cargar un mapa
				if (x < 0 || y < 0 || x >= ancho || y >= ancho) {
					Cuadro.VACIO.mostrar(x, y, pantalla);
					;
				} else {
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO OBRENET CUADRO

	public Cuadro obtenerCuadro(final int x, final int y) {

		if (x < 0 || y < 0 || x >= ancho || y >= ancho) {
			return Cuadro.VACIO;
		}
		switch (cuadros[x + y * ancho]) {
		case 0:
			return Cuadro.ASFALTO;
		case 1:
			return Cuadro.ARENA;
		case 2:
			return Cuadro.BORDECARRETERA;
		case 3:
			return Cuadro.ESQUINACARRETERA;
		case 4:
			return Cuadro.FINPARED;
		case 5:
			return Cuadro.LINEACARRETERA;
		case 6:
			return Cuadro.OXIDO;
		case 7:
			return Cuadro.PARED;
		case 8:
			return Cuadro.PAREDPUERTAARENA;
		case 9:
			return Cuadro.PUERTAI;
		case 10:
			return Cuadro.PUERTAMAB;
		case 11:
			return Cuadro.PUERTAMAR;
		case 12:
			return Cuadro.PUERTASI;
		default:
			return Cuadro.VACIO;

		}
	}

}
