import java.util.Scanner;

import javax.swing.Timer;


public class Menu {
	public char car;
	public void manejarMenu(Escenario vida, Timer reloj) {

		switch (car) {
		case 'c': {
			boolean flag = true;
			int ancho = obtenerNumero("Ingrese la posicion horizontal del casillero que desea cambiar");
			int alto = obtenerNumero("Ingrese la posicion vertical del casillero que desea cambiar");
			flag = !vida.comprobarEspacio(ancho, alto, 0, 0);
			if (flag) {
				System.out.println("La posicion ingresada no es valida");
			} else {
				System.out.println("El estado fue cambiado");
				vida.cambiarBloque(ancho, alto);
			}
			vida.mostrarTablero();
			break;
		}
		case 'x': {
			System.out.println("Saliendo del programa");
			break;
		}
		case 'a': {
			vida.avanzar();
			vida.mostrarTablero();
			break;
		}
		case 's': {
			if (reloj.isRunning())
				reloj.stop();
			else {
				reloj.start();
				System.out
						.println("Se ha iniciado el reloj, para detenerlo ingrese 's' nuevamente");
			}
			break;
		}
		case 'r': {
			reloj.stop();
			car = 'x';
			Juego.main(null);
			break;
		}
		case 'p': {
			int idPatron = obtenerNumero("Ingrese el id del patron, para ver las opciones ingrese 0");
			int alto = 0, ancho = 0;
			if (idPatron > 0 && idPatron < 7) {
				ancho = obtenerNumero("Ingrese la coordenada horizontal del patron");
				alto = obtenerNumero("Ingrese la coordenada vertical del patron");
			} else if (idPatron > 6 || idPatron < 0) {
				System.out.println("Ha ingresado un numero no valido");
				break;
			}
			switch (idPatron) {
			case 0: {
				System.out.println("1- Bloque\n" + "2- Barco\n"
						+ "3- Parpadeador\n" + "4- Sapo\n" + "5- Planeador\n"
						+ "6- Nave Ligera");
				break;
			}
			default: {
				vida.dibujarPatron(idPatron, ancho, alto);
				vida.mostrarTablero();
				break;
			}
			}
			break;
		}
		case 'h': {
			System.out.println("'a'- Avanzar generacion\n"
					+ "'p'- Dibujar patron\n" + "'c'- Cambiar estado celula\n"
					+ "'s'- Start/Stop (Reloj)\n" + "'r'- Reiniciar\n"
					+ "'h'- Ayuda\n" + "'x'- Salir");
		}
		}
	}
	public static int obtenerNumero(String cadena) {
		int retorno = 0;
		Scanner entrada = new Scanner(System.in);
		String error = "";
		do {
			System.out.println(error + cadena);
			error = "";
			try {
				retorno = Integer.parseInt(entrada.nextLine());
			} catch (NumberFormatException e) {
				error = "Hubo un problema, por favor reingrese el numero. ";
			}
		} while (!error.isEmpty());
		return retorno;
	}
}
