/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author KENSHIN
 */
public interface IHashService {
    public String HashPassword(String rawPassword);
    public boolean Verify(String inputPassword, String hash);
    public String GenerateRandomString(int length);
}
