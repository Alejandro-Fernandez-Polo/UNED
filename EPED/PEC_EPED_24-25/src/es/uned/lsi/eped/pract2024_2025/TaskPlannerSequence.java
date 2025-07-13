package es.uned.lsi.eped.pract2024_2025;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.ListIF;
import es.uned.lsi.eped.DataStructures.List;

/**
 * La clase TaskPlannerSequence proporciona una implementación de la interfaz TaskPlannerIF.
 * Mantiene dos listas para gestionar tareas:
 * - futureTasks: para almacenar tareas programadas para el futuro.
 * - pastTasks: para almacenar tareas ya completadas o descartadas.
 * <p>
 * Las tareas se gestionan en orden secuencial según su fecha, lo que garantiza que se agreguen,
 * eliminen, actualicen o ejecuten siguiendo su programación lógica.
 */
public class TaskPlannerSequence implements TaskPlannerIF {

    /**
     * Representa la colección de tareas pasadas ejecutadas o descartadas
     * dentro de TaskPlannerSequence. Esta lista registra las tareas completadas o no completadas
     * para el seguimiento histórico y permite la iteración sobre tareas pasadas si es necesario.
     * <p>
     * La lista `pastTasks` se implementa como una interfaz de lista genérica (ListIF<Task>)
     * y almacena instancias de la clase Task.
     * <p>
     * Esta lista está diseñada para proporcionar acceso ordenado al historial de tareas
     * gestionadas por la secuencia del planificador de tareas.
     */
    protected ListIF<Task> pastTasks;
    /**
     * Una lista que almacena las próximas tareas que se planificarán o ejecutarán.
     * Cada tarea de la lista se representa mediante una instancia de la clase Task,
     * lo que permite gestionar actividades futuras al asociar una descripción y una fecha a cada tarea.
     * <p>
     * Esta estructura admite diversas operaciones, como añadir, eliminar o reprogramar tareas,
     * y desempeña un papel fundamental en el seguimiento de las tareas pendientes.
     */
    protected ListIF<Task> futureTasks;

    /**
     * Constructor de la clase TaskPlannerSequence.
     * Inicializa el objeto TaskPlannerSequence creando listas vacías
     * para gestionar tareas futuras y pasadas.
     */
    public TaskPlannerSequence() {
        futureTasks = new List<Task>();
        pastTasks = new List<Task>();
    }

    /**
     * Añade una nueva tarea con su descripción y fecha específica a la lista de tareas futuras,
     * manteniéndola ordenada por fecha. Si ya existe una tarea en la fecha especificada,
     * no se añade la nueva tarea y se muestra un mensaje.
     *
     * @param text La descripción de la tarea que se desea añadir.
     * @param date La fecha en la que debe completarse la tarea, representada como un número entero.
     */
    public void add(String text, int date) {
        Task nuevaTarea = new Task(text, date);

        if (nuevaTarea.getText() != null) {
            int pos = 1;

            // Buscar la posición de inserción ordenada
            while (pos <= futureTasks.size() && futureTasks.get(pos).compareTo(nuevaTarea) < 0) {
                pos++;
            }

            // Verificar si ya existe una tarea en esa fecha
            if (pos <= futureTasks.size() && futureTasks.get(pos).compareTo(nuevaTarea) == 0) {
                System.out.println("Esta fecha ya tiene una tarea asignada");
            } else {
                futureTasks.insert(pos, nuevaTarea);
                System.out.println(text + " " + date + ": Tarea incluida");
            }
        }
    }

    /**
     * Elimina una tarea cuyo campo de fecha coincide con el valor proporcionado.
     * Recorre la lista de tareas futuras y elimina la tarea si encuentra una
     * coincidencia en la fecha.
     *
     * @param date la fecha de la tarea que debe eliminarse, representada como un número entero
     */
    public void delete(int date) {
        for (int i = 1; i <= futureTasks.size(); i++) {
            if (futureTasks.get(i).getDate() == date) {
                futureTasks.remove(i);
                System.out.println(date + ": Tarea eliminada");
            }
        }
    }

    /**
     * Reprograma una tarea, cambiando su fecha original a una nueva fecha especificada.
     *
     * @param origDate la fecha actual de la tarea que se desea mover.
     * @param newDate  la nueva fecha a la que se desea mover la tarea.
     */
    public void move(int origDate, int newDate) {
        for (int i = 1; i <= futureTasks.size(); i++) {
            Task t = futureTasks.get(i);
            if (t.getDate() == origDate) {
                futureTasks.remove(i);
                t.setDate(newDate);
                this.add(t.getText(), t.getDate());
                System.out.println(origDate + " " + newDate + ": Tarea movida de fecha");
            }
        }
    }


    /**
     * Ejecuta la siguiente tarea de la lista de tareas futuras.
     * La tarea se marca como completada, se elimina de la lista de tareas futuras y se añade al historial
     * de tareas pasadas. Se imprime un mensaje para indicar que la tarea se ha completado.
     * <p>
     * Condiciones previas:
     * - La lista de tareas futuras no debe estar vacía.
     * <p>
     * Condiciones posteriores:
     * - La siguiente tarea (la primera de la lista de tareas futuras) se marca como completada.
     * - La tarea se elimina de la lista de tareas futuras.
     * - La tarea se añade al final de la lista de tareas pasadas.
     */
    public void execute() {
        if (!futureTasks.isEmpty()) {
            Task next = futureTasks.get(1);
            next.setCompleted();
            futureTasks.remove(1);
            pastTasks.insert(pastTasks.size() + 1, next);
            System.out.println("Tarea realizada");
        }
    }

    /**
     * Descarta la siguiente tarea moviéndola de la lista de tareas futuras
     * a la lista de tareas pasadas, marcándola como no completada.
     * <p>
     * Condiciones previas:
     * - La lista de tareas futuras no debe estar vacía.
     * - La primera tarea de la lista de tareas futuras se identifica en la posición 1.
     * - La tarea se elimina de la lista de tareas futuras en la posición 1 y se añade
     * a la lista de tareas pasadas al final de la lista sin marcarla como completada.
     * <p>
     * Condiciones posteriores:
     * - La tarea ya no forma parte de la lista de tareas futuras.
     * - La tarea se añade a la lista de tareas pasadas como incompletada.
     */
    public void discard() {
        if (!futureTasks.isEmpty()) {
            Task next = futureTasks.get(1);
            futureTasks.remove(1);
            pastTasks.insert(pastTasks.size() + 1, next);
            System.out.println("Tarea descartada");
        }
    }

    /**
     * Devuelve un iterador que itera sobre la colección de tareas futuras.
     *
     * @return un iterador de tipo IteratorIF<TaskIF> que itera secuencialmente a través de las tareas en la lista de tareas futuras.
     */
    public IteratorIF<TaskIF> iteratorFuture() {
        System.out.println("iteratorFuture");
        ListIF<TaskIF> futureTasksIF = new List<TaskIF>();
        for (int i = 1; i <= futureTasks.size(); i++) {
            futureTasksIF.insert(i, futureTasks.get(i));
        }
        return futureTasksIF.iterator();
    }

    /**
     * Devuelve un iterador de la lista histórica de tareas pasadas.
     *
     * @return un objeto IteratorIF<TaskIF> para iterar sobre las tareas pasadas.
     */
    public IteratorIF<TaskIF> iteratorPast() {
        System.out.println("iteratorPast");
        ListIF<TaskIF> pastTasksIF = new List<TaskIF>();
        for (int i = 1; i <= pastTasks.size(); i++) {
            pastTasksIF.insert(i, pastTasks.get(i));
        }
        return pastTasksIF.iterator();
    }

}