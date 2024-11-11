package greenpowerweb.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CPFService {

    private static final String URL = "https://api.invertexto.com/v1/validator";
    private static final String TOKEN = "16105|zznB1xp5QYh83Je0ARWpblnZo1PMkB2C";

    public ResultadoValidacaoCPF validarCPF(String cpf) throws ClientProtocolException, IOException {
        String url = URL + "?token=" + TOKEN + "&value=" + cpf;
        HttpGet request = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
        CloseableHttpResponse resposta = httpClient.execute(request);
        ResultadoValidacaoCPF resultadoValidacao = null;

        HttpEntity entidade = resposta.getEntity();
        if (entidade != null) {
            String resultado = EntityUtils.toString(entidade);
            JsonObject jsonObject = new Gson().fromJson(resultado, JsonObject.class);
            boolean valido = jsonObject.has("valid") && jsonObject.get("valid").getAsBoolean();
            String cpfFormatado = jsonObject.has("formatted") ? jsonObject.get("formatted").getAsString() : cpf;
            
            resultadoValidacao = new ResultadoValidacaoCPF(valido, cpfFormatado);
        }

        resposta.close();
        return resultadoValidacao;
    }

    public class ResultadoValidacaoCPF {
        private boolean valido;
        private String cpfFormatado;

        public ResultadoValidacaoCPF(boolean valido, String cpfFormatado) {
            this.valido = valido;
            this.cpfFormatado = cpfFormatado;
        }

        public boolean isValido() {
            return valido;
        }

        public String getCpfFormatado() {
            return cpfFormatado;
        }
    }
}
