/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;
import java.io.IOException;

/**
 *
 * @author KENSHIN
 */
public interface IYoutubeService {
    public String uploadVideo(String videoFilePath, String videoTitle) throws IOException;
}
