package Warsztat;

import java.util.Scanner;

public class UserManagement {
    UserDao userDao = new UserDao();


    public UserManagement() {

        Scanner in = new Scanner(System.in);
        int menuOption = 0;
        do {
            display_menu();
            menuOption =in.nextInt();
            in.nextLine();
            switch (menuOption) {
                case 1:
                    System.out.println("You picked option 1");
                    addUser(userDao);
                    break;
                case 2:
                    System.out.println("You picked option 2");
                    editUser(userDao);
                    break;
                case 3:
                    System.out.println("You picked option 3");
                    deleteUser(userDao);
                    break;
                case 4:
                    System.out.println("You picked option 4");
                    System.err.println("You picked quit, bye !");
                    break;
                default:
                    System.err.println("Unrecognized option");
                    break;
            }
        }while(menuOption != 4);
    }
    public void display_menu() {
        printAll(userDao);
        System.out.println("1) add – dodanie użytkownika \n2) edit – edycja użytkownika\n3) delete – usunięcie użytkownika\n4) quit – zakończenie programu");
        System.out.println("Wybierz jedną z opcji: ");
    }

    private void deleteUser(UserDao userDao) {
        int id;
        Scanner scr = new Scanner (System.in);
        System.out.println("Please insert id for delete: ");
        id = scr.nextInt();
        scr.nextLine();
        userDao.delete(id);
    }

    private void editUser(UserDao userDao) {

        Scanner scr = new Scanner ( System.in );

        System.out.println("insert id: ");
        int id = scr.nextInt();
        scr.nextLine();
        System.out.println("insert name: ");
        String userName = scr.nextLine();
        System.out.println("insert email: ");
        String email = scr.nextLine();
        System.out.println("insert password: ");
        String password = scr.nextLine();
        System.out.println("insert group_id: ");
        int group_id = scr.nextInt();
        scr.nextLine();
        User user = new User(userName,email,password,group_id);
        user.setId(id);
        userDao.update(user);
    }

    private void addUser(UserDao userDao) {

        String userName;
        String email;
        String password;
        int group_id;

        Scanner scr = new Scanner ( System.in );
        System.out.println("insert name: ");
        userName = scr.nextLine();
        System.out.println("insert email: ");
        email = scr.nextLine();
        System.out.println("insert password: ");
        password = scr.nextLine();
        System.out.println("insert group_id: ");
        group_id = scr.nextInt();

        User user = new User(userName,email,password,group_id);
        userDao.create(user);
    }

    public static void main ( String[] args ) {
        new UserManagement();
    }

    public void printAll (UserDao userDao){
        System.out.println("List of users: ");
        for (User user : userDao.findAll()) {
            System.out.println(user);
        }
        System.out.println();
    }
}

