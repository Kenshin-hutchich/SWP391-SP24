/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KENSHIN
 */
public class DataService implements IDataService {

    private String getFileExtension(Part part) {
        String contentDispositionHeader = part.getHeader("content-disposition");
        String[] elements = contentDispositionHeader.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.lastIndexOf('.') + 1, element.length() - 1);
            }
        }
        return "";
    }

    @Override
    public String saveImage(Part imageFile, String appPath, String directoryPath) {
        if (imageFile != null && imageFile.getSize() > 0) {
            // Đường dẫn để lưu trữ avatar trên server
            String avatarPath = appPath + directoryPath;

            // Tạo thư mục nếu chưa tồn tại
            File avatarDir = new File(avatarPath);
            if (!avatarDir.exists()) {
                avatarDir.mkdir();
            }

            // Tạo tên file ngẫu nhiên
            String fileName = UUID.randomUUID().toString() + "." + getFileExtension(imageFile);

            // Đường dẫn lưu trữ trên server
            String filePath = avatarPath + File.separator + fileName;

            // Ghi file vào thư mục
            try ( InputStream inputStream = imageFile.getInputStream();  OutputStream outputStream = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException ex) {
                Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Cập nhật đường dẫn file vào cơ sở dữ liệu của bạn
            String avatarUrl = directoryPath + fileName;
            return avatarUrl;
        } else {
            return "";
        }
    }

    @Override
    public Integer parseInt(String intString) throws NumberFormatException {
        return Integer.parseInt(intString);
    }

    @Override
    public double parseDouble(String doubleString) throws NumberFormatException {
        return Double.parseDouble(doubleString);
    }

    @Override
    public Boolean parseBoolean(String booleanString) {
        String lowercaseString = booleanString.toLowerCase();
        if (null == lowercaseString) {
            return null;
        } else {
            switch (lowercaseString) {
                case "true":
                    return true;
                case "false":
                    return false;
                default:
                    return null;
            }
        }
    }
}
