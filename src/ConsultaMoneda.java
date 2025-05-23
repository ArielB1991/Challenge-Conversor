import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    public Moneda buscarMoneda(String siglaMoneda){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/7f4ee1c40b45dc64004a35ca/latest/" + siglaMoneda);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try{
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
                    return new Gson().fromJson(response.body(), Moneda.class);

        }catch (Exception e){
            throw new RuntimeException("No se encontro la moneda buscada");
        }

    }


}
