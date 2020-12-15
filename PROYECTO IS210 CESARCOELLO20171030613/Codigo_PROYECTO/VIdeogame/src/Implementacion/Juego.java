package Implementacion;



import java.util.ArrayList;
import java.util.HashMap;

import Clases.Fondo;
import Clases.Item;
//import Clases.Jugador;
import Clases.JugadorAnimado;
import Clases.JugadorAnimado2;
import Clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Juego extends Application{
	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	private JugadorAnimado jugadorAnimado;
	private JugadorAnimado2 jugadorAnimado2;
	private Fondo fondo;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean izquierda;
	public static boolean derecha;
	public static boolean arriba2;
	public static boolean abajo2;
	public static boolean izquierda2;
	public static boolean derecha2;
	public static HashMap<String, Image> imagenes;
	
	private Item item;


	//private Tile tile;
	private ArrayList<Tile> tiles;
	
	private int tilemap[][] = {
			{2,3,2,2,2,2,4,2,2,2},
			{2,2,2,3,2,4,4,2,2,2},
			{1,1,1,2,2,2,2,2,3,3},
			{6,4,1,2,2,2,2,2,4,4},
			{4,4,4,4,4,4,4,2,6,4},
			{7,8,9,1,3,3,4,2,1,1},
			{1,1,1,1,2,2,4,2,2,2},
			{2,2,2,2,2,2,4,3,2,2},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}
	};

	
	public static void main(String[] args){
		launch(args);			//launch internamente ejecuta el metodo start
}

	@Override
	public void start(Stage ventana) throws Exception{
		inicializarComponentes();
		gestionEventos();
		ventana.setScene(escena);
		ventana.setTitle("Conteo Final");
		ventana.show();
		cicloJuego();
	}
	
	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();	//para ver la cantidad de FpS, retorna tiempo en nanosegundos
		AnimationTimer animationTimer = new AnimationTimer() {
			
			//Este metodo se ejecuta aproximadamente 60 veces por segundo 60FPS
			@Override
			public void handle(long tiempoActual) {
				double t = (tiempoActual - tiempoInicial) / 1000000000.0;
				System.out.println(t);
				acualizarEstado(t);
				pintar();
			}	//se usa para pintar -
			
		};
			animationTimer.start(); //empieza el ciclo de juego
	}
	
	public void acualizarEstado(double t) {
		//jugador.mover()
		jugadorAnimado.verificarColisionesItem(item);
		jugadorAnimado2.verificarColisionesItem(item);
		jugadorAnimado.calcularFrame(t);
		jugadorAnimado.mover();
		jugadorAnimado2.calcularFrame(t);
		jugadorAnimado2.mover();


		//fondo.mover();
	}
	
	
	public void inicializarComponentes(){
		imagenes = new HashMap<String, Image>();
		cargarImagenes();
		//jugador = new Jugador(20, 40, "Azul", 5, 0); //parametros, x, y, vidas, nombre imagen
		jugadorAnimado = new JugadorAnimado(20, 40, "mister", 2, 0, "descanso");
		jugadorAnimado2 = new JugadorAnimado2(200, 200, "mister", 2, 0, "descanso2");
		fondo =  new Fondo(0, 0, 0, 0, "Fondo1","Fondo2", 5);
		inicializarTiles();
		item = new Item(630, 0, "item", 0);
		
		//tile = new Tile(0,0,"Tilemap",0,0,70,70,70);
		root = new Group();
		escena = new Scene(root, 700, 500); // tamaño de la ventana
		lienzo = new Canvas(700, 500);
		root.getChildren().add(lienzo);	
		graficos = lienzo.getGraphicsContext2D();
	}
	
	public void inicializarTiles() {
		tiles = new ArrayList<Tile>();
		for(int i=0;i<tilemap.length;i++) {
			for(int j=0;j<tilemap[i].length;j++) {
				if(tilemap[i][j] != 0) {
					this.tiles.add(new Tile(tilemap[i][j],j*70,i*70,"Tilemap",0,70,70));
				}
				
			}
		}
	}
	
	public void cargarImagenes() {
		imagenes.put("Azul", new Image("Azul.png"));//para agregar imagenes
		imagenes.put("Morado", new Image("Morado.png"));
		imagenes.put("Fondo1", new Image("Fondo1.png"));
		imagenes.put("Fondo2", new Image("Fondo2.png"));
		imagenes.put("Tilemap", new Image("tilemap.png"));
		imagenes.put("mister", new Image("mister.png"));
		imagenes.put("item", new Image("item.png"));
	}
	
	public void pintar() {
		//graficos.fillRect(0,0,100,100);
		fondo.pintar(graficos);
		//tile.pintar(graficos);
		for(int i=0;i<tiles.size();i++)
			tiles.get(i).pintar(graficos);
		//jugador.pintar(graficos);
		jugadorAnimado.pintar(graficos);
		jugadorAnimado2.pintar(graficos);
		item.pintar(graficos);
		
		
		
		/*graficos.drawImage(imagenes.get("Tilemap"),
				0, 		//coorX dentro de la imagen
				70,		//coorY dentro de la imagen
				70, 	//ancho
			    70,		//alto
			    0,		//coorX pintar
			    0,		//coorY pintar
			    70,		//ancho pintar redimensionar
			    70		//alto pintar redimensionar
				);*/
		
	}
	
	public void gestionEventos() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			//El metodo Handle se ejecuta cada vez que se presiona una tecla.
			@Override
			public void handle(KeyEvent evento) {
				
				switch(evento.getCode().toString()) {
					case "RIGHT":
						derecha = true;
						jugadorAnimado.setDireccion(1);
						jugadorAnimado.setAnimacionActual("caminar");
					break;
					case "LEFT":
						izquierda = true;
						jugadorAnimado.setDireccion(-1);
						jugadorAnimado.setAnimacionActual("caminar");
					break;
					case "UP":
						arriba = true;
						jugadorAnimado.setAnimacionActual("caminar");
					break;
					case "DOWN":
						abajo = true;
						jugadorAnimado.setAnimacionActual("caminar");
					break;
					case "SPACE":
						jugadorAnimado.setAnimacionActual("matar");
						break;
					case "D":
						derecha2 = true;
						jugadorAnimado2.setDireccion(1);
						jugadorAnimado2.setAnimacionActual("caminar2");
					break;
					case "A":
						izquierda2 = true;
						jugadorAnimado2.setDireccion(-1);
						jugadorAnimado2.setAnimacionActual("caminar2");
					break;
					case "W":
						arriba2 = true;
						jugadorAnimado2.setAnimacionActual("caminar2");
					break;
					case "S":
						abajo2 = true;
						jugadorAnimado2.setAnimacionActual("caminar2");
					break;
					case "Z":
						jugadorAnimado2.setAnimacionActual("matar2");
						break;
					
				
				}
			}	
		});
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			//este metodo se ejecuta cada que se suelta una tecla
			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()) {
				case "RIGHT":
					derecha = false;
					jugadorAnimado.setAnimacionActual("descanso");
				break;
				case "LEFT":
					izquierda = false;
					jugadorAnimado.setAnimacionActual("descanso");
				break;
				case "UP":
					arriba = false;
					jugadorAnimado.setAnimacionActual("descanso");
				break;
				case "DOWN":
					abajo = false;
					jugadorAnimado.setAnimacionActual("descanso");
				break;
				case "SPACE":
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "D":
					derecha2 = false;
					jugadorAnimado2.setAnimacionActual("descanso2");
				break;
				case "A":
					izquierda2 = false;
					jugadorAnimado2.setAnimacionActual("descanso2");	
				break;
				case "W":
					arriba2 = false;
					jugadorAnimado2.setAnimacionActual("descanso2");
				break;
				case "S":
					abajo2 = false;
					jugadorAnimado2.setAnimacionActual("descanso2");
				break;
				case "Z":
					jugadorAnimado2.setAnimacionActual("descanso2");
					break;
			}
			}
			
		});
	}

}


























