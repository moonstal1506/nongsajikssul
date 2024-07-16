package com.nongsa.agriculture.service;

import com.nongsa.agriculture.model.Document;
import com.nongsa.agriculture.repository.DocumentRepository;
import com.nongsa.shop.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {

    @Value("${documentLocation}")
    private String documentLocation;

    private final DocumentRepository documentRepository;

    private final FileService fileService;

    public void saveDocument(Document document, MultipartFile documentFile) throws Exception {
        String oriFileName = documentFile.getOriginalFilename();
        String fileName = "";
        String fileUrl = "";

        //파일 업로드
        if (!StringUtils.isEmpty(oriFileName)) {
            fileName = fileService.uploadFile(documentLocation, oriFileName,
                    documentFile.getBytes());
            fileUrl = "/document/" + fileName;
        }

        document.updateDocument(oriFileName, fileName, fileUrl);
        documentRepository.save(document);
    }

    public void deleteDocument(Long documentId) throws Exception {
            Document savedDocument = documentRepository.findById(documentId)
                    .orElseThrow(EntityNotFoundException::new);

            if (!StringUtils.isEmpty(savedDocument.getFileName())) {
                fileService.deleteFile(documentLocation + "/" + savedDocument.getFileName());
            }

            documentRepository.deleteById(documentId);
    }

    public void updateDocument(Long documentId, MultipartFile documentFile) throws Exception {
        if (!documentFile.isEmpty()) {
            Document savedDocument = documentRepository.findById(documentId)
                    .orElseThrow(EntityNotFoundException::new);

            if (!StringUtils.isEmpty(savedDocument.getFileName())) {
                fileService.deleteFile(documentLocation + "/" + savedDocument.getFileName());
            }

            String oriFileName = documentFile.getOriginalFilename();
            String fileName = fileService.uploadFile(documentLocation, oriFileName, documentFile.getBytes());
            String fileUrl = "/document/" + fileName;
            savedDocument.updateDocument(oriFileName, fileName, fileUrl);
        }
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}
