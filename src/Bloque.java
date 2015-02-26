
public class Bloque {
	Estado estado;
	Estado proximoEstado;
	int posHorizontal, posVertical;
	public boolean esVecino(int horizontal, int vertical){
		return (Math.abs(posHorizontal-horizontal)<=1 && Math.abs(posVertical-vertical)<=1)&&(horizontal!=posHorizontal||vertical!=posVertical);
	}
	public void actualizarEstado(){
		estado = proximoEstado;
	}
	public boolean esIgual(int horizontal, int vertical){
		return (posHorizontal==horizontal && posVertical==vertical);
	}
	public void cambiarEstado(){
		estado = estado.estadoOpuesto();
	}
}
