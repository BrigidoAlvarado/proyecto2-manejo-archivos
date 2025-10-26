package org.archivos.ecommercegt.services.utilities;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Data
@Service
public class ImageService {

    public static final String IMAGE_PATH = "images-server";

    private String safeFileName;

    public ImageService() {
        File dir = new File(IMAGE_PATH);
        if (!dir.exists()) {
            boolean result = dir.mkdir();
            if (!result) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can't create directory");
            }
        }
    }

    public String getImageUrl(MultipartFile file) {
            safeFileName = null;
            if (file.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
            }
            String originalName = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();
            safeFileName = UUID.randomUUID() + "_" + originalName.replaceAll("[^a-zA-Z0-9._-]", "_");
            return IMAGE_PATH + File.separator + safeFileName;
    }

    public void saveImage(MultipartFile file) {
        try{
            Path destinationPath = Paths.get(IMAGE_PATH).resolve(safeFileName).normalize().toAbsolutePath();
            file.transferTo(destinationPath.toFile());
        }catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving file", e);
        }
    }

    public byte[] getImage(String relativePath) {
        try {
            Path imagePath = Paths.get(relativePath).normalize().toAbsolutePath();

            if (!Files.exists(imagePath)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
            }

            return Files.readAllBytes(imagePath);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error reading image", e);
        }
    }

    public String getBase64Image(String relativePath) {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString( getImage(relativePath) );
    }

    public void deleteImage(String relativePath) {
        try {
            // Construye la ruta absoluta normalizada
            Path imagePath = Paths.get(relativePath).normalize().toAbsolutePath();

            // Verifica si el archivo existe antes de eliminarlo
            if (!Files.exists(imagePath)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
            }

            // Elimina el archivo
            Files.delete(imagePath);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting image", e);
        }
    }

    public void validateImage(MultipartFile imageFile) {
        if (imageFile == null || imageFile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo guardar la imagen o no se encontró");
        }

        if (imageFile.getSize() > 5 * 1024 * 1024) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La imagen excede el tamaño permitido (5 MB)");
        }

    }
}