public class Estado {
	public final static Vivo Vivo = new Vivo();
	public final static Estado Muerto = new Estado();
	public boolean estaVivo(){
		return false;
	}
	public Estado estadoOpuesto(){
		return Vivo;
	}
	public char mostrarCaracter(){
		return ' ';
	}
}
