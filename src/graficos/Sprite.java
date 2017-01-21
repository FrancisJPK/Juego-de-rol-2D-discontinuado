package graficos;

public final class Sprite {
	private final int lado;

	private int x;
	private int y;

	public int[] pixeles;
	private HojaSprites hoja;

	// coleccion de sprites
	public static final Sprite asfalto = new Sprite(32, 0, 0, HojaSprites.desierto);
	public static final Sprite arena = new Sprite(32, 1, 0, HojaSprites.desierto);
	public static final Sprite bordeCarretera = new Sprite(32, 2, 0, HojaSprites.desierto);
	public static final Sprite lineaCarretera = new Sprite(32, 3, 0, HojaSprites.desierto);
	public static final Sprite esquinaCarretera = new Sprite(32, 4, 0, HojaSprites.desierto);
	public static final Sprite pared = new Sprite(32, 5, 0, HojaSprites.desierto);
	public static final Sprite finPared = new Sprite(32, 6, 0, HojaSprites.desierto);
	public static final Sprite paredPuertaArena = new Sprite(32, 6, 1, HojaSprites.desierto);
	public static final Sprite puertaSI = new Sprite(32, 7, 0, HojaSprites.desierto);
	public static final Sprite puertaI = new Sprite(32, 7, 1, HojaSprites.desierto);
	public static final Sprite puertaMAR = new Sprite(32, 8, 0, HojaSprites.desierto);
	public static final Sprite puertaMAB = new Sprite(32, 8, 1, HojaSprites.desierto);
	public static final Sprite oxido = new Sprite(32, 9, 0, HojaSprites.desierto);
	public static final Sprite vacio = new Sprite(32, 0x1c3c3c);
	// fin de la coleccion

	// ||-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|METODO
	// OBTENER LADO
	public int obtenerLado() {
		return lado;
	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-CONSTRUCTOR
	// DE SPRITE
	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja) {

		this.lado = lado;
		pixeles = new int[lado * lado];

		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;

		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenerAncho()];
			}
		}
	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-CONSTRUCTOR
	// DE SPRITE ALTERNATIVO
	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}

	}

}
