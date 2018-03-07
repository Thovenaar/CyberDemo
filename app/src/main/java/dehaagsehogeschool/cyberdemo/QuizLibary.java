package dehaagsehogeschool.cyberdemo;

/**
 * Created by Tony on 2/17/2018.
 */

public class QuizLibary {

    //Alle vragen voor het Quiz spel worden hier gedefinieerd
    public String quizQuestions[] = {
            "Welke van de onderstaande wachtwoorden zijn veilig om te gebruiken?",

            "Voorbeeld vraag 2.",

            "Voorbeeld vraag 3",

            "Vraag 4...",

            "Vraag 5...",

            "Vraag 6...",

            "Vraag 7...",

            "Vraag 8...",

            "Vraag 8..."
    };

    //Alle antwoord mogelijkheden worden hier gedefinieerd
    private String quizChoices[][] = {
            {"123456", "jan1992", "Ihdsk77Se@", "ditraadjenooit"},

            {"Antwoord 1", "Antwoord 2", "Antwoord 3", "Antwoord 4"},

            {"Antwoord 1", "Antwoord 2", "Antwoord 3", "Antwoord 4"},

            {"123456", "jan1992", "Ihdsk77Se@", "Antwoord 4"},

            {"Antwoord 1", "Antwoord 5", "Antwoord 3", "Antwoord 4"},

            {"Antwoord 1", "Antwoord 6", "Antwoord 3", "Antwoord 4"},

            {"123456", "jan1992", "Antwoord 7", "ditraadjenooit"},

            {"Antwoord 1", "Antwoord 2", "Antwoord 8", "Antwoord 4"},

            {"Antwoord 1", "Antwoord 2", "Antwoord 9", "Antwoord 4"},
    };

    //Alle antwoorden worden hier gedefinieerd
    private String quizAnswers[] = {
            "Ihdsk77Se@",

            "Antwoord 2",

            "Antwoord 3",

            "Antwoord 4",

            "Antwoord 5",

            "Antwoord 6",

            "Antwoord 7",

            "Antwoord 8",

            "Antwoord 9"

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
