package com.lteixeira.poctestcontainers.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;

public class ReadFileUtils {
    private final static ObjectMapper mapper = new ObjectMapper();

    public static byte[] read(final String file) {
        try (final InputStream inputStream = new ClassPathResource(file).getInputStream()) {
            return FileCopyUtils.copyToByteArray(inputStream);
        } catch (final Exception e) {
            throw new RuntimeException(String.format("Erro ao processar: %s", file));
        }
    }

    public static<T> T readAsJson(final String file, final Class<T> clazz) {
        try {
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(read(file), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
