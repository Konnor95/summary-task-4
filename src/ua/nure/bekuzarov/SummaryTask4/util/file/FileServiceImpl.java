package ua.nure.bekuzarov.SummaryTask4.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.exception.FileException;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Provides realization of {@link FileService} interface.
 */
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
    private final String folderPath;

    /**
     * @param folderPath root folder for looking files up
     */
    public FileServiceImpl(String folderPath) {
        this.folderPath = folderPath + File.separator;
        File fileSaveDir = new File(folderPath);
        if (!fileSaveDir.exists() && !fileSaveDir.mkdirs()) {
            LOGGER.warn("Folder wasn't create");
            throw new FileException("Unexpected result when creating folder");
        }
    }

    @Override
    public String saveFile(Integer name, String subDirectory, Part file) {
        return saveFile("" + name, subDirectory, file);
    }

    @Override
    public String saveFile(String name, String subDirectory, Part file) {
        String extension = getExtension(file);
        String fileName = generateFileName(name, extension);
        String path = folderPath + subDirectory + fileName;
        writeFileOnDisk(file, path);
        LOGGER.debug("File saved to {} ", path);
        return fileName;
    }

    @Override
    public File getFile(String path) {
        return new File(folderPath + path);
    }

    @Override
    public void removeFile(String name, String subDirectory) {
        if (name == null || name.isEmpty()) {
            return;
        }
        String path = folderPath + subDirectory + name;
        removeFile(path);
    }

    @Override
    public void removeFiles(List<String> names, String subDirectory) {
        for (String name : names) {
            removeFile(name, subDirectory);
        }
    }

    @Override
    public String getExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return null;
        }
        try {
            return fileName.substring(index + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new FileException("Cannot get file extension", e);
        }
    }

    private String getExtension(Part file) {
        String fileName = getFileName(file);
        return getExtension(fileName);
    }

    private String getFileName(Part filePart) {
        String header = filePart.getHeader("content-disposition");
        if (header == null) {
            return null;
        }
        for (String headerPart : header.split(";")) {
            if (headerPart.trim().startsWith("filename")) {
                return headerPart.substring(headerPart.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    private void writeFileOnDisk(Part file, String filePath) {
        try {
            file.write(filePath);
        } catch (IOException e) {
            LOGGER.error("File cannot be written on disk", e);
            throw new FileException("File cannot be save", e);
        }
    }

    private String generateFileName(String name, String extension) {
        return name + "." + extension;
    }

    private void removeFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            LOGGER.debug("File deleted from {} ", path);
        } else {
            LOGGER.error("File deletion failed. Path: {}", path);
        }
    }

}
