package Clases;

import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado extends ObjetoJuego {
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



	public JugadorAnimado(int x, int y, String nombreImagen, int velocidad, int vidas, String animacionActual) {
		super(x, y, nombreImagen, velocidad);
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
		Rectangle coordenadasCaminar[]= {
				new Rectangle(70,210,70,70),		//x,y,anchoFRagmento, altoFragmento
				new Rectangle(140,210,70,70)
		};
		Animacion animacionCaminar = new Animacion(0.2,coordenadasCaminar);
		animaciones.put("caminar", animacionCaminar);
		
		Rectangle coordenadasDescanso[]= {
				new Rectangle(140,70,70,70),	
				new Rectangle(70,70,70,70)
		};
		
		Animacion animacionDescanso = new Animacion(0.2,coordenadasDescanso);
		animaciones.put("descanso", animacionDescanso);
	
		Rectangle coordenadasMatar[]= {
				new Rectangle(70,350,70,70),		
		};
		
		Animacion animacionMatar = new Animacion(0.2,coordenadasMatar);
		animaciones.put("matar", animacionMatar);
		
		Rectangle coordenadasExplotar[]= {
				new Rectangle(140,470,90,70),		
		};
		
		Animacion animacionExplotar = new Animacion(0.2,coordenadasExplotar);
		animaciones.put("explotar", animacionExplotar);
			
		
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
		//graficos.strokeRect(x, y, anchoImagen - 10, altoImagen);
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
		
		if (Juego.derecha) {	//mover hacia la derecha
			x+=velocidad;		//esto tambien incrementa la velocidad
		}
		

		if (Juego.izquierda) {	
			x-=velocidad;		
		}
		

		if (Juego.arriba) {	
			y-=velocidad;		
		}

		if (Juego.abajo) {	
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
		if(this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal()))
			setAnimacionActual("explotar");
	}
}
