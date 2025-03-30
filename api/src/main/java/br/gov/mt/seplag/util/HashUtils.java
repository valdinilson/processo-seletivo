package br.gov.mt.seplag.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HashUtils {

    /**
     * Gera um hash com algoritmo especificado e tamanho truncado.
     *
     * @param input     Texto a ser hashado
     * @param algoritmo Algoritmo (ex: SHA-256, SHA-1)
     * @return Hash em hexadecimal
     */
    public static String gerarHash(String input, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hex = new StringBuilder();
            for (byte b : digest) {
                hex.append(String.format("%02x", b));
            }

            return hex.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo de hash não suportado: " + algoritmo, e);
        }
    }
}
