/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

/**
 *
 * @author KENSHIN
 */
public interface IMailService {

    public void SendConfirmationCode(String email, String code) throws MessagingException, UnsupportedEncodingException;
    
    public void SendPassword(String email, String pasw) throws MessagingException, UnsupportedEncodingException;
}
