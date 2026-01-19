package com.montenegro.technova.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {

  private final Path storagePath = Paths.get("uploads");

  public FileStorageServiceImpl() {
    try {
      if (!Files.exists(storagePath)) {
        Files.createDirectories(storagePath);
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not create upload folder", e);
    }
  }

  @Override
  public String saveFile(MultipartFile file) {
    if (file == null || file.isEmpty()) {
      return null;
    }

    String extension = getExtension(file.getOriginalFilename());
    String fileName = UUID.randomUUID() + extension;

    try {
      Path target = storagePath.resolve(fileName);
      Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
      return fileName;
    } catch (IOException e) {
      throw new RuntimeException("Could not save file", e);
    }
  }

  @Override
  public void deleteFile(String fileName) {
    if (fileName == null) return;

    try {
      Path filePath = storagePath.resolve(fileName);
      Files.deleteIfExists(filePath);
    } catch (IOException e) {
      log.error("Could not delete file: " + fileName);
    }
  }

  private String getExtension(String fileName) {
    if (fileName == null || !fileName.contains(".")) {
      return "";
    }
    return fileName.substring(fileName.lastIndexOf("."));
  }
}
