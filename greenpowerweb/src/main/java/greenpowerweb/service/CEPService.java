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

public class CEPService {

    private static final String URL = "https://viacep.com.br/ws";

    public ResultadoConsultaCEP consultarCEP(String cep) throws ClientProtocolException, IOException {
        String url = URL + "/" + cep + "/json/";
        HttpGet request = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
        CloseableHttpResponse resposta = httpClient.execute(request);
        ResultadoConsultaCEP resultadoConsulta = null;

        HttpEntity entidade = resposta.getEntity();
        if (entidade != null) {
            String resultado = EntityUtils.toString(entidade);
            JsonObject jsonObject = new Gson().fromJson(resultado, JsonObject.class);
            if (jsonObject.has("erro") && jsonObject.get("erro").getAsBoolean()) {
                throw new IOException("Erro ao consultar o CEP (" + cep + "): CEP não encontrado.");
            }

            String rua = jsonObject.has("logradouro") ? jsonObject.get("logradouro").getAsString() : "";
            String bairro = jsonObject.has("bairro") ? jsonObject.get("bairro").getAsString() : "";
            String cidade = jsonObject.has("localidade") ? jsonObject.get("localidade").getAsString() : "";
            String estado = jsonObject.has("uf") ? jsonObject.get("uf").getAsString() : "";
            String cepFormatado = jsonObject.has("cep") ? jsonObject.get("cep").getAsString() : cep;

            resultadoConsulta = new ResultadoConsultaCEP(rua, bairro, cidade, estado, cepFormatado);
        }

        resposta.close();
        return resultadoConsulta;
    }

    public class ResultadoConsultaCEP {
        private String rua;
        private String bairro;
        private String cidade;
        private String estado;
        private String cepFormatado;

        public ResultadoConsultaCEP(String rua, String bairro, String cidade, String estado, String cepFormatado) {
            this.rua = rua;
            this.bairro = bairro;
            this.cidade = cidade;
            this.estado = estado;
            this.cepFormatado = cepFormatado;
        }

        public String getRua() {
            return rua;
        }

        public String getBairro() {
            return bairro;
        }

        public String getCidade() {
            return cidade;
        }

        public String getEstado() {
            return estado;
        }

        public String getCepFormatado() {
            return cepFormatado;
        }
    }
}
