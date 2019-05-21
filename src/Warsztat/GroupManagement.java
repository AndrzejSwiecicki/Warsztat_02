package Warsztat;

import java.util.Scanner;

public class GroupManagement {
    public static void main(String[] args) {
        new GroupManagement();
    }
    public GroupManagement() {
        GroupDao groupDao = new GroupDao();
        PrintAllGroup(groupDao);
        Scanner in = new Scanner(System.in);
        int menuoption = 0;
        do {
            display_menu();
            menuoption =in.nextInt();
            in.nextLine();
            switch (menuoption) {
                case 1:
                    System.out.println("You picked option 1");
                    addGroup(groupDao);
                    PrintAllGroup(groupDao);
                    break;
                case 2:
                    System.out.println("You picked option 2");
                    editGroup(groupDao);
                    PrintAllGroup(groupDao);
                    break;
                case 3:
                    System.out.println("You picked option 3");
                    deleteGroup(groupDao);
                    PrintAllGroup(groupDao);
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
    private static void deleteGroup(GroupDao groupDao) {
        int id;
        Scanner scr = new Scanner ( System.in );
        System.out.println("Please insert id for delete exercise: ");
        id = scr.nextInt();
        groupDao.delete(id);
    }

    private static void editGroup(GroupDao groupDao) {

        int id;
        String name;

        Scanner scr = new Scanner(System.in);
        System.out.println("insert id: ");
        id = scr.nextInt();
        scr.nextLine();
        System.out.println("insert name: ");
        name = scr.nextLine();

        User_group user_group = new User_group(name);
        user_group.setId(id);
        groupDao.update(user_group);
    }

    private static void addGroup(GroupDao groupDao) {

        String name;

        Scanner scr = new Scanner(System.in);
        System.out.println("insert name: ");
        name = scr.nextLine();

        User_group user_group = new User_group(name);
        groupDao.create(user_group);
    }

    private static void PrintAllGroup(GroupDao groupDao) {
        System.out.println("List of exercise: ");
        for (User_group group : groupDao.findAll()) {
            System.out.println(group);
        }
        System.out.println();
    }

    public static void display_menu() {
        System.out.println("1) add – dodanie grupy \n2) edit – edycja grupy\n3) delete – usunięcie grupy\n4) quit – zakończenie programu");
        System.out.println("Wybierz jedną z opcji: ");
    }
}
