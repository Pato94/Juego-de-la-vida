import java.util.ArrayList;

public class Escenario {
	ArrayList<Bloque> bloques;
	int generacion = 0;
	public void cambiarBloqueAVivo(int ancho, int alto){
		for (Bloque bloque : bloques) {
			if (bloque.esIgual(ancho, alto) && !bloque.estado.estaVivo()) {
				bloque.cambiarEstado();
			}
		}
	}
	public void cambiarBloque(int ancho, int alto){
		for (Bloque bloque : bloques) {
			if (bloque.esIgual(ancho, alto)) {
				bloque.cambiarEstado();
			}
		}
	}
	public ArrayList<Bloque> obtenerVecinos(int horizontal, int vertical) {
		ArrayList<Bloque> listaAux = new ArrayList<Bloque>();
		for (Bloque bloque : bloques) {
			if (bloque.esVecino(horizontal, vertical)) {
				listaAux.add(bloque);
			}
		}
		return listaAux;
	}

	public void mostrarPosicionesVecinos(int horizontal, int vertical) {
		System.out.println("Mostrando vecinos para: (" + horizontal + ", "
				+ vertical + ")");
		ArrayList<Bloque> listaAux = obtenerVecinos(horizontal, vertical);
		for (Bloque bloque : listaAux) {
			System.out.println("(" + bloque.posHorizontal + ", "
					+ bloque.posVertical + ")");
		}
	}

	public void dibujarPatron(int id, int ancho, int alto) {
		switch (id) {
		case 1: {// Patron Bloque
			if (comprobarEspacio(ancho, alto, 1, 1)) {
				cambiarBloqueAVivo(ancho, alto);
				cambiarBloqueAVivo(ancho+1, alto);
				cambiarBloqueAVivo(ancho, alto+1);
				cambiarBloqueAVivo(ancho+1, alto+1);
			}
			break;
		}
		case 2: {// Patron Barco
			if (comprobarEspacio(ancho, alto, 2, 2)) {
				cambiarBloqueAVivo(ancho, alto);
				cambiarBloqueAVivo(ancho+1, alto);
				cambiarBloqueAVivo(ancho, alto+1);
				cambiarBloqueAVivo(ancho+1, alto+2);
				cambiarBloqueAVivo(ancho+2, alto+1);
			}
			break;
		}
		case 3: {// Patron Parpadeador
			if (comprobarEspacio(ancho, alto, 2, 0)) {
				cambiarBloqueAVivo(ancho, alto);
				cambiarBloqueAVivo(ancho+1, alto);
				cambiarBloqueAVivo(ancho+2, alto);
			}
			break;
		}
		case 4: {// Patron Sapo
			if (comprobarEspacio(ancho, alto, 3, 1)) {
				cambiarBloqueAVivo(ancho+1, alto);
				cambiarBloqueAVivo(ancho+2, alto);
				cambiarBloqueAVivo(ancho+3, alto);
				cambiarBloqueAVivo(ancho, alto+1);
				cambiarBloqueAVivo(ancho+1, alto+1);
				cambiarBloqueAVivo(ancho+2, alto+1);
			}
			break;
		}
		case 5: {// Patron Planeador
			if (comprobarEspacio(ancho, alto, 2, 2)) {
				cambiarBloqueAVivo(ancho, alto);
				cambiarBloqueAVivo(ancho+1, alto);
				cambiarBloqueAVivo(ancho+2, alto);
				cambiarBloqueAVivo(ancho, alto+1);
				cambiarBloqueAVivo(ancho+1, alto+2);
			}
			break;
		}
		case 6: {// Patron Nave Ligera
			if (comprobarEspacio(ancho, alto, 4, 3)) {
				cambiarBloqueAVivo(ancho+1, alto);
				cambiarBloqueAVivo(ancho+4, alto);
				cambiarBloqueAVivo(ancho, alto+1);
				cambiarBloqueAVivo(ancho, alto+2);
				cambiarBloqueAVivo(ancho, alto+3);
				cambiarBloqueAVivo(ancho+1, alto+3);
				cambiarBloqueAVivo(ancho+2, alto+3);
				cambiarBloqueAVivo(ancho+3, alto+3);
				cambiarBloqueAVivo(ancho+4, alto+2);
			}
			break;
		}

		}
	}

	public boolean comprobarEspacio(int ancho, int alto, int incAncho,
			int incAlto) {
		boolean flag = true;
		for (Bloque bloque : bloques) {
			if (bloque.esIgual(ancho + incAncho, alto + incAlto))
				flag = false;
		}
		if (flag)
			System.out.println("No hay espacio suficiente para el patron");
		return !flag;
	}

	public void mostrarTablero() {
		int auxiliar = 0, flag = 0;
		System.out.println("Mostrando la generación : " + generacion);
		for (Bloque bloque : bloques) {
			if (bloque.posHorizontal == auxiliar && flag != 0) {
				auxiliar = bloque.posHorizontal;
				System.out.print("|\n");
			}
			System.out.print("|" + bloque.estado.mostrarCaracter());
			// if (bloque.estado.estaVivo()){
			// System.out.print("|*");
			// } else System.out.print("| ");
			flag = 1;
		}
		System.out.print("|\n");
	}

	public void crearLista(int horizontal, int vertical) {
		bloques = new ArrayList<Bloque>();
		for (int i = 0; i < vertical; i++) {
			for (int j = 0; j < horizontal; j++) {
				Bloque nuevo = new Bloque();
				nuevo.estado = Estado.Muerto;
				nuevo.posHorizontal = j;
				nuevo.posVertical = i;
				bloques.add(nuevo);
			}
		}
	}

	public int contarBloquesVivos(ArrayList<Bloque> lista) {
		int cantidad = 0;
		for (Bloque bloque : lista) {
			if (bloque.estado.estaVivo())
				cantidad++;
		}
		return cantidad;
	}

	public void asignarProximoEstado(Bloque bloque) {
		ArrayList<Bloque> vecinos = obtenerVecinos(bloque.posHorizontal,
				bloque.posVertical);
		int cantVivos = contarBloquesVivos(vecinos);
		if (cantVivos == 3)
			bloque.proximoEstado = Estado.Vivo;
		else if (cantVivos == 2 && bloque.estado.estaVivo())
			bloque.proximoEstado = Estado.Vivo;
		else
			bloque.proximoEstado = Estado.Muerto;
	}

	public void avanzar() {
		for (Bloque bloque : bloques) {
			asignarProximoEstado(bloque);
		}
		for (Bloque bloque : bloques) {
			bloque.actualizarEstado();
		}
		generacion++;
	}
}