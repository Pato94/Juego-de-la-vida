
public class Vivo extends Estado{
	public boolean estaVivo(){
		return true;
	}
	public char mostrarCaracter(){
		return '*';
	}
	public Estado estadoOpuesto(){
		return Estado.Muerto;
	}
}
