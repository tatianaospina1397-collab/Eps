
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Metodo {

    public ObjEps[] LLenarArreglo(
        ObjEps[] pacientes,
        Scanner sc) {

    int posicion = 0;
    int continuar = 1;

    while (continuar == 1) {

        
        if (posicion == pacientes.length) {

            ObjEps[] nuevo =
                    new ObjEps[pacientes.length + 1];

            for (int i = 0; i < pacientes.length; i++) {

                nuevo[i] = pacientes[i];
            }

            pacientes = nuevo;
        }

        ObjEps o = new ObjEps();

        System.out.println("\n===== REGISTRAR PACIENTE =====");

        System.out.println("Ingrese el ID del paciente");
        o.setId(sc.nextInt());

        System.out.println("Ingrese el nombre del paciente");
        o.setNombre(sc.next());

        System.out.println("Ingrese la edad del paciente");
        o.setEdad(sc.nextInt());

        System.out.println("Ingrese el servicio");
        o.setServicio(sc.next());

        o.setEstado("Pendiente");

        pacientes[posicion] = o;

        posicion++;

        System.out.println("\nPaciente registrado correctamente");

        System.out.println("\n¿Desea ingresar otro paciente?");
        System.out.println("1) Si");
        System.out.println("2) No");

        continuar = sc.nextInt();
    }

    return pacientes;
}

    public ObjEps[] MostrarArreglo(ObjEps[] m){
        for (int i = 0; i < m.length; i++) {
            if (m[i] != null) {
                System.out.println( "ID: " + m[i].getId() + " Nombre: " + m[i].getNombre() + " | Edad: " + m[i].getEdad() + " Servicio: " + m[i].getServicio() + " Estado: " + m[i].getEstado());
                
            }
        }
        return m;
    }

    public ObjEps BuscarPaciente(ObjEps[] m, int id){
        for (int i = 0; i < m.length; i++) {
            if (m[i] !=null && m[i].getId() == id ) {
                return m[i];
                
            }
        }
        return null;
    }

    public Stack<ObjEps> EnviarPendientes(ObjEps[] pacientes, Stack<ObjEps> pilaPendientes, Scanner sc){
        System.out.println("Ingrese el Id del paciente");
            int id = sc.nextInt();

            ObjEps o = BuscarPaciente(pacientes, id);
            if(o != null){
                if(o.getEstado().equals("Pendiente")){
                    pilaPendientes.push(o);
                    System.out.println("Paciente enviado a la pila de pendientes");
                }
                else{
                    System.out.println("El paciente no puede ser enviado por su estado es :"+ o.getEdad());
                }

            }
            else{
                System.out.println("Paciente no fue encontrado");
            }

            return pilaPendientes;
    }

    public  Stack<ObjEps> MostrarPilaPendientes( Stack<ObjEps> pilaPendientes){
        if(pilaPendientes.isEmpty()){
            System.out.println("No Hay pacientes pendientes");
        }
        else{
            for(ObjEps o : pilaPendientes){
                System.out.println( "ID: " + o.getId() + " Nombre: " + o.getNombre() + " | Edad: " + o.getEdad() + " Servicio: " + o.getServicio() + " Estado: " + o.getEstado());
                

            }
        }
        return pilaPendientes;
    }

    public Queue<ObjEps> AtenderPacientes(Stack<ObjEps> pilaPendientes, Queue<ObjEps> colasAtendidos){
        if(!pilaPendientes.isEmpty()){
            ObjEps o = pilaPendientes.pop();
            o.setEstado("Paciente ATendido");
            colasAtendidos.offer(o);
            System.out.println("Paciente atendido" + o.getNombre());
        }
        else{
            System.out.println("No hay paciente en espera");
        }
        return colasAtendidos;

    }

    public Queue<ObjEps> Cancelaciondecitas(Queue<ObjEps>colaCancelada,Scanner sc,ObjEps[] pacientes){
     System.out.println("Ingrese el Id del pacienre");
        int id = sc.nextInt();
        ObjEps o = BuscarPaciente(pacientes, id);
        if(o != null){
            if(o.getEstado().equals("Atendido")){
                o.setEstado("Cancelado");
                colaCancelada.offer(o);
                System.out.println("Cita cancelada para: "+ o.getNombre());
            }
            else{
                System.out.println("Paciente ya fue atendido y no se puede cancelar");
            }

        }   
        return colaCancelada;
    }

    public ObjEps CambiarSevicio(ObjEps[] pacientes, Scanner sc){
        
        System.out.println("Ingrese el Id del paciente");
        int id = sc.nextInt();
        ObjEps o = BuscarPaciente(pacientes,id);
        if(o != null){
            System.out.println("Servicio actual es: "+ o.getServicio());
            System.out.println("Ingrese el nuevo servicio");
            String nuevoServicio = sc.next();
            o.setServicio(nuevoServicio);
            System.out.println("Servicio cambiado correctamente");

        }
        else{
            System.out.println("Paciente no encontrado");

        }
        return  o;
    }

    public Queue<ObjEps> MarcaPrioritario(Stack<ObjEps> pilaPendientes, Queue<ObjEps> colaPrioritaria, ObjEps[] pacientes, Scanner sc ){
         System.out.println("Ingrese el Id del pacienre");
        int id = sc.nextInt();
        ObjEps o = BuscarPaciente(pacientes, id);
        if(o != null){
            if(!o.getEstado().equals("Atendido") && !o.getEstado().equals("Cancelado")){
                o.setEstado("Prioritario");
                colaPrioritaria.offer(o);
                System.out.println("Paciente marcado Prioritario");
            }
            else{
                System.out.println("No se puede cambiar el estado al Paciente");
            }
        }
        else{
            System.out.println("Paciente no encontrado");
        }
        return  colaPrioritaria;
    }

    public Queue<ObjEps> RetirarAtencion(Queue<ObjEps> colasAtendidos, ObjEps[] pacientes, Scanner sc){
        System.out.println("Ingrese el Id del pacienre");
        int id = sc.nextInt();
        Queue<ObjEps> aux = new LinkedList<>();
        boolean encontrar = false;
        while (!colasAtendidos.isEmpty()) {
            ObjEps o = colasAtendidos.poll();
            if(o.getId() == id){
                o.setEstado("Retirado");
                encontrar = true;
                System.out.println("Paciente retirado de la atencion");
            }
            else{
                aux.offer(o);
            }
            
        }
        while (!aux.isEmpty()) {
            colasAtendidos.offer(aux.poll());
            
        }
        if (!encontrar) {
            System.out.println("El paciente no fue encontrado");
            
        }
        return colasAtendidos;

    }


     public Stack<ObjEps> SolicitarAtencion(Stack<ObjEps> pilaPendiente, ObjEps[] pacientes, Scanner sc){
        System.out.println("Ingrese el Id del pacienre");
        int id = sc.nextInt();   
        ObjEps o = BuscarPaciente(pacientes, id);
        if(o!= null){
            if (o.getEstado().equals("Cancelado") || o.getEstado().equals("Retirado")) {
                o.setEstado("Pendiente");
                pilaPendiente.push(o);
                System.out.println("Paciente volvio solicitar atencion");

                
            }else{
                System.out.println("No se puede solicitar atencion");
            }
        } 
        else{
            System.out.println("Paciente no fue econtrado"  );

        }
        return pilaPendiente;
    }

    public ObjEps MostrarPacientes(ObjEps o){
        System.out.println("id"+ o.getId() + "Nombre" + o.getNombre() + "Edad" + o.getEdad() + "Servicio" + o.getServicio() + "Estado" + o.getEstado());
        return o;
    }

    public Queue<ObjEps> MostrarColaAtendidos(Queue<ObjEps> colasAtendidos){
        if(colasAtendidos.isEmpty()){
            System.out.println("No hay pacientes por atender");
        }
         else{
            for(ObjEps o : colasAtendidos){
                MostrarPacientes(o);

            }
         }
         return colasAtendidos;
    }
    public Queue<ObjEps> MostrarColaCancelados(Queue<ObjEps> colaCancelada){
        if(colaCancelada.isEmpty()){
            System.out.println("No hay pacientes cancelados");
        }
        else{
            for(ObjEps o : colaCancelada){
                MostrarPacientes(o);
            }
        }
        return colaCancelada;
    }
    public Queue<ObjEps> MostrarColaPrioritarios(Queue<ObjEps> colaPrioritaria){
        if(colaPrioritaria.isEmpty()){
            System.out.println("No hay pacientes prioritarios");
        }
        else{
            for(ObjEps o : colaPrioritaria){
                MostrarPacientes(o);
            }
        }
        return colaPrioritaria;
    }
    public ObjEps[] MostrarTodosPacientes(Stack<ObjEps> pilaPendiente, ObjEps[] pacientes,Queue<ObjEps> colaPrioritaria,Queue<ObjEps> colaCancelada,Queue<ObjEps> colasAtendidos){
        System.out.println("Lista de pacientes");
        System.out.println("\n=================================");
        MostrarArreglo(pacientes);
        MostrarPilaPendientes(pilaPendiente);
        MostrarColaAtendidos(colasAtendidos);
        MostrarColaCancelados(colaCancelada);
        MostrarColaPrioritarios(colaPrioritaria);
        return pacientes;
    }
    public String[] AgregarHistorial(String[] historial, String operacion){
        int posicion = 0;
        while (posicion < historial.length && historial[posicion] !=null) {
            posicion ++;
        }

        if (posicion < historial.length) { 
            historial[posicion] = operacion; 
        }
        else {
             String[] nuevo = new String[historial.length + 5]; 
             for (int i = 0; i < historial.length; i++){ 
                nuevo[i] = historial[i]; 
            } 
                nuevo[historial.length] = operacion; historial = nuevo; 
            }
            return historial;

    }
    public String[] MostrarHistorial( String[] historial) { 
    boolean hayOperaciones = false; 
    for (int i = 0; i < historial.length; i++) { 
        if (historial[i] != null) { 
            System.out.println( (i + 1) + ") " + historial[i] ); hayOperaciones = true; 
        } 
    } 
    if (!hayOperaciones) { System.out.println( "No se han realizado operaciones" ); 

    } 
    return historial; 
} 

}