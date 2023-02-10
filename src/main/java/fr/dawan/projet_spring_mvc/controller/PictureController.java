package fr.dawan.projet_spring_mvc.controller;

import fr.dawan.projet_spring_mvc.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@Controller
public class PictureController {

    @Autowired
    PictureService pictureService;

    @GetMapping("/pictures/{picture:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String picture) throws MalformedURLException {
        Resource file = pictureService.loadPicture(picture);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; picture=\""+file.getFilename()+"\"").body(file);
    }
}
