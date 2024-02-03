package ajedrez;
import java.util.Scanner;

public class Ajedrez {

	//Para que la columna tenga estas letras
	char[] letrasColumnas = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

	public static void main(String[] args) {
		Ajedrez ajedrez = new Ajedrez();
		ajedrez.iniciarJuego();
	}

	//Metodo para iniciar el juego 
	public void iniciarJuego() {

		//Declaricion de todas las variables
		Scanner sn = new Scanner(System.in);
		boolean movimientoValido = false;
		int filaDestino;
		int filaX;
		int columnaY;
		String partePieza = "";
		String peonN = "p";
		String torreN = "t";
		String caballoN = "c";
		String alfilN = "a";
		String reinaN = "q";
		String reyN = "k";
		String peonB = "P";
		String torreB = "T";
		String caballoB = "C";
		String alfilB = "A";
		String reinaB = "Q";
		String reyB = "K";
		String jugadorActual;
		String piezaSeleccionada;
		String fila1;
		String destinoLimpio;
		char columna1;
		char columnaDestino;
		String tablero[][] = new String[8][8];
		boolean[][] peonesPromocionados = new boolean[8][8];

		//Bucle for para hacer que las posiciones del tablero que no tienen ficha sean "-"
		System.out.println( "\tTABLERO DE AJEDREZ\n");

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = "-";
			}
		}

		//Asignaciones de las posiciones de la matriz con las piezas
		tablero[0][0] = torreN;
		tablero[0][1] = caballoN;
		tablero[0][2] = alfilN;
		tablero[0][3] = reinaN;
		tablero[0][4] = reyN;
		tablero[0][5] = alfilN;
		tablero[0][6] = caballoN;
		tablero[0][7] = torreN;

		for (int i = 0; i < tablero[1].length; i++) {
			tablero[1][i] = peonN;
		}

		tablero[7][0] = torreB;
		tablero[7][1] = caballoB;
		tablero[7][2] = alfilB;
		tablero[7][3] = reinaB;
		tablero[7][4] = reyB;
		tablero[7][5] = alfilB;
		tablero[7][6] = caballoB;
		tablero[7][7] = torreB;

		for (int i = 0; i < tablero[6].length; i++) {
			tablero[6][i] = peonB;
		}

		//Declarion del jugador actual
		jugadorActual = "blanco";

		//Bucle para que el juego no pare con solo realizar un movimiento
		while (true) {
			System.out.print("  ");

			//Bucle para mostar las letras y un espacio entre ellas
			for (char letraColumna : letrasColumnas) {
				System.out.print(letraColumna + "   ");
			}
			System.out.println();
			//Bucle para mostrae los numeros del tablero con un espacio y las piezas del tablero con un espacio entre ellas
			for (int i = 0; i < tablero.length; i++) {
				System.out.print(i + " ");
				for (int j = 0; j < tablero[i].length; j++) {
					System.out.print(tablero[i][j] + "   ");
				}
				System.out.println();
				System.out.println();
			}
			try {
				System.out.println("Turno del jugador " + jugadorActual);
				boolean seleccionValida = false;
				piezaSeleccionada = "";
				filaX = 0;
				columnaY = 0;

				//Bucle por si elige una pieza no valida que no pare
				while (!seleccionValida) {

					//Pedimos la pieza que quiere mover y quitamos los espacios y lo pasamos a minusculas
					System.out.println("Dime la posición de la pieza que quieres mover en formato: (H2):");
					partePieza = sn.nextLine().replaceAll("\\s", "").replaceAll(",", "").toLowerCase();


					if (partePieza.length() == 2) {
						columna1 = partePieza.charAt(0);
						fila1 = partePieza.substring(1);

						filaX = Integer.parseInt(fila1);
						columnaY = letraAColumna(columna1);

						if (esCoordenadaValida(filaX, columnaY)) {

							//Las piezas seleccionadas las igualamos a fila x y columna y para tomar esa posicion en el tablero
							piezaSeleccionada = tablero[filaX][columnaY];

							//Estas condiciones es para que el jugador solo pueda seleccionar las piezas de su equipo
							if ((jugadorActual.equals("blanco") && Character.isUpperCase(piezaSeleccionada.charAt(0))) ||
									(jugadorActual.equals("negro") && Character.isLowerCase(piezaSeleccionada.charAt(0)))) {
								seleccionValida = true;
							}
							//Si selecciona una pieza de otro equipo daria este error 
							else {
								System.out.println("Selecciona una pieza válida de tu equipo.");
							}
						} 

						//Si introduce numero de coordenadas superiores a h o 8 daria este error
						else {
							System.out.println("Las coordenadas están fuera del tablero.");
						}
					}

					//Si introcuce unas coordenadas modo(1a) daria este error  
					else {
						System.out.println("Error, Ingresa las coordenadas en el formato correcto (h1).");
					}
				}

				System.out.println("La pieza seleccionada es: " + piezaSeleccionada);
				movimientoValido = false;

				//Mientras que el movimiento no sea invalido estara en este bucle
				while (!movimientoValido) {
					System.out.println("Introduce la coordenada donde quieres mover la pieza (H2):");
					destinoLimpio = sn.nextLine().replaceAll("\\s", "").replaceAll(",", "").toLowerCase();

					//Comprobamos que el destino al que se quiere llevar la pieza es valido
					if (destinoLimpio.length() == 2) {
						columnaDestino = destinoLimpio.charAt(0);
						filaDestino = Integer.parseInt(destinoLimpio.substring(1));

						if (esCoordenadaValida(filaDestino, letraAColumna(columnaDestino))) {
							boolean esMovimientoValido = false;

							// Verificar si la pieza seleccionada es un peón (negro o blanco)
							if (piezaSeleccionada.equals(peonN) || piezaSeleccionada.equals(peonB)) {

								// Validar el movimiento del peón blanco o negro según el jugador actual
								esMovimientoValido = (jugadorActual.equals("blanco"))
										? MovimientoPeonBlanco.esMovimientoPeonBlancoValido(filaX, columnaY, filaDestino, letraAColumna(columnaDestino), tablero)
												: MovimientoPeonNegro.esMovimientoPeonNegroValido(filaX, columnaY, filaDestino, letraAColumna(columnaDestino), tablero);
							} 

							// Verificar si la pieza seleccionada es un caballo (negro o blanco)
							else if (piezaSeleccionada.equals(caballoN) || piezaSeleccionada.equals(caballoB)) {
								// Validar el movimiento del caballo
								esMovimientoValido = MovimientoCaballo.esMovimientoCaballoValido(filaX, columnaY, filaDestino, letraAColumna(columnaDestino), tablero);
							} 

							// Verificar si la pieza seleccionada es una torre (negra o blanca)
							else if (piezaSeleccionada.equals(torreN) || piezaSeleccionada.equals(torreB)) {
								// Validar el movimiento de la torre
								esMovimientoValido = MovimientoTorre.esMovimientoTorreValido(filaX, columnaY, filaDestino, letraAColumna(columnaDestino), tablero);
							} 

							// Verificar si la pieza seleccionada es un alfil (negro o blanco)
							else if (piezaSeleccionada.equals(alfilN) || piezaSeleccionada.equals(alfilB)) {
								// Validar el movimiento del alfil
								esMovimientoValido = MovimientoAlfil.esMovimientoAlfilValido(filaX, columnaY, filaDestino, letraAColumna(columnaDestino), tablero);
							} 

							// Verificar si la pieza seleccionada es una reina (negra o blanca)
							else if (piezaSeleccionada.equals(reinaN) || piezaSeleccionada.equals(reinaB)) {
								// Validar el movimiento de la reina (combina movimientos de torre y alfil)
								esMovimientoValido = MovimientoReina.esMovimientoReinaValido(filaX, columnaY, filaDestino, letraAColumna(columnaDestino), tablero);
							} 

							// Verificar si la pieza seleccionada es un rey (negro o blanco)
							else if (piezaSeleccionada.equals(reyN) || piezaSeleccionada.equals(reyB)) {
								// Validar el movimiento del rey (movimiento simple)
								esMovimientoValido = MovimientoRey.esMovimientoReyValido(filaX, columnaY, filaDestino, letraAColumna(columnaDestino));
							}

							// Si el movimiento es válido, realizarlo y actualizar el tablero
							if (esMovimientoValido) {

								// Verificar si se capturó una pieza durante el movimiento
								if (!tablero[filaDestino][letraAColumna(columnaDestino)].equals("-")) {
									System.out.println("\n¡Capturaste una pieza!");
									System.out.println("Jugador: " + jugadorActual);
									System.out.println("Pieza capturada: " + tablero[filaDestino][letraAColumna(columnaDestino)]);
								}
								// Realizar el movimiento en el tablero
								tablero[filaDestino][letraAColumna(columnaDestino)] = tablero[filaX][columnaY];
								tablero[filaX][columnaY] = "-";

								// Lógica de promoción de peones
								boolean peonPromocionado = false;

								// ...

								// Después de realizar el movimiento del peón, verifica la promoción
								if ((filaDestino == 0 && jugadorActual.equals("blanco")) || (filaDestino == 7 && jugadorActual.equals("negro"))) {
								    if (!peonPromocionado) {
								        System.out.println("¡El peón ha alcanzado el extremo del tablero! Elige una pieza para la promoción: (q/t/c/a)");
								        String eleccionPromocion = sn.nextLine().toLowerCase(); // Mantener minúsculas por defecto

								        // Verifica la elección del jugador y actualiza la posición del peón
								        switch (eleccionPromocion) {
								            case "q":
								                tablero[filaDestino][letraAColumna(columnaDestino)] = (jugadorActual.equals("blanco")) ? "Q" : "q";
								                break;
								            case "t":
								                tablero[filaDestino][letraAColumna(columnaDestino)] = (jugadorActual.equals("blanco")) ? "T" : "t";
								                break;
								            case "c":
								                tablero[filaDestino][letraAColumna(columnaDestino)] = (jugadorActual.equals("blanco")) ? "C" : "c";
								                break;
								            case "a":
								                tablero[filaDestino][letraAColumna(columnaDestino)] = (jugadorActual.equals("blanco")) ? "A" : "a";
								                break;
								            default:
								                System.out.println("Elección no válida. Se promocionará a una reina por defecto.");
								                tablero[filaDestino][letraAColumna(columnaDestino)] = (jugadorActual.equals("blanco")) ? "Q" : "q";
								                break;
								        }

								        // Indicar que el peón ha sido promocionado
								        peonPromocionado = true;
								    } else {
								        System.out.println("Este peón ya ha sido promocionado y no puede promocionarse nuevamente.");
								    }
								}
								// Indicar que el movimiento fue válido y cambiar al siguiente jugador
								movimientoValido = true;
								jugadorActual = (jugadorActual.equals("blanco")) ? "negro" : "blanco";

								//Mostrar si el usuario está en Jaque!
								if(estaEnJaque(tablero, jugadorActual)){
									System.out.println("¡Jaque!");								  
								}
							} else {
								// Informar que el movimiento es inválido para la pieza seleccionada
								System.out.println("Movimiento inválido para esta pieza.");
							}
						} else {
							System.out.println("Las coordenadas de destino están fuera del tablero.");
						}
					}
				}
			}
			//Si se introduce un caracter especial que lo detecte
			catch (NumberFormatException e) {
				System.out.println("Error al convertir las coordenadas a números enteros.");
				sn.next();
			}
		}
	}
	
	private boolean esCoordenadaValida(int fila, int columna) {
		return fila >= 0 && fila < 8 && columna >= 0 && columna < 8;
	}
	private int letraAColumna(char letraColumna) {
		return letraColumna - 'a';
	}
	private boolean estaEnJaque(String[][] tablero, String jugadorActual) {
		// Encontrar las coordenadas del rey actual
		int filaRey = -1;
		int columnaRey = -1;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				String pieza = tablero[i][j];
				if ((jugadorActual.equals("blanco") && pieza.equals("K")) ||
						(jugadorActual.equals("negro") && pieza.equals("k"))) {
					filaRey = i;
					columnaRey = j;
					break;
				}
			}
		}

		// Verificar si alguna pieza puede amenazar al rey
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				String pieza = tablero[i][j];
				if ((jugadorActual.equals("blanco") && Character.isLowerCase(pieza.charAt(0))) ||
						(jugadorActual.equals("negro") && Character.isUpperCase(pieza.charAt(0)))) {

					boolean esMovimientoValido = false;
					if (pieza.equals("p") || pieza.equals("P")) {
						esMovimientoValido = (jugadorActual.equals("blanco"))
								? MovimientoPeonBlanco.esMovimientoPeonBlancoValido(i, j, filaRey, columnaRey, tablero)
										: MovimientoPeonNegro.esMovimientoPeonNegroValido(i, j, filaRey, columnaRey, tablero);
					} else if (pieza.equals("c") || pieza.equals("C")) {
						esMovimientoValido = MovimientoCaballo.esMovimientoCaballoValido(i, j, filaRey, columnaRey, tablero);
					} else if (pieza.equals("t") || pieza.equals("T")) {
						esMovimientoValido = MovimientoTorre.esMovimientoTorreValido(i, j, filaRey, columnaRey, tablero);
					} else if (pieza.equals("a") || pieza.equals("A")) {
						esMovimientoValido = MovimientoAlfil.esMovimientoAlfilValido(i, j, filaRey, columnaRey, tablero);
					} else if (pieza.equals("q") || pieza.equals("Q")) {
						esMovimientoValido = MovimientoReina.esMovimientoReinaValido(i, j, filaRey, columnaRey, tablero);
					} else if (pieza.equals("k") || pieza.equals("K")) {
						esMovimientoValido = MovimientoRey.esMovimientoReyValido(i, j, filaRey, columnaRey);
					}

					if (esMovimientoValido) {
						return true; // El rey está en jaque
					}
				}
			}
		}
		return false; // El rey no está en jaque
	}
}
class MovimientoPeonBlanco {
	public static boolean esMovimientoPeonBlancoValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, String[][] tablero) {
		int direccion = -1;
		boolean esPrimerMovimiento = (filaOrigen == 6 && direccion == -1);
		if (columnaDestino == columnaOrigen && tablero[filaDestino][columnaDestino].equals("-")) {
			if (direccion == -1 && filaDestino < filaOrigen && filaDestino >= 0) {
				return (filaDestino - filaOrigen == -1) || (esPrimerMovimiento && (filaDestino - filaOrigen == -2) && tablero[filaOrigen + direccion][columnaOrigen].equals("-"));
			}
		} else if (Math.abs(columnaDestino - columnaOrigen) == 1 && Math.abs(filaDestino - filaOrigen) == 1) {
			return !tablero[filaDestino][columnaDestino].equals("-") && esPiezaContraria(tablero[filaOrigen][columnaOrigen], tablero[filaDestino][columnaDestino]);
		}
		return false;
	}
	private static boolean esPiezaContraria(String piezaOrigen, String piezaDestino) {
		return Character.isUpperCase(piezaOrigen.charAt(0)) != Character.isUpperCase(piezaDestino.charAt(0));
	}
}
class MovimientoPeonNegro {
	public static boolean esMovimientoPeonNegroValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, String[][] tablero) {
		int direccion = 1;
		boolean esPrimerMovimiento = (filaOrigen == 1 && direccion == 1);
		if (columnaDestino == columnaOrigen && tablero[filaDestino][columnaDestino].equals("-")) {
			if (direccion == 1 && filaDestino > filaOrigen && filaDestino <= 7) {
				return (filaDestino - filaOrigen == 1) || (esPrimerMovimiento && (filaDestino - filaOrigen == 2) && tablero[filaOrigen + direccion][columnaOrigen].equals("-"));
			}
		} else if (Math.abs(columnaDestino - columnaOrigen) == 1 && Math.abs(filaDestino - filaOrigen) == 1) {
			return !tablero[filaDestino][columnaDestino].equals("-") && esPiezaContraria(tablero[filaOrigen][columnaOrigen], tablero[filaDestino][columnaDestino]);
		}
		return false;
	}
	private static boolean esPiezaContraria(String piezaOrigen, String piezaDestino) {
		return Character.isUpperCase(piezaOrigen.charAt(0)) != Character.isUpperCase(piezaDestino.charAt(0));
	}
}
class MovimientoTorre {
	public static boolean esMovimientoTorreValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, String[][] tablero) {
		if ((filaOrigen == filaDestino && columnaOrigen != columnaDestino) || 
				(columnaOrigen == columnaDestino && filaOrigen != filaDestino)) {
			int pasoFila = (filaDestino > filaOrigen) ? 1 : (filaDestino < filaOrigen) ? -1 : 0;
			int pasoColumna = (columnaDestino > columnaOrigen) ? 1 : (columnaDestino < columnaOrigen) ? -1 : 0;
			if (pasoFila != 0) {
				for (int fila = filaOrigen + pasoFila; fila != filaDestino; fila += pasoFila) {
					if (!tablero[fila][columnaOrigen].equals("-")) {
						return false; // Hay una pieza en el camino
					}
				}
			} else {
				for (int columna = columnaOrigen + pasoColumna; columna != columnaDestino; columna += pasoColumna) {
					if (!tablero[filaOrigen][columna].equals("-")) {
						return false; // Hay una pieza en el camino
					}
				}
			}
			return tablero[filaDestino][columnaDestino].equals("-") || esPiezaContraria(tablero[filaOrigen][columnaOrigen], tablero[filaDestino][columnaDestino]);
		}
		return false;
	}
	private static boolean esPiezaContraria(String piezaOrigen, String piezaDestino) {
		return !piezaDestino.equals("-") && Character.isUpperCase(piezaOrigen.charAt(0)) != Character.isUpperCase(piezaDestino.charAt(0));
	}
}
class MovimientoCaballo {
	public static boolean esMovimientoCaballoValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, String[][] tablero) {
		int filaDelta = Math.abs(filaDestino - filaOrigen);
		int columnaDelta = Math.abs(columnaDestino - columnaOrigen);
		if ((filaDelta == 2 && columnaDelta == 1) || (filaDelta == 1 && columnaDelta == 2)) {
			String piezaDestino = tablero[filaDestino][columnaDestino];
			return piezaDestino.equals("-") || esPiezaContraria(tablero[filaOrigen][columnaOrigen], piezaDestino);
		}
		return false;
	}

	private static boolean esPiezaContraria(String piezaOrigen, String piezaDestino) {
		return Character.isUpperCase(piezaOrigen.charAt(0)) != Character.isUpperCase(piezaDestino.charAt(0));
	}
}
class MovimientoAlfil {
	public static boolean esMovimientoAlfilValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, String[][] tablero) {
		int filaDelta = Math.abs(filaDestino - filaOrigen);
		int columnaDelta = Math.abs(columnaDestino - columnaOrigen);
		return filaDelta == columnaDelta && noHayObstruccionesDiagonales(filaOrigen, columnaOrigen, filaDestino, columnaDestino, tablero) &&
				(tablero[filaDestino][columnaDestino].equals("-") || esPiezaContraria(tablero[filaOrigen][columnaOrigen], tablero[filaDestino][columnaDestino]));
	}
	private static boolean esPiezaContraria(String piezaOrigen, String piezaDestino) {
		return Character.isUpperCase(piezaOrigen.charAt(0)) != Character.isUpperCase(piezaDestino.charAt(0));
	}
	private static boolean noHayObstruccionesDiagonales(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, String[][] tablero) {
		int pasoFila = (filaDestino > filaOrigen) ? 1 : -1;
		int pasoColumna = (columnaDestino > columnaOrigen) ? 1 : -1;
		for (int i = filaOrigen + pasoFila, j = columnaOrigen + pasoColumna; i != filaDestino && j != columnaDestino; i += pasoFila, j += pasoColumna) {
			if (!tablero[i][j].equals("-")) {
				return false;
			}
		}
		return true;
	}
}
class MovimientoReina {
	public static boolean esMovimientoReinaValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, String[][] tablero) {
		return (MovimientoTorre.esMovimientoTorreValido(filaOrigen, columnaOrigen, filaDestino, columnaDestino, tablero) ||
				MovimientoAlfil.esMovimientoAlfilValido(filaOrigen, columnaOrigen, filaDestino, columnaDestino, tablero)) &&
				(tablero[filaDestino][columnaDestino].equals("-") || esPiezaContraria(tablero[filaOrigen][columnaOrigen], tablero[filaDestino][columnaDestino]));
	}
	private static boolean esPiezaContraria(String piezaOrigen, String piezaDestino) {
		return Character.isUpperCase(piezaOrigen.charAt(0)) != Character.isUpperCase(piezaDestino.charAt(0));
	}
}
class MovimientoRey {
	public static boolean esMovimientoReyValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
		int filaDelta = Math.abs(filaDestino - filaOrigen);
		int columnaDelta = Math.abs(columnaDestino - columnaOrigen);
		return filaDelta <= 1 && columnaDelta <= 1;
	}
}