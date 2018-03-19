package dehaagsehogeschool.cyberdemo.managers;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dehaagsehogeschool.cyberdemo.models.Question;

/**
 * Created by Thomas on 17-Mar-18.
 */

public class QuestionManager {

    private String _source;
    private Context _context;

    public QuestionManager(String source, Context context) {
        _source = source;
        _context = context;
    }

    public List<Question> getQuestions() {
        return parseQuestions();
    }

    private XmlPullParser createXmlParser() {
        XmlPullParser parser = null;

        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            parser = parserFactory.newPullParser();
            InputStream inputStream = _context.getAssets().open(_source);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parser;
    }

    private ArrayList<Question> parseQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        XmlPullParser parser = createXmlParser();
        Question question = null;

        try {
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String eltName = null;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        eltName = parser.getName();

                        if (eltName.equals("question")) {
                            question = new Question();
                            questions.add(question);
                        } else if (question != null) {
                            if (eltName.equals("quizquestion")) {
                                question.question = parser.nextText();
                            } else if (eltName.equals("answer")) {
                                if (question.answers == null) {
                                    question.answers = new ArrayList<String>();
                                    question.answers.add(parser.nextText());
                                } else {
                                    question.answers.add(parser.nextText());
                                }
                            } else if (eltName.equals("rightanswer")) {
                                question.rightAnswer = parser.nextText();
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return questions;
    }

}
