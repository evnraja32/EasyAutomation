package examples.script_encryption;

import javax.script.*;

public class ScriptReader {

    public static void main(String[] args) {
        // Creating script engine
        ScriptEngine ee = new ScriptEngineManager().getEngineByName("javascript");
        String cardNumber = "4761360074185165";
        String cvv = "797";
        String nameOnCard = "Raja Evn";
        String expiryYear = "12";
        String expiryMonth = "2023";

        try {
            // Evaluating Nashorn code
            ee.eval("cardNumber=" + cardNumber);
            ee.eval("cvv=" + cvv);
            ee.eval("nameOnCard=" + nameOnCard);
            ee.eval("expiryYear=" + expiryYear);
            ee.eval("expiryMonth=" + expiryMonth);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }
}
