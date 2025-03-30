package br.gov.mt.seplag.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

/**
 * Utilitário para padronizar respostas HTTP baseadas em Page.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtil {

    /**
     * Retorna 204 NO CONTENT se a página estiver vazia,
     * ou 200 OK com os dados.
     *
     * @param page Page retornada da consulta
     * @param <T>  Tipo da entidade
     * @return ResponseEntity com status adequado
     */
    public static <T> ResponseEntity<Page<T>> fromPage(Page<T> page) {
        return page.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(page);
    }
}