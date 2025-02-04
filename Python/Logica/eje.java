import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class SistemaInterfazGrafica extends JFrame {

    private JTable table;
    private JTextArea reporteArea;
    private JTextArea tendenciasArea;

    public SistemaInterfazGrafica(int n) {
        Random random = new Random();
        // inicializacion de variables
        String[] nombresGenerados = {
    "Carlos", "Daniel", "Robert", "Juan", "Luis", "Maria", "Alberto", "Sofia", "Pedro", "Camila",
    "Diego", "Daniela", "Emily", "Lucia", "Miguel", "Fernando", "Valentina", "Andrés", "Paula", "Javier",
    "Gabriela", "Ricardo", "Adriana", "Hugo", "Elena", "Esteban", "Patricia", "Sebastián", "Alejandra", "Raúl",
    "Isabel", "Manuel", "Verónica", "Francisco", "Victoria", "Eduardo", "Rosa", "Gustavo", "Antonella", "Matías",
    "Natalia", "Felipe", "Lorena", "Joaquín", "Mariana", "Cristian", "Juliana", "Santiago", "Beatriz", "Óscar"
        };
        String[] nombres = new String[n];
        double[][] calificaciones = new double[n][3];
        String nombre;
        double promedio;
        int frecuencia;
        Map<String, Object[]> mapaEstudiantes = new HashMap<>();
        
        // Configuración del JFrame
        setTitle("Sistema de Calificaciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear un panel para la tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        String[] columnNames = {"Nombre", "Matematica", "Fisica", "Quimica", "Promedio", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPaneTabla = new JScrollPane(table);
        panelTabla.add(scrollPaneTabla, BorderLayout.CENTER);
        add(panelTabla, BorderLayout.NORTH);

        // Crear un panel para el reporte y las tendencias
        JPanel panelReporte = new JPanel(new GridLayout(1, 2));
        reporteArea = new JTextArea();
        reporteArea.setEditable(false);
        JScrollPane scrollPaneReporte = new JScrollPane(reporteArea);
        panelReporte.add(scrollPaneReporte);

        tendenciasArea = new JTextArea();
        tendenciasArea.setEditable(false);
        JScrollPane scrollPaneTendencias = new JScrollPane(tendenciasArea);
        panelReporte.add(scrollPaneTendencias);

        add(panelReporte, BorderLayout.CENTER);

        // Generar datos aleatorios
        for (int i = 0; i < n; i++) {
            nombres[i] = nombresGenerados[random.nextInt(nombresGenerados.length)];
            for (int j = 0; j < 3; j++) {
                calificaciones[i][j] = Math.round(random.nextDouble() * 10 * 100) / 100.0;
            }

            promedio = calcularPromedio(calificaciones, i);
            frecuencia = (promedio < 7) ? 1 : 2;
            mapaEstudiantes.put(nombres[i], new Object[]{promedio, frecuencia});
            String estado = (promedio >= 7) ? "Aprobado" : "Reprobado";
            model.addRow(new Object[]{
                nombres[i],
                calificaciones[i][0],
                calificaciones[i][1],
                calificaciones[i][2],
                promedio,
                estado
            });

            // Generar reporte por estudiante
            reporteArea.append("Estudiante: " + nombres[i] + "\n");
            reporteArea.append("Calificaciones: " + calificaciones[i][0] + ", " + calificaciones[i][1] + ", " + calificaciones[i][2] + "\n");
            reporteArea.append("Promedio: " + promedio + " - " + estado + "\n\n");
        }

        // Mostrar las tendencias de los estudiantes
        tendenciasArea.append("Tendencias de estudiantes:\n");
        for (Map.Entry<String, Object[]> entry : mapaEstudiantes.entrySet()) {
            nombre = entry.getKey();
            promedio = (double) entry.getValue()[0];
            frecuencia = (int) entry.getValue()[1];
            tendenciasArea.append("Estudiante: " + nombre + " - Promedio: " + promedio + " - Frecuencia: " + frecuencia + "\n");
        }
    }

    public static double calcularPromedio(double[][] calificaciones, int cont) {
        double suma = 0;
        for (double calificacion : calificaciones[cont]) {
            suma += calificacion;
        }
        return Math.round((suma / calificaciones[cont].length) * 100) / 100.0;
    }

    public static void main(String[] args) {
        //inicializacion
        int n;
        // Pedir al usuario el número de estudiantes
        String input = JOptionPane.showInputDialog("Ingrese el número de estudiantes:");
        n = Integer.parseInt(input);

        // Crear y mostrar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            SistemaInterfazGrafica interfaz = new SistemaInterfazGrafica(n);
            interfaz.setVisible(true);
        });
    }
}