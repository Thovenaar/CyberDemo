package dehaagsehogeschool.cyberdemo;

/**
 * Created by Tony on 2/17/2018.
 */

public class QuizQuestions {

    //Alle vragen voor het Quiz spel worden hier gedefinieerd
    private String quizQuestions[] = {
            "Welke van de onderstaande wachtwoorden zijn veilig?",
            "Ik test er 2",
            "Ik test er 3",
            "Ik test er 4",
            "Ik test er 5",
            "Ik test er 6",
            "Ik test er 7",
            "Ik test er 8",
    };

    //Alle antwoord mogelijkheden worden hier gedefinieerd
    private String quizChoices[][] = {
            {"123456", "jan1992", "Ihdsk77Se@", "ditraadjenooit"},
            {"lol10", "lol20", "lol30", "lol40"},
            {"lol100", "lol200", "lol300", "lol400"},
    };

    //Alle antwoorden worden hier gedefinieerd
    private String quizAnswers[] = {
            "Ihdsk77Se@", "lol20", "lol300"

    };

    //Haal vragen op uit quizQuestions array. Begin bij 0 ...
    public String getQuestion(int a) {
        String questions = quizQuestions[a];
        return questions;

    }

    public String getAnswer(int a){
        String answer = quizAnswers[a];
        return answer;

    }

    //Haal de verschillende antwoord mogelijkheden op. Begin bij 0...
    public String getChoice1(int a) {
        String choice1 = quizChoices[a][0];
        return choice1;
    }

    public String getChoice2(int a) {
        String choice2 = quizChoices[a][1];
        return choice2;
    }

    public String getChoice3(int a) {
        String choice3 = quizChoices[a][2];
        return choice3;
    }

    public String getChoice4(int a) {
        String choice4 = quizChoices[a][3];
        return choice4;
    }
}
