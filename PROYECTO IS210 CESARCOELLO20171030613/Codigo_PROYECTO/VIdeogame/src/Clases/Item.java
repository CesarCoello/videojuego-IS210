package Clases;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item extends ObjetoJuego {

	public Item(int x, int y, String nombreImagen, int velocidad) {
		super(x, y, nombreImagen, velocidad);	
		this.ancho = (int)Juego.imagenes.get("item").getWidth();
		this.alto = (int)Juego.imagenes.get("item").getHeight();
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get("item"), this.x, this.y);
		//graficos.setStroke(Color.RED);
		//graficos.strokeRect(x, y, ancho, alto);
		
	}

	@Override
	public void mover() {
		
	}
	

	public Rectangle obtenerRectangulo() {
		return new Rectangle(x, y, ancho, alto);
	}
	
}
