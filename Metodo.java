
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Metodo {

    public ObjEps[] LLenarArreglo(ObjEps[] m, Scanner sc ){
        for (int i = 0; i < m.length; i++) {
            ObjEps o = new ObjEps();

            System.out.println("Ingrese el Id ");
            o.setId(sc.nextInt());
            System.out.println("Ingrese el nombre del Paciente");
            o.setNombre(sc.next());
            System.out.println("Ingrese  la edad del paciente");
            o.setEdad(sc.nextInt());
            System.out.println("Ingre el servicio");
            o.setServicio(sc.next());
            o.setEstado("Pendiente");

            m[i] = o;
        }
        return m;
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

    public ObjEps CambiarSevicio(ObjEps[] paciente, Scanner sc){
        System.out.println("Ingrese el Id del paciente");
        int id = sc.nextInt();
        ObjEps o = BuscarPaciente(paciente,id);
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
    
    



}