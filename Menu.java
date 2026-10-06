import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Metodo m = new Metodo();

        ObjEps[]paciente = new ObjEps[1];
       paciente = m.LLenarArreglo(paciente, sc);
        Stack<ObjEps> pilaPendientes = new Stack<>(); 
        Queue<ObjEps> colaAtendidos = new LinkedList<>(); 
        Queue<ObjEps> colaCancelados = new LinkedList<>(); 
        Queue<ObjEps> colaPrioritarios = new LinkedList<>(); 
        String[] historial = new String[10];


        boolean continuar = true;

      while (continuar) {
        System.out.println("\n======================================"); 
        System.out.println(" Servicio de EPS contigo"); 
        System.out.println("1)Registar Paciente");
        System.out.println("2) Mostrar pacientes registrados"); 
        System.out.println("3) Enviar paciente a pendientes"); 
        System.out.println("4) Mostrar pacientes pendientes"); 
        System.out.println("5) Atender paciente"); 
        System.out.println("6) Cancelar cita"); 
        System.out.println("7) Cambiar servicio"); 
        System.out.println("8) Marcar paciente como prioritario"); 
        System.out.println("9) Retirar paciente de atencion"); 
        System.out.println("10) Volver a solicitar atencion"); 
        System.out.println("11) Mostrar todos los pacientes"); 
        System.out.println("12) Mostrar historial de operaciones"); 
        System.out.println("13) Mostrar pacientes atendidos"); 
        System.out.println("14) Mostrar pacientes cancelados"); 
        System.out.println("15) Mostrar pacientes prioritarios");
       

        System.out.println("\nSeleccione una opcion:"); 
        int opcion = sc.nextInt();
        switch (opcion) {
             case 1:
                paciente = m.LLenarArreglo(paciente, sc);
                
                break;
            case 2:
                paciente = m.MostrarArreglo(paciente);
                
                break;

            case 3:
                pilaPendientes = m.EnviarPendientes(paciente, null, sc);
                historial = m.AgregarHistorial(historial, "Paciente enviado pendientes");
                
                break;
            case 4:
                pilaPendientes = m.MostrarPilaPendientes(pilaPendientes);
    
                break;
            case 5:
                colaAtendidos = m.AtenderPacientes(pilaPendientes, null);
                historial = m.AgregarHistorial(historial, "Paciente Atendido");

                 break;
            case 6:
                colaCancelados = m.Cancelaciondecitas(colaCancelados, sc, paciente);
                historial = m.AgregarHistorial(historial, "cita cancelada");

                
                break;
            case 7:
                 
                break;
            case 8:
                colaPrioritarios = m.MostrarColaPrioritarios(colaPrioritarios);
                historial = m.AgregarHistorial(historial, "Paciente mrcado como Prioritaria");
                
                break;

            case 9:
                colaAtendidos = m.RetirarAtencion(colaAtendidos, paciente, sc);
                historial = m.AgregarHistorial(historial, "Paciente retirado de la atencion");
                
                break;
            case 10:
                pilaPendientes = m.SolicitarAtencion(pilaPendientes, paciente, sc);
                historial = m.AgregarHistorial(historial, "Paciente volvio solcitar atencion");
                
                break;
            case 11:
                paciente = m.MostrarTodosPacientes(pilaPendientes, paciente, colaPrioritarios, colaCancelados, colaAtendidos);
                
                break;
            case 12:
                historial = m.MostrarHistorial(historial);
                
                break;
            case 13:
                colaAtendidos = m.MostrarColaAtendidos(colaAtendidos);
                
                break;
            case 14:
                colaCancelados = m.MostrarColaCancelados(colaCancelados);                
                break;
            case 15:
                colaPrioritarios = m.MarcaPrioritario(pilaPendientes, colaPrioritarios, paciente, sc);
                break;
        default:
                System.out.println("Opcion no valida");
                break;
        }
        
      }
    }
}
