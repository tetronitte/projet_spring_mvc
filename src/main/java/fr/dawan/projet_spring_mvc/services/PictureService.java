package fr.dawan.projet_spring_mvc.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PictureService {

    public void savePicture(MultipartFile picture) throws IOException {
        byte[] bytes = picture.getBytes();
        Path path = Paths.get("./pictures/" + picture.getOriginalFilename());
        Files.write(path, bytes);
    }

    public Resource loadPicture(String picture) throws MalformedURLException {
        Path filePath = Paths.get("pictures", picture);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        return null;
    }
}
