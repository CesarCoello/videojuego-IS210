package Clases;

import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado2 extends ObjetoJuego {
	private int vidas;
	private HashMap<String, Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion = 1;

	
	public int getDireccion() {
		return direccion;
	}



	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}



	public JugadorAnimado2(int x2, int y2, String nombreImagen, int velocidad, int vidas, String animacionActual) {
		super(x2, y2, nombreImagen, velocidad);
		this.vidas = vidas;
		this.animacionActual = animacionActual;
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
	}

	

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public void inicializarAnimaciones() {
		
		Rectangle coordenadasCaminar2[]= {
				new Rectangle(70,280,70,70),	
				new Rectangle(140,280,70,70)
		};
		Animacion animacionCaminar2 = new Animacion(0.2,coordenadasCaminar2);
		animaciones.put("caminar2", animacionCaminar2);
		
		
		Rectangle coordenadasDescanso2[]= {
				new Rectangle(140,140,70,70),		
				new Rectangle(70,140,70,70)
		};
		
		Animacion animacionDescanso2 = new Animacion(0.2,coordenadasDescanso2);
		animaciones.put("descanso2", animacionDescanso2);
		
		Rectangle coordenadasMatar2[]= {
				new Rectangle(70,420,70,70),		
		};
		
		Animacion animacionMatar2 = new Animacion(0.2,coordenadasMatar2);
		animaciones.put("matar2", animacionMatar2);
		

		Rectangle coordenadasExplotar2[]= {
				new Rectangle(70,543,90,70),		
		};
		
		Animacion animacionExplotar2 = new Animacion(0.2,coordenadasExplotar2);
		animaciones.put("explotar2", animacionExplotar2);
			
		
	}
	
	
	
	public void calcularFrame(double t) {
		Rectangle coordenadas = animaciones.get(animacionActual).calcularFrameActual(t);
		this.xImagen = (int)coordenadas.getX();
		this.yImagen = (int)coordenadas.getY();
		this.anchoImagen = (int)coordenadas.getHeight(); 
		this.altoImagen = (int)coordenadas.getWidth(); 
	}
	

	public Rectangle obtenerRectangulo() {
		return new Rectangle(x, y, (direccion*anchoImagen) - 10, altoImagen);
	}
	
	
	//se ejecuta por cada iteracion del ciclo de juego
	@Override //se esta sobreescribiendo de ObjetoJuego(PADRE)													// graficos del tipo Graphics context
	public void pintar(GraphicsContext graficos) {		//este metodo se encargaraa de pintar al jugador
		graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, yImagen, anchoImagen, altoImagen, x + (direccion==-1?anchoImagen:0), y, (direccion)*anchoImagen, altoImagen);
		//graficos.setStroke(Color.RED);
		//graficos.strokeRect(x, y, (direccion*anchoImagen) -10, altoImagen);
	}
	
	//se ejecuta por cada iteracion del ciclo de juego
	public void mover() {
		if(x>700)		//esta condicion para posicionar al personaje al inicio
			x=0;
		else if(x<0)		//esta condicion para posicionar al personaje al inicio
			x=700;
		else if(y>500)		//esta condicion para posicionar al personaje al inicio
			y=0;
		else if(y<0)		//esta condicion para posicionar al personaje al inicio
			y=500;
		
		if (Juego.derecha2) {	//mover hacia la derecha
			x+=velocidad;		//esto tambien incrementa la velocidad
		}
		

		if (Juego.izquierda2) {	
			x-=velocidad;		
		}
		

		if (Juego.arriba2) {	
			y-=velocidad;		
		}

		if (Juego.abajo2) {	
			y+=velocidad;		
		}
		
				
}
		


	public String getAnimacionActual() {
		return animacionActual;
	}



	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}
	

	public void verificarColisionesItem(Item item) {
		if(this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
			setAnimacionActual("explotar2");
	}
	}
	
}