package levels;

import java.awt.*;

public class Laberinto {
    private int fila = 0, columna = 0;
    private final int numfilas = 13, numcolumnas = 23;
    private final int altobloque = 30, anchobloque = 30;
    Color colorfondo = Color.white;
    Leer_niveles levels;

    public void paintfield(Graphics g) {
        int[][] laberinto = obtenerLaberinto();


        for (fila = 0; fila < numfilas; fila++) {
            for (columna = 0; columna < numcolumnas; columna++) {
                if (laberinto[fila][columna] == 1) {
                    g.setColor(Color.gray);
                    g.fillRect(columna * 30, fila * 30, anchobloque, altobloque);
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(columna * 30, fila * 30, anchobloque, altobloque);
                } else if (laberinto[fila][columna] == 2) {
                    g.setColor(Color.PINK);

                    int xpoints[] = {345, 330, 360};
                    int ypoints[] = {180, 210, 210};
                    int npoints = 3;

                    g.fillPolygon(xpoints, ypoints, npoints);

                }
            }
        }

    }

    public int[][] obtenerLaberinto() {
        int nivel1[][] = levels.buildmaze();
        return nivel1;
    }
}
