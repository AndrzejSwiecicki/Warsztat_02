package Warszat;

import java.util.Arrays;

public class app {
    public static void main(String[] args) {
        User user = new User("marysia","marysia@gmail.com","haslo",2);

        UserDao modyfikacja = new UserDao();
        Exercise exercise =new Exercise();

        ExerciseDao exerciseDao = new ExerciseDao();

   //     User userToDb = modyfikacja.create(user);
        // User_group grupa_1 = new User_group("GRUPA PIERWSZA");

     // GroupDao daoG = new GroupDao();

     // daoG.delete(1);

      //  SolutionDao sol = new SolutionDao();
//      User_group group = new User_group();
//      GroupDao gruoD = new GroupDao();

        System.out.println(Arrays.toString(modyfikacja.findAllByGroupId(2)));


    }
}
