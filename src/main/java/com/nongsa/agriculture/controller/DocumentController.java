package com.nongsa.agriculture.controller;

import com.nongsa.agriculture.model.Document;
import com.nongsa.agriculture.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping(value = "/admin")
@Controller
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @Value("${documentLocation}")
    private String documentLocation;

    @GetMapping("/documents")
    public String listUploadedFiles(Model model) {
        List<Document> documents = documentService.getAllDocuments();
        model.addAttribute("documents", documents);
        return "agriculture/chatAdmin";
    }

    @PostMapping("/uploadDocument")
    public String uploadDocument(@RequestParam("documentFile") MultipartFile documentFile, Model model) throws Exception {
        Document document = new Document();
        documentService.saveDocument(document, documentFile);
        return "redirect:/admin/documents";
    }

    @GetMapping("/downloadDocument/{fileName:.+}/{oriFileName}")
    @ResponseBody
    public ResponseEntity<Resource> downloadDocument(@PathVariable String fileName, @PathVariable String oriFileName) {
        try {
            Path filePath = Paths.get(documentLocation).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                String encodedFileName = URLEncoder.encode(oriFileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
                        .body(resource);
            } else {
                throw new RuntimeException("Document not found " + fileName);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Document not found " + fileName, ex);
        }
    }

    @GetMapping("/deleteDocument/{documentId}")
    public String deleteDocument(@PathVariable Long documentId) throws Exception {
        documentService.deleteDocument(documentId);
        return "redirect:/admin/documents";
    }
}