import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import instruments.Calculator;
import instruments.Operation;
import instruments.Translator;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Decorator {
    private Translator translator;
    private Calculator calculator;


    public Decorator() {
        translator = new Translator();
        calculator = new Calculator();
    }

    public void solveExpression(String expression) {
        try {
            List<Operation> polskBack = translator.translate(expression);
            String result = calculator.calculateExpression(polskBack);
            writeToMongo(expression, polskBack.stream().map((s) -> s.toString()).collect(Collectors.toList()), result);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void writeToMongo(String expression, List<String> polskBack, String result) {
        try (MongoClient mongoClient = MongoClients.create()) {
            for (String names:
                    mongoClient.listDatabaseNames()) {
                System.out.println("DB name: " + mongoClient.listDatabaseNames());
            }
            MongoDatabase db = mongoClient.getDatabase("calculations");
            MongoCollection col = db.getCollection("calcs");
            HashMap map = new HashMap();
            map.put("_id", new ObjectId());
            map.put("polsk_back", polskBack);
            map.put("expression",  expression);
            map.put("result", result);

            col.insertOne(new Document(map));

        }
    }
}
