package u.com.example.quiz;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class QB {

    List <Question> list = new ArrayList<>();
    DBWorldCup myDataBaseHelper;
    public int getLength(){
        return list.size();
    }
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num-1);
    }
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }



    public void initQuestions(Context context) {
        myDataBaseHelper = new DBWorldCup(context);
        list = myDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialQuestion(new Question("Which African country was the first to compete in a Football World Cup?",
                    new String[]{"Cameroon", "Eygpt", "South Africa", "Tunisia"}, "Eygpt"));
            myDataBaseHelper.addInitialQuestion(new Question("Which country won most World cups?",
                    new String[]{"Argentina", "Brazil", "Germany", "Italy"}, "Brazil"));
            myDataBaseHelper.addInitialQuestion(new Question("Where was the first World cup held?",
                    new String[]{"Argentina", "Brazil", "England", "Uruguay"}, "Uruguay"));
            myDataBaseHelper.addInitialQuestion(new Question("World cup was never held in one of these continents",
                    new String[]{"Africa", "Asia", "Australia/Oceania", "North America"}, "Australia/Oceania"));
            myDataBaseHelper.addInitialQuestion(new Question("One of these South American teams never became a world champion",
                    new String[]{"Brazil", "Argentina", "Chile", "Uruguay"}, "Chile"));
            list = myDataBaseHelper.getAllQuestionsList();
        }
    }

}