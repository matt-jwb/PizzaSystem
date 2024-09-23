package com.example.assignment2springboot.util;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Component
public class StringHasher {
    public String hashString (String stringToHash) {
        try{
            byte[] salt = {
                    Byte.parseByte("10"),
                    Byte.parseByte("-93"),
                    Byte.parseByte("-102"),
                    Byte.parseByte("98"),
                    Byte.parseByte("77"),
                    Byte.parseByte("-7"),
                    Byte.parseByte("39"),
                    Byte.parseByte("91"),
                    Byte.parseByte("-98"),
                    Byte.parseByte("56"),
                    Byte.parseByte("-19"),
                    Byte.parseByte("-116"),
                    Byte.parseByte("81"),
                    Byte.parseByte("22"),
                    Byte.parseByte("-21"),
                    Byte.parseByte("21")
            };

            KeySpec spec = new PBEKeySpec(stringToHash.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            return String.format("%x", new BigInteger(hash));
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new RuntimeException("String hashing failed");
        }
    }
}
