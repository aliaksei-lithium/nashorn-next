package by.lithium;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class NashornRunner {
    static NashornScriptEngine nashornScriptEngine = setupNashornScriptEngine();

    public static void main(String[] args) {
        try {
            Object result = nashornScriptEngine.invokeFunction("test");
            System.out.printf(result.toString());
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static NashornScriptEngine setupNashornScriptEngine() throws RuntimeException {
        NashornScriptEngine nashornScriptEngine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        try {
            nashornScriptEngine.eval(read("test.js"));
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
        return nashornScriptEngine;
    }

    private static Reader read(String path) {
        InputStream in = NashornRunner.class.getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }

}
