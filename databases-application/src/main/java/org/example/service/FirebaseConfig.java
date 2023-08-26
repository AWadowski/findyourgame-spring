package org.example.service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import org.example.FireBaseApi;
import org.example.GameApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.path}")
    private String firebaseConfigPath;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        InputStream refreshToken = getClass().getResourceAsStream("/key.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://databases-3fd9e.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);

        return FirebaseApp.getInstance();
    }

    public List<GameApi> getFromFireBase(){
        List<GameApi> gameApiList = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    GameApi gameApi = ds.getValue(GameApi.class);
                    gameApiList.add(gameApi);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
        return gameApiList;
    }

    public void addToFirebase(FireBaseApi gameApi) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference gamesCollection = firestore.collection("games");

        ApiFuture<DocumentReference> future = gamesCollection.add(gameApi);

        try {
            DocumentReference documentReference = future.get();
            System.out.println("Game added to Firestore successfully at: " + documentReference.getPath());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error adding game to Firestore: " + e.getMessage());
        }
    }
}
