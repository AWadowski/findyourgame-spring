package org.example.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FirebaseUploader {
    private final FirebaseConfig firebaseConfig;

    public FirebaseUploader(FirebaseConfig firebaseConfig) {
        this.firebaseConfig = firebaseConfig;
    }

    public void uploadImageAndStoreMetadata(Path imagePath) throws IOException {
        // Upload the .jpg file to Firebase Storage
        String fileName = UUID.randomUUID().toString() + ".jpg";
        BlobInfo blobInfo = BlobInfo.newBuilder(firebaseConfig.getDefaultBucketName(), fileName)
                .setContentType("image/jpeg").build();
        try (InputStream inputStream = Files.newInputStream(imagePath)) {
            Blob blob = firebaseConfig.getStorage().get(firebaseConfig.getDefaultBucketName())
                    .create(String.valueOf(blobInfo), inputStream);
        }

        // Get the download URL of the uploaded file
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        String downloadUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                firebaseConfig.getDefaultBucketName(), encodedFileName);

        // Store the metadata and download URL in the Firebase Realtime Database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("images");
        String key = ref.push().getKey();
        Map<String, Object> data = new HashMap<>();
        data.put("fileName", fileName);
        data.put("contentType", "image/jpeg");
        data.put("downloadUrl", downloadUrl);
        ref.child(key).setValueAsync(data);
    }
}
