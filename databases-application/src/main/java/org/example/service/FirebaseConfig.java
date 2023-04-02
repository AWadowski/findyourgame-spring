package org.example.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

//@Configuration
public class FirebaseConfig {

/*    private Storage storage;
    private String defaultBucketName;

    @PostConstruct
    public void initialize() {
        try {
            Resource resource = new ClassPathResource("key.json");
            InputStream serviceAccount = resource.getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
            storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build()
                    .getService();
            defaultBucketName = options.getStorageBucket();
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize Firebase", e);
        }
    }
    public Storage getStorage() {
        return storage;
    }
    public String getDefaultBucketName() {
        return defaultBucketName;
    }*/
}

