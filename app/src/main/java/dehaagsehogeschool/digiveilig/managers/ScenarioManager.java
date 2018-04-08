package dehaagsehogeschool.digiveilig.managers;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dehaagsehogeschool.digiveilig.ImageParser;
import dehaagsehogeschool.digiveilig.models.Scenario;

public class ScenarioManager {

    private String _source;
    private Context _context;

    public ScenarioManager(String source, Context context) {
        _source = source;
        _context = context;
    }


    public List<Scenario> getScenarios() {
        return parseScenarios();
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

    private ArrayList<Scenario> parseScenarios() {
        ArrayList<Scenario> scenarios = new ArrayList<>();
        XmlPullParser parser = createXmlParser();
        Scenario scenario = null;

        try {
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String eltName = null;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        eltName = parser.getName();

                        if (eltName.equals("scenario")) {
                            scenario = new Scenario();
                            scenarios.add(scenario);
                        } else if (scenario != null) {
                            if (eltName.equals("scenarioimage")) {
                                String image;
                                image = parser.nextText();
                                scenario.scenarioImage = ImageParser.getImageBasedOnFileName(image, _context);
                            } else if (eltName.equals("scenarioquestion")) {
                                scenario.question = parser.nextText();
                            } else if (eltName.equals("answer")) {
                                if (scenario.answers == null) {
                                    scenario.answers = new ArrayList<String>();
                                    scenario.answers.add(parser.nextText());
                                } else {
                                    scenario.answers.add(parser.nextText());
                                }
                            } else if (eltName.equals("rightanswer")) {
                                scenario.rightAnswer = parser.nextText();
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return scenarios;
    }
}
