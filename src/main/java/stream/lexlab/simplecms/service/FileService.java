package stream.lexlab.simplecms.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stream.lexlab.simplecms.models.FileRecord;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService {
    @Value("${app.upload-dir}")
    private Path uploadDir;

    @PostConstruct
    void prepareFolder() throws IOException {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
    }

    public List<FileRecord> listFiles() throws IOException {
        return Files.walk(uploadDir, 1)
                .filter(Files::isRegularFile)
                .map(path -> {
                    try {
                        long size = Files.size(path);
                        var created = Files.getLastModifiedTime(path).toString();
                        return new FileRecord(path.getFileName().toString(), size, created);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    public void upload(MultipartFile... files) throws IOException {
        for (MultipartFile file : files) {
                Path filePath = uploadDir.resolve(file.getOriginalFilename());
                try(var is = file.getInputStream(); var os = new FileOutputStream(filePath.toFile())){
                    is.transferTo(os);
                }
        }
    }

    public List<String> deleteFiles(List<String> files) {
        return files.stream().filter(file->{
            Path path = uploadDir.resolve(Path.of(file));
            try {
                Files.delete(path);
                return true;
            } catch (IOException e) {
                return false;
            }
        }).toList();

    }
}
