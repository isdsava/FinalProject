package au.com.fintechapps.javajoshingredux;

public class Joshing {

    public String joshOn(int args) {

        switch (args) {
            case 1:
                return "Horse walks into bar. Barman say's why the long face. Horse says I'm a gelding.";

            case 2:
                return "A vicar and a rabbi and a mufti walk into a bar. Worlds problems are now solved thanks to tequila";
        }
            return "Yikes ";
    }

    public String[][] meJokes(){
        String[][] passMe = new String[3][2];

        passMe[0][0] = "Horse walks into bar. Barman say's why the long face. ";
        passMe[0][1] = "Horse says I'm a gelding.";
        passMe[1][0] = "A vicar and a rabbi and a mufti walk into a bar";
        passMe[1][1] = "Worlds problems are now solved thanks to tequila";
        passMe[2][0] = "Man – Haven’t I seen you someplace before?";
        passMe[2][1] = "Woman - Yes, that's why I don't go there anymore";

        return passMe;
    }

}
