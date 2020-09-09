package m3;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminExample {
    public static void main(String[] args) throws Exception {

        Admin admin = Admin.create(
                Map.of("bootstrap.servers", "localhost:9092")
        );

        printAllTopics(admin);
        // TODO: create a 'booking-created' topic
        NewTopic newTopic = new NewTopic("quote-feedback", Optional.empty(), Optional.empty());
        admin.createTopics(List.of(newTopic))
                .all()
                .get();
        printAllTopics(admin);
    }

    static void printAllTopics(Admin client) throws Exception {
        var topics = client.listTopics().names().get();
        System.out.println("Topics in the cluster:");
        topics.forEach(System.out::println);
        System.out.println();
    }
}
