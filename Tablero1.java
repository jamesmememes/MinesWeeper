public class Tablero1 {
private int filas; 
private int columnas; 
private int [][]tablero; 
private int [][]tableroJuego; 
private int cantidadDeMinas;
 
private int MINA = -1; 
private char TAPADA = 'X'; 

public Tablero1 () {
 tablero = new int [10] [10]; 
}

public void cargarDimensionesTablero (int filas,int  columnas) {
 tablero = new int [filas] [columnas]; 
}

public void cargarDimensionesTableroGame (int filas, int columnas) {
 tableroJuego = new int [filas] [columnas]; 
 
}

public void loadTableroJuego (int filas, int columnas) {
 for (int i = 0; i < getFilas(); ++i) {
     for (int h = 0; h < getColumnas(); ++h) {
       tableroJuego [i] [h] = TAPADA;   
        
     }   
 }
}

public void setFilas (int filas) {
 this.filas = filas; 

}

public void setColumnas (int columnas) {
 this.columnas = columnas; 

}

public void calculateMines () {
 this.cantidadDeMinas = (int) (filas*columnas* 0.1);  
}

public int getDimensiones () {
 return filas*columnas; 
}

public int getFilas () {
 return filas; 
}

public int getColumnas () {
 return columnas; 
}

public boolean esPosicionValida (int fila, int columna) {
  boolean existePos = true; 
  if ( fila >filas || columna >columnas || fila < filas  || columna < columnas ) { 
    existePos = false; 
  }
  return existePos; 
}

public boolean theresMine (int fila, int columna) {
 boolean hayMina = false; 
 if (tablero [fila] [columna] == MINA) {
  hayMina = true;    
 }
 return hayMina; 
}

public boolean hayMinaVsCasassola (int fila, int columna) {
 return tablero [fila] [columna] == MINA; 
}

public void setMines () {
 for (int i = 0; i < cantidadDeMinas; ++i) {
  int fila =  (int) (Math.random() * getFilas()); 
  int columna = (int) (Math.random() * getColumnas()); 
  while (theresMine (fila, columna)) {  
    fila = (int) (Math.random() * getFilas()); 
    columna = (int) (Math.random() * getColumnas());
  }
  
  tablero[fila] [columna] = MINA;  
 } 
} 

public void setValuesAroundMine () {
 for (int i = 0; i < getFilas(); ++i) {
   for (int h = 0; h < getColumnas(); ++h) {
    if (tablero [i] [h] == MINA) {
     addOnesAroud (i, h); 
    }  
   }   
 }
}

public void addOnesAroud (int filas, int columnas) {
 for (int f = filas -1; f < filas +1; ++f) {
    for (int c = columnas -1; c < columnas +1; ++c) {
      if (esPosicionValida (f, c) && !theresMine(f, c) && (!( f== filas || c == columnas))) {
       tablero [f] [c] = tablero [f] [c] +1;  
      } 
    }   
 }
}

public void sumarAlrededorMina () {
 for (int f = 0; f < filas; ++f) {
    for (int c = 0; c < columnas; ++c) {
     if (theresMine (f,c)) {
      int filaTemporal = f -1;
      int columnaTemporal = c; 
      if (esPosicionValida(filaTemporal, columnaTemporal)) {++tablero [filaTemporal] [columnaTemporal]; }
      filaTemporal = f -1; 
      columnaTemporal = c + 1; 
      if (esPosicionValida(filaTemporal, columnaTemporal)) {++tablero [filaTemporal] [columnaTemporal]; }
      filaTemporal = f; 
      columnaTemporal = c +1; 
      if (esPosicionValida(filaTemporal, columnaTemporal)) {++tablero [filaTemporal] [columnaTemporal]; }
      filaTemporal = f + 1; 
      columnaTemporal = c + 1; 
      if (esPosicionValida(filaTemporal, columnaTemporal)) {++tablero [filaTemporal] [columnaTemporal]; }
      filaTemporal = f +1; 
      columnaTemporal = c; 
      if (esPosicionValida(filaTemporal, columnaTemporal)) {++tablero [filaTemporal] [columnaTemporal]; }
      filaTemporal = f + 1; 
      columnaTemporal = c - 1; 
      if (esPosicionValida(filaTemporal, columnaTemporal)) {++tablero [filaTemporal] [columnaTemporal]; }
      filaTemporal = f; 
      columnaTemporal = c -1; 
      if (esPosicionValida(filaTemporal, columnaTemporal)) {++tablero [filaTemporal] [columnaTemporal]; }
      filaTemporal = f - 1; 
      columnaTemporal = c -1; 
      if (esPosicionValida(filaTemporal, columnaTemporal)) {++tablero [filaTemporal] [columnaTemporal]; }
     }
    }    
 }
}

public void initValoresTablero (int filas, int columnas) {
 tablero = new int [filas] [columnas]; 
 setMines(); 
 setValuesAroundMine(); 
}

public String toString () {
 String gameBoard = "Minas :"+cantidadDeMinas+"\n";  
 for (int i= 0; i < getFilas(); ++i) {
   for (int h = 0; h < getColumnas(); ++h) {
    int vecPosValue = tablero [i] [h]; 
    gameBoard+= vecPosValue + "    "; 
   }    
  gameBoard+="\n"; 
 }   
 return gameBoard;  
}
}
