package Warsztat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        new TaskManager();
    }
    public TaskManager(){
        SolutionDao solutionDao = new SolutionDao();


        Scanner in = new Scanner(System.in);
        int menuoption = 0;

        do {
            display_menu();
            menuoption =in.nextInt();
            in.nextLine();
            switch (menuoption) {
                case 1:
                    System.out.println("You picked option 1");
                    SolutionAdd(solutionDao);

                    break;
                case 2:
                    System.out.println("You picked option 2");
                    PrintAllUserSolutions(solutionDao);
                    break;
                case 3:
                    System.out.println("You picked option 3");
                    System.err.println("You picked quit, bye !");
                    break;
                default:
                    System.err.println("Unrecognized option");
                    break;
            }
        } while (menuoption != 3);
    }

    private void PrintAllUserSolutions(SolutionDao solutionDao) {

        Scanner scr = new Scanner(System.in);
        System.out.println("Insert user ID:  ");
        int users_id =scr.nextInt();

        System.out.println(Arrays.toString(solutionDao.findAllByUserId(users_id)));
    }

    private void SolutionAdd(SolutionDao solutionDao) {
        ExerciseDao exerciseDao = new ExerciseDao();
        UserDao userDao = new UserDao();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();

        String created = dateFormat.format(date);
        String updated = null;
        String description = "";
        int exercise_id;
        int users_id;

        printAll(userDao);
        Scanner scr = new Scanner(System.in);
        System.out.println("podaj id użytkownika: ");
        users_id = scr.nextInt();
        PrintAllExercise(exerciseDao);
        System.out.println("podaj id zadania: ");
        exercise_id = scr.nextInt();


        Solution solution = new Solution(created,updated,description,exercise_id,users_id);
        solutionDao.create(solution);
        printAllTasks(solutionDao);
    }

    private void printAllTasks(SolutionDao solutionDao) {

        System.out.println("Tasks list: ");
        for (Solution solution: solutionDao.findAll()){
            System.out.println(solution);
        }
        System.out.println();
    }

//    private void addSolution(SolutionDao solutionDao) {
//
//        Scanner in = new Scanner(System.in);
//        System.out.println("podaj id użytkownika: ");
//        int user_id =in.nextInt();
//    }

    public static void display_menu() {
        System.out.println("1) add – przypisywanie zadań do użytkowników \n2) view – przeglądanie rozwiązań danego użytkownika\n3) quit – zakończenie programu");
        System.out.println("Wybierz jedną z opcji: ");
    }

    private static void PrintAllExercise(ExerciseDao exerciseDao) {
        System.out.println("List of exercise: ");
        for (Exercise exercise : exerciseDao.findAll()) {
            System.out.println(exercise);
        }
        System.out.println();
    }

    public void printAll (UserDao userDao){
        System.out.println("List of users: ");
        for (User user : userDao.findAll()) {
            System.out.println(user);
        }
        System.out.println();
    }
}

