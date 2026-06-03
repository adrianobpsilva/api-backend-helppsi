package com.atividade.aula.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenCageService {

    @Value("${geocoding.opencage.api.key}")
    private String apiKey;

    public Map<String, Double> buscarCoordenadas(String endereco) {

        try {

            System.out.println("Entrou no OpenCage");
            System.out.println("Endereço recebido: " + endereco);
            System.out.println("API KEY: " + apiKey);

            String url =
                    "https://api.opencagedata.com/geocode/v1/json?q="
                    + endereco.replace(" ", "%20")
                    + "&key="
                    + apiKey;

            System.out.println("URL CONSULTADA:");
            System.out.println(url);

            RestTemplate restTemplate =
                    new RestTemplate();

            Map<?, ?> response =
                    restTemplate.getForObject(
                            url,
                            Map.class
                    );

            System.out.println("RESPOSTA:");
            System.out.println(response);

            List<?> results =
                    (List<?>) response.get("results");

            if (results == null || results.isEmpty()) {

                System.out.println(
                        "Nenhum resultado encontrado."
                );

                return null;
            }

            Map<?, ?> primeiroResultado =
                    (Map<?, ?>) results.get(0);

            Map<?, ?> geometry =
                    (Map<?, ?>) primeiroResultado.get("geometry");

            Double latitude =
                    ((Number) geometry.get("lat"))
                            .doubleValue();

            Double longitude =
                    ((Number) geometry.get("lng"))
                            .doubleValue();

            System.out.println(
                    "Latitude encontrada: "
                    + latitude
            );

            System.out.println(
                    "Longitude encontrada: "
                    + longitude
            );

            return Map.of(
                    "latitude", latitude,
                    "longitude", longitude
            );

        } catch (Exception e) {

            System.out.println("ERRO OPEN CAGE:");
            e.printStackTrace();

            return null;
        }
    }
}