package zero.core;

import zero.utils.*;

/**
 * Created by Bonz-pc on 2/24/14.
 */

public class Environment {
    Preference pref;

    public Environment() {
        pref = XMLParser.pullPreference("src/zero/data/preferences.xml");
    }

    public static void main(String[] args) {
        Environment env = new Environment();
        System.out.println(env.pref);
    }

}
