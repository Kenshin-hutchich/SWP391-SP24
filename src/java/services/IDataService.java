/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import jakarta.servlet.http.Part;

/**
 *
 * @author KENSHIN
 */
public interface IDataService {
    public Integer parseInt(String intString) throws NumberFormatException;
    public double parseDouble(String doubleString) throws NumberFormatException;
    public String saveImage(Part imageFile, String appPath, String directoryPath);
    public Boolean parseBoolean(String booleanString);
}
