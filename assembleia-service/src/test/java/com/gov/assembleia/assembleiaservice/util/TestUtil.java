package com.gov.assembleia.assembleiaservice.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestUtil {

  private static final ObjectMapper mapper = createObjectMapper();

  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

  private static ObjectMapper createObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    mapper.registerModule(new JavaTimeModule());
    return mapper;
  }

  /**
   * Converte um objeto em um array de byte JSON.
   *
   * @param object o objeto a ser convertido.
   * @return o array de byte JSON.
   * @throws IOException
   */
  public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
    return mapper.writeValueAsBytes(object);
  }

}
