package stream.lexlab.simplecms.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stream.lexlab.simplecms.models.FileRecord;
import stream.lexlab.simplecms.service.FileService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileRecord>> listFiles() throws IOException {
        return ResponseEntity.ok(fileService.listFiles());
    }

    @PostMapping
    public ResponseEntity<List<FileRecord>> uploadMultiple(@RequestParam("files") MultipartFile[] files) throws IOException {
        fileService.upload(files);
        return ResponseEntity.ok(fileService.listFiles());
    }

    @DeleteMapping
    public ResponseEntity deleteFiles(@RequestBody List<String> files){
        return ResponseEntity.ok(fileService.deleteFiles(files));
    }
}
