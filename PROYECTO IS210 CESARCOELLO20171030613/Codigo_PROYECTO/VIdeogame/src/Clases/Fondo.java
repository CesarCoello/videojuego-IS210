package Clases;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Fondo extends ObjetoJuego{
	private String nombreImagen2;	//este es el fondo 2, igual se agrega al constructor
	private int x2;
	//constructor desde la clase PADRE
	
	public Fondo(int x, int y, int x2, int y2, String nombreImagen, String nombreImagen2, int velocidad) {
		super(x, y, nombreImagen, velocidad);
		this.nombreImagen2 = nombreImagen2;
		this.ancho = (int)Juego.imagenes.get("Fondo1").getWidth();
		this.alto = (int)Juego.imagenes.get("Fondo1").getHeight();
		this.x2 = this.ancho+this.x;
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen), this.x, this.y);
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen2), this.x2, this.y);
	}

	@Override
	public void mover() {
		
	
	/*	if(x <= -1*ancho) {
			x=ancho;
		}
		if(x2 <= -1*ancho) {
			x2=ancho;
		}
		
		if (Juego.derecha) {
			x-=velocidad;
			x2-=velocidad;
	}
		if (Juego.izquierda) {
			x+=velocidad;
			x2+=velocidad;
		}
		
	
		
		if (Juego.derecha2) {
			x-=velocidad;
			x2-=velocidad;
	}
		if (Juego.izquierda2) {
			x+=velocidad;
			x2+=velocidad;
		}
		
	}*/
}
}
