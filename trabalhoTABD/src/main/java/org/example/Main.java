package org.example;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://root:root@cluster0.emot9.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");

        MongoDatabase database = mongoClient.getDatabase("btsShows");

        MongoCollection<Document> collection = database.getCollection("shows");

        Document show1 = new Document("show_id", 1)
                .append("location", "Seoul")
                .append("date", "2023-09-25")
                .append("available_tickets", 100000);
        collection.insertOne(show1);

        List<Document> shows = Arrays.asList(
                new Document("show_id", 2)
                        .append("location", "Tokyo")
                        .append("date", "2023-10-15")
                        .append("available_tickets", 50000),

                new Document("show_id", 3)
                        .append("location", "New York")
                        .append("date", "2023-11-05")
                        .append("available_tickets", 75000)
        );
        collection.insertMany(shows);

        collection.updateOne(
                new Document("show_id", 1),
                new Document("$set", new Document("available_tickets", 95000))
        );

        collection.deleteOne(new Document("show_id", 2));

        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }

        mongoClient.close();
    }
}