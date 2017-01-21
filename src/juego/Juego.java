
package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import control.Teclado;
import graficos.Pantalla;
import mapa.Mapa;
import mapa.mapaCargado;

public class Juego extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int ANCHO = 1000;
	private static final int ALTO = 800;
	private static final String NOMBRE = "After-That";

	private static String contador_fps = "";
	private static String contador_aps = "";

	private static int aps = 0;
	private static int fps = 0;

	private static int x = 0;
	private static int y = 0;

	private static volatile boolean Corriendo = false;

	private static JFrame Ventana;
	private static Thread thread;
	private static Teclado teclado;
	private static Pantalla pantalla;

	private static Mapa mapa;

	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);

	private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

	private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono/icono.png"));

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-CONSTRUCTOR
	// DE JUEGO
	private Juego() {

		setPreferredSize(new Dimension(ANCHO, ALTO));

		pantalla = new Pantalla(ALTO, ANCHO);

		// mapa = new mapaGenerado(128, 128);
		mapa = new mapaCargado("/mapas/cruce.png");
		Ventana = new JFrame(NOMBRE);

		teclado = new Teclado();
		addKeyListener(teclado);

		Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ventana.setResizable(false);
		Ventana.setIconImage(icono.getImage());
		Ventana.setUndecorated(true);
		Ventana.setVisible(true);
		Ventana.setLayout(new BorderLayout());
		Ventana.add(this, BorderLayout.CENTER);
		Ventana.pack();
		Ventana.setLocationRelativeTo(null);

	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// INICIAR
	private synchronized void iniciar() {

		Corriendo = true;
		thread = new Thread(this, "Graficos");
		thread.start();
	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// DETENER
	private synchronized void detener() {
		Corriendo = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// ACTUALIZAR
	public void Actualizar() {

		teclado.Actualizar();
		if (teclado.arriba) {
			y--;
		}
		if (teclado.abajo) {
			y++;
		}
		if (teclado.izquierda) {
			x--;
		}
		if (teclado.derecha) {
			x++;
		}
		if (teclado.salir) {
			System.exit(0);
		}

		aps++;
	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// MOSTRAR
	public void Mostrar() {

		BufferStrategy estrategia = getBufferStrategy();

		if (estrategia == null) {
			createBufferStrategy(3);
			return;
		}

		// pantalla.limpiar(); este metodo ya no es nesesario porque usamos
		// estrategias buffer
		mapa.mostrar(x, y, pantalla);

		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

		// metodo que podria ser lento en algunas PCs
		// for(int i = 0; i < pixeles.length; i++){
		// pixeles[i] = pantalla.pixeles[i];
		// }

		Graphics g = estrategia.getDrawGraphics();

		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(ANCHO / 2, ALTO / 2, 32, 32);
		g.drawString(contador_aps, 10, 20);
		g.drawString(contador_fps, 10, 35);
		g.dispose();

		estrategia.show();

		fps++;
	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// RUN
	public void run() {

		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;

		requestFocus();

		while (Corriendo) {
			final long inicioBucle = System.nanoTime();

			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;

			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) {
				Actualizar();
				delta--;
			}

			Mostrar();

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				contador_aps = "APS: " + aps;
				contador_fps = "FPS: " + fps;

				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}

	// -|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-METODO
	// MAIN
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.iniciar();
	}

}
