package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
	private final int ancho;
	private final int alto;
	public final int pixeles[];

	// coleccion de hojas de sprite
	public static HojaSprites desierto = new HojaSprites("/texturas/desierto.png", 320, 320);
	// fin de la coleccion

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// OBTENER ANCHO
	public int obtenerAncho() {
		return ancho;
	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-CONSTRUCTOR
	// DE HOJASPRITES
	public HojaSprites(final String ruta, final int ancho, final int alto) {
		this.alto = alto;
		this.ancho = ancho;
		pixeles = new int[ancho * alto];
		BufferedImage imagen;

		try {
			imagen = ImageIO.read(this.getClass().getResource(ruta));
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
