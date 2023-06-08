package contact_management.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService{
    void uploadFile(MultipartFile file,Long crmBaseEntityId);
    void deleteFile(String fileName) throws IOException;

    List<String>getCrmBaseEntityFileNames(Long id);

    java.net.URI getAbsolutePath(String filename);

    int countFiles();
}
