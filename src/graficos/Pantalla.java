package graficos;

import mapa.cuadro.Cuadro;

public final class Pantalla {

	private final int alto;
	private final int ancho;

	private int diferenciaX;
	private int diferenciaY;

	public void estableceDiferencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}

	public int obtenerAlto() {
		return alto;
	}

	public int obtenerAncho() {
		return ancho;
	}

	public final int[] pixeles;

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-CONSTRUCTOR
	// DE PANTALLA
	public Pantalla(final int alto, final int ancho) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[alto * ancho];
	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// LIMPIAR
	public void limpiar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;
		}
	}

	// |-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// MOSTRAR CUADRO
	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {

		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		for (int y = 0; y < cuadro.sprite.obtenerLado(); y++) {
			int posY = y + compensacionY;

			for (int x = 0; x < cuadro.sprite.obtenerLado(); x++) {
				int posX = x + compensacionX;

				if (posX < -cuadro.sprite.obtenerLado() || posX >= ancho || posY < 0 || posY >= alto) {

					break;
				}

				if (posX < 0) {
					posX = 0;
				}
				pixeles[posX + posY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.obtenerLado()];
			}
		}
	}

}
