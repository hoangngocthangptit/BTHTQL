package com.example.javaapi.until;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFile {
    public static void saveFiles(String fileName, MultipartFile multipartFile) throws IOException {
        String uploadDir="D:\\cham_cong\\HTTQL\\QLNSWEB\\BackEnd\\src\\main\\java\\img";
        Path uploadDirectory = Paths.get(uploadDir);

        if(!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }
        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadDirectory.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        catch (IOException e) {
            throw new IOException("Could not save file: "+fileName, e);
        }
    }
}
