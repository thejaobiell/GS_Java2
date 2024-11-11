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

public class EmailService {

    private static final String URL = "https://api.invertexto.com/v1/email-validator/";
    private static final String TOKEN = "16105|zznB1xp5QYh83Je0ARWpblnZo1PMkB2C";

    public ResultadoValidacaoEmail validarEmail(String email) throws ClientProtocolException, IOException {
        String url = URL + email + "?token=" + TOKEN;
        HttpGet request = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
        CloseableHttpResponse response = httpClient.execute(request);
        ResultadoValidacaoEmail validacaoEmail = null;

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
            
            boolean formatoValido = jsonObject.has("valid_format") && jsonObject.get("valid_format").getAsBoolean();
            boolean mxValido = jsonObject.has("valid_mx") && jsonObject.get("valid_mx").getAsBoolean();
            boolean descartavel = jsonObject.has("disposable") && jsonObject.get("disposable").getAsBoolean();
            
            validacaoEmail = new ResultadoValidacaoEmail(formatoValido, mxValido, descartavel);
        }

        response.close();
        return validacaoEmail;
    }

    public class ResultadoValidacaoEmail {
        private boolean formatoValido;
        private boolean mxValido;
        private boolean descartavel;

        public ResultadoValidacaoEmail(boolean formatoValido, boolean mxValido, boolean descartavel) {
            this.formatoValido = formatoValido;
            this.mxValido = mxValido;
            this.descartavel = descartavel;
        }

        public boolean isFormatoValido() {
            return formatoValido;
        }

        public boolean isMxValido() {
            return mxValido;
        }

        public boolean isDescartavel() {
            return descartavel;
        }
    }
}
