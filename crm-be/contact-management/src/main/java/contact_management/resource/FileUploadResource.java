//package contact_management.resource;
//
//import contact_management.service.FileUploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//@RestController
//@RequestMapping("api/file")
//public class FileUploadResource {
//    @Autowired
//    FileUploadService fileUploadService;
//
//    @GetMapping("/all/crm-base-entity/{crmBaseEntityId}")
//    public ResponseEntity<List<String>>getFilesForCrmBaseEntity(@PathVariable("crmBaseEntityId")Long id){
//        return new ResponseEntity<>(fileUploadService.getCrmBaseEntityFileNames(id),HttpStatus.OK);
//    }
//
//    @GetMapping("/get-absolute-path/{fileName}")
//    public ResponseEntity<java.net.URI>getAbsolutePath(@PathVariable("fileName") String fileName){
//        return new ResponseEntity<>(fileUploadService.getAbsolutePath(fileName),HttpStatus.OK);
//    }
//
//    @PostMapping("/upload/crm-base-entity/{id}")
//    public ResponseEntity<?>uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
//            fileUploadService.uploadFile(file,id);
//            return ResponseEntity.ok("file uploaded successfully");
//    }
//
//    @DeleteMapping("/{fileName}")
//    public ResponseEntity<?>deleteFile(@PathVariable("fileName")String fileName) throws IOException {
//        fileUploadService.deleteFile(fileName);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//}
