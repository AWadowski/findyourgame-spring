package org.example.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@AllArgsConstructor
public class FireBaseService {

    private Firestore db;
    private CollectionReference lista;


    public FireBaseService() throws IOException {
        File file = new File(
                getClass().getClassLoader().getResource("key.json").getFile()
        );

        FileInputStream fis = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(fis))
                .setDatabaseUrl("https://console.firebase.google.com/u/0/project/databases-4370c")
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
        lista = db.collection("Gry");
        lista.get();
    }
}

