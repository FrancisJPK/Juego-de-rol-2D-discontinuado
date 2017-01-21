package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mapa.cuadro.Cuadro;

public class mapaCargado extends Mapa {

	// array que va a guardar los valores de los pixeles del modelo del
	// mapa(15x15)
	private int[] pixeles;

	public mapaCargado(String ruta) {
		super(ruta);
	}

	// leer el mapa (imagen de 15x15) para obtener la informacion nesesaria de
	// la imagen, este metodo se extiende de la clase mapa
	protected void cargarMapa(String ruta) {
		try {
			// leemos la imagen de referencia(la de 15x15)
			BufferedImage imagen = ImageIO.read(mapaCargado.class.getResource(ruta));
			// aca inicializamos el tamaño del mapa
			ancho = imagen.getWidth();
			alto = imagen.getHeight();

			// inicializamos cuadrosCatalogo(clase mapa)
			cuadrosCatalogo = new Cuadro[ancho * alto];
			// inicializamos el array de pixeles que contendra los datos de
			// color del plano del mapa
			// (otra vez, la imagen de 15x15)
			pixeles = new int[ancho * alto];
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// extendemos este metodo de la clase mapa
	protected void generarMapa() {
		// recorremos los pixeles para traducirlos
		// en el switch se pone continue para que pase a la siguiente iteracion
		// una vez encontrado el
		// color adecuado
		// nota: el ff que se pone antes de los valores rgb(los ultimos 6
		// digitos), es el canal alfa
		// definira la opacidad del color y para que todos nuestros sprites sean
		// opacos ponemos el valor
		// maximo(ff)
		for (int i = 0; i < pixeles.length; i++) {
			switch (pixeles[i]) {
			case 0xff000000:
				cuadrosCatalogo[i] = Cuadro.ASFALTO;
				continue;
			case 0xffB5886C:
				cuadrosCatalogo[i] = Cuadro.ARENA;
				continue;
			case 0xff7E624F:
				cuadrosCatalogo[i] = Cuadro.BORDECARRETERA;
				continue;
			case 0xffD0D0D0:
				cuadrosCatalogo[i] = Cuadro.LINEACARRETERA;
				continue;
			case 0xff98735B:
				cuadrosCatalogo[i] = Cuadro.ESQUINACARRETERA;
				continue;
			case 0xff808080:
				cuadrosCatalogo[i] = Cuadro.PARED;
				continue;
			case 0xff4F1F1F:
				cuadrosCatalogo[i] = Cuadro.PUERTASI;
				continue;
			case 0xff1A0A0A:
				cuadrosCatalogo[i] = Cuadro.PUERTAI;
				continue;
			case 0xff7C2D2A:
				cuadrosCatalogo[i] = Cuadro.OXIDO;
				continue;
			case 0xff893A2E:
				cuadrosCatalogo[i] = Cuadro.PUERTAMAR;
				continue;
			case 0xff7B2B2B:
				cuadrosCatalogo[i] = Cuadro.PUERTAMAB;
				continue;
			case 0xff717171:
				cuadrosCatalogo[i] = Cuadro.FINPARED;
				continue;
			case 0xff626262:
				cuadrosCatalogo[i] = Cuadro.PAREDPUERTAARENA;
				continue;
			default:
				cuadrosCatalogo[i] = Cuadro.VACIO;
			}
		}
	}
}
