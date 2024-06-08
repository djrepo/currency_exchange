package tools;

import com.mongodb.BasicDBObject;
import org.bson.BSONObject;
import org.bson.BsonDocument;
import org.bson.Document;

public class Parser {

    String json = "{\n" +
            "  \"entryID\": \"1533723599042\",\n" +
            "  \"key\": {\n" +
            "    \"$numberLong\": \"1375301640029223812\"\n" +
            "  },\n" +
            "  \"bid\": {\n" +
            "    \"$numberInt\": \"1563910\"\n" +
            "  }\n" +
            "}";

    // Parse the Extended JSON to a BSON Document
    BasicDBObject bson = BasicDBObject.parse(json);
    String entryID = bson.toJson()
    ObjectMapper ObjectMapper = new ObjectMapper().
    get("entryID").toString();
/*
    Document doc = Document.parse(json);

    doc.get("key", BsonDocument.class);

    // Extract the fields from the BSON Document using constants
    String entryID = doc.getString("entryID");
    long key;

    {
        BsonDocument key1 = doc.get("key", BsonDocument.class);
        key1.toJson()
        key = key1.getInt64("$numberLong").getValue();
    }

    int bid = doc.get("bid", BsonDocument.class).getInt32("$numberInt").getValue();

    // Create the MyJsonObj instance
    MyJsonObj obj = new MyJsonObj(entryID, key, bid);*/
}
