package Warsztat;

import java.util.Scanner;

public class ExerciseManagement{
    public static void main(String[] args) {
        new ExerciseManagement();
    }
    public ExerciseManagement() {
        ExerciseDao exerciseDao = new ExerciseDao();
            PrintAllExercise(exerciseDao);
            Scanner in = new Scanner(System.in);
            int menuoption = 0;
            do {
                display_menu();
                menuoption =in.nextInt();
                in.nextLine();
                switch (menuoption) {
                    case 1:
                        System.out.println("You picked option 1");
                        addExercise(exerciseDao);
                        PrintAllExercise(exerciseDao);
                        break;
                    case 2:
                        System.out.println("You picked option 2");
                        editExercise(exerciseDao);
                        PrintAllExercise(exerciseDao);
                        break;
                    case 3:
                        System.out.println("You picked option 3");
                        deleteExercise(exerciseDao);
                        PrintAllExercise(exerciseDao);
                        break;
                    case 4:
                        System.out.println("You picked option 4");
                        System.err.println("You picked quit, bye !");
                        break;
                    default:
                        System.err.println("Unrecognized option");
                        break;
                }
            } while (menuoption != 4);
        }


    private static void deleteExercise(ExerciseDao exerciseDao) {
        int id;
        Scanner scr = new Scanner ( System.in );
        System.out.println("Please insert id for delete exercise: ");
        id = scr.nextInt();
        exerciseDao.delete(id);
    }

    private static void editExercise(ExerciseDao exerciseDao) {

        int id;
        String title;
        String description;

        Scanner scr = new Scanner(System.in);
        System.out.println("insert id: ");
        id = scr.nextInt();
        scr.nextLine();
        System.out.println("insert title: ");
        title = scr.nextLine();
        System.out.println("insert description:");
        description = scr.nextLine();

        Exercise exercise = new Exercise(title,description);
        exercise.setId(id);
        exerciseDao.update(exercise);
    }

    private static void addExercise(ExerciseDao exerciseDao) {

         String title;
         String description;

         Scanner scr = new Scanner(System.in);
         System.out.println("insert title: ");
         title = scr.nextLine();
         System.out.println("insert description: ");
         description = scr.nextLine();

         Exercise exercise = new Exercise(title,description);
         exerciseDao.create(exercise);
    }

    private static void PrintAllExercise(ExerciseDao exerciseDao) {
        System.out.println("List of exercise: ");
        for (Exercise exercise : exerciseDao.findAll()) {
            System.out.println(exercise);
        }
        System.out.println();
    }

    public static void display_menu() {
        System.out.println("1) add – dodanie zadania \n2) edit – edycja zadania\n3) delete – usunięcie zadania\n4) quit – zakończenie programu");
        System.out.println("Wybierz jedną z opcji: ");
    }
}
