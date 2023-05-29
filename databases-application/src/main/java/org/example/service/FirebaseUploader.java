package org.example.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FirebaseUploader {

    private final DatabaseReference ref;

    public FirebaseUploader() {
        FirebaseApp app = FirebaseApp.getInstance();
        ref = FirebaseDatabase.getInstance(app).getReference("/some/path");
    }

    public void writeData(Object data) {
        ref.setValueAsync(data);
    }

    public void readData() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object data = dataSnapshot.getValue();
                // Do something with the data
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                log.info("Failed to read value {}.", error.toException());
            }
        });
    }

    public void readAllData() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    System.out.println(ds.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }

}
