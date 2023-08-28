package contact_management.service.impl;

import contact_management.entity.CrmBaseEntity;
import contact_management.entity.File;
import contact_management.enumeration.AllowedFileTypes;
import contact_management.repository.CrmBaseEntityRepository;
import contact_management.repository.FileRepository;
import contact_management.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private CrmBaseEntityRepository crmBaseEntityRepository;
    @Value("${file-upload-path}")
    private String root;

    public void saveFilePathInDatabase(MultipartFile file, Long crmBaseEntityId) {
        CrmBaseEntity crmBaseEntity = crmBaseEntityRepository.findById(crmBaseEntityId).orElseThrow(() -> new EntityNotFoundException());
        fileRepository.save(new File(null, file.getOriginalFilename().toString(), crmBaseEntity));

    }


    @Override
    @Transactional
    public void uploadFile(MultipartFile file, Long crmBaseEntityId) {
        try {
            if (!checkIfFileAlreadyExists(file)) {
                checkFileType(file);
                Files.copy(file.getInputStream(), Paths.get(this.root).resolve(file.getOriginalFilename()));
                this.saveFilePathInDatabase(file, crmBaseEntityId);
                fileRepository.countFiles();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfFileAlreadyExists(MultipartFile file) {
        return fileRepository.existsByPath(file.getOriginalFilename());
    }

    @Override
    @Transactional
    public void deleteFile(String fileName) throws IOException {
        Path file = Paths.get(this.root).resolve(fileName);
        if (!Files.deleteIfExists(file)) {
            throw new IOException("file does not exist");
        }
        File fileEntity = fileRepository.findFileByPath(fileName).orElseThrow(() -> new EntityNotFoundException("file does not exist"));
        fileRepository.delete(fileEntity);
        fileRepository.countFiles();
    }

    @Override
    public List<String> getCrmBaseEntityFileNames(Long id) {
        return fileRepository.findAllByCrmBaseEntity(id);
    }

    public static void checkFileType(MultipartFile file) throws Exception {
        String[] splittedFileName = file.getOriginalFilename().split("\\.");
        if (splittedFileName.length != 2) throw new Exception("unsupported file extension");
        String fileType = splittedFileName[1];
        boolean isAllowedType = false;
        for (AllowedFileTypes allowedFileType : AllowedFileTypes.values()) {
            if (fileType.equalsIgnoreCase(allowedFileType.toString())) {
                isAllowedType = true;
            }
        }
        if (!isAllowedType) throw new Exception("not allowed type");

    }


    public java.net.URI getAbsolutePath(String filename) {
        Path file = Paths.get(this.root).resolve(filename);
        return file.toUri();
    }

    @Override
    @Cacheable(value = "countFiles")
    public int countFiles() {
        return fileRepository.countFiles();
    }
}
