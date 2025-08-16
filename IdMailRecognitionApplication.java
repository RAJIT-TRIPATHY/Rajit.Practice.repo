import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class IdMailRecognitionApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdMailRecognitionApplication.class, args);
    }
}

@Document(collection = "recognized_ids")
class RecognizedId {
    @Id
    private String id;
    private String email;
    private String name;
    private String type; // "office" or "school"
    private String recognizedAt;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getRecognizedAt() { return recognizedAt; }
    public void setRecognizedAt(String recognizedAt) { this.recognizedAt = recognizedAt; }
}

interface RecognizedIdRepository extends MongoRepository<RecognizedId, String> {
    List<RecognizedId> findByType(String type);
    Optional<RecognizedId> findByEmail(String email);
}

@RestController
@RequestMapping("/api/ids")
class IdRecognitionController {

    @Autowired
    private RecognizedIdRepository repository;

    // Recognize and store a new ID
    @PostMapping("/recognize")
    public RecognizedId recognizeId(@RequestBody RecognizedId id) {
        // Here you would add logic to recognize/validate the ID mail
        // For now, just save to MongoDB
        return repository.save(id);
    }

    // Get all recognized IDs
    @GetMapping
    public List<RecognizedId> getAllIds() {
        return repository.findAll();
    }

    // Get recognized IDs by type (office/school)
    @GetMapping("/type/{type}")
    public List<RecognizedId> getIdsByType(@PathVariable String type) {
        return repository.findByType(type);
    }

    // Get recognized ID by email
    @GetMapping("/email/{email}")
    public RecognizedId getIdByEmail(@PathVariable String email) {
        return repository.findByEmail(email).orElse(null);
    }
}

