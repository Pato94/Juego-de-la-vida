import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

public class Juego {

	public static void main(String args[]) {
		int ancho, alto;
		Menu menu = new Menu();
		Escenario vida = new Escenario();
		Timer reloj = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vida.avanzar();
				vida.mostrarTablero();
			}
		});
		System.out.println("Bienvenido al juego de la vida");
		ancho = Menu.obtenerNumero("Por favor ingrese el ancho del universo deseado");
		alto = Menu.obtenerNumero("Por favor ingrese el alto del universo deseado");
		vida.crearLista(ancho, alto);
		vida.mostrarTablero();
		do {

			System.out
					.println("Por favor ingrese un comando, para ver la ayuda ingrese 'h'");
			try {
				do {
					menu.car = (char) System.in.read();
				} while ("hacxsrp".indexOf(menu.car) < 0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menu.manejarMenu(vida, reloj);
		} while (menu.car != 'x');
	}
}