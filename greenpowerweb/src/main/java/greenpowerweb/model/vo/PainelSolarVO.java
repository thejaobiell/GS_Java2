package greenpowerweb.model.vo;

import java.sql.Date;

public class PainelSolarVO {
    private int id_painelsolar;
    private String email_cliente;
    private double energia_gerada_kwh;
    private double energia_consumida_kwh;
    private Date data_registro;

    public PainelSolarVO() {}

    public PainelSolarVO(int id_painelsolar, String email_cliente, double energia_gerada_kwh, double energia_consumida_kwh, Date data_registro) {
        this.id_painelsolar = id_painelsolar;
        this.email_cliente = email_cliente;
        this.energia_gerada_kwh = energia_gerada_kwh;
        this.energia_consumida_kwh = energia_consumida_kwh;
        this.data_registro = data_registro;
    }

    public int getId_painelsolar() {
        return id_painelsolar;
    }

    public void setId_painelsolar(int id_painelsolar) {
        this.id_painelsolar = id_painelsolar;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public double getEnergia_gerada_kwh() {
        return energia_gerada_kwh;
    }

    public void setEnergia_gerada_kwh(double energia_gerada_kwh) {
        this.energia_gerada_kwh = energia_gerada_kwh;
    }

    public double getEnergia_consumida_kwh() {
        return energia_consumida_kwh;
    }

    public void setEnergia_consumida_kwh(double energia_consumida_kwh) {
        this.energia_consumida_kwh = energia_consumida_kwh;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

    @Override
    public String toString() {
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dateFormat.format(data_registro);
        return "Painel Solar [ID: " + id_painelsolar + 
               ", Email Cliente: " + email_cliente + 
               ", Energia Gerada: " + String.format("%.2f", energia_gerada_kwh) + " kWh" +
               ", Energia Consumida: " + String.format("%.2f", energia_consumida_kwh) + " kWh" + 
               ", Data de Registro: " + dataFormatada + "]";
    }
}
