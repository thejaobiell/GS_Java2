package greenpowerweb.model.vo;

public class ClienteVO {
    private String email_Cliente;
    private String senha_Cliente;
    private String nome_Cliente;
    private String sobrenome_Cliente;
    private String cpf_Cliente;
    private String rua_Cliente;
    private int numero_Cliente;
    private String complemento_Cliente;
    private String bairro_Cliente;
    private String cidade_Cliente;
    private String estado_Cliente;
    private String cep_Cliente;
    
    public ClienteVO() {}

    public ClienteVO(String email_Cliente, String senha_Cliente, String nome_Cliente, String sobrenome_Cliente, String cpf_Cliente, String rua_Cliente, int numero_Cliente, String complemento_Cliente, String bairro_Cliente, String cidade_Cliente, String estado_Cliente, String cep_Cliente) {
        this.email_Cliente = email_Cliente;
        this.senha_Cliente = senha_Cliente;
        this.nome_Cliente = nome_Cliente;
        this.sobrenome_Cliente = sobrenome_Cliente;
        this.cpf_Cliente = cpf_Cliente;
        this.rua_Cliente = rua_Cliente;
        this.numero_Cliente = numero_Cliente;
        this.complemento_Cliente = complemento_Cliente;
        this.bairro_Cliente = bairro_Cliente;
        this.cidade_Cliente = cidade_Cliente;
        this.estado_Cliente = estado_Cliente;
        this.cep_Cliente = cep_Cliente;
    }

    public String getEmail_Cliente() {
        return email_Cliente;
    }

    public void setEmail_Cliente(String email_Cliente) {
        this.email_Cliente = email_Cliente;
    }

    public String getSenha_Cliente() {
        return senha_Cliente;
    }

    public void setSenha_Cliente(String senha_Cliente) {
        this.senha_Cliente = senha_Cliente;
    }

    public String getNome_Cliente() {
        return nome_Cliente;
    }

    public void setNome_Cliente(String nome_Cliente) {
        this.nome_Cliente = nome_Cliente;
    }

    public String getSobrenome_Cliente() {
        return sobrenome_Cliente;
    }

    public void setSobrenome_Cliente(String sobrenome_Cliente) {
        this.sobrenome_Cliente = sobrenome_Cliente;
    }

    public String getCpf_Cliente() {
        return cpf_Cliente;
    }

    public void setCpf_Cliente(String cpf_Cliente) {
        this.cpf_Cliente = cpf_Cliente;
    }

    public String getRua_Cliente() {
        return rua_Cliente;
    }

    public void setRua_Cliente(String rua_Cliente) {
        this.rua_Cliente = rua_Cliente;
    }

    public int getNumero_Cliente() {
        return numero_Cliente;
    }

    public void setNumero_Cliente(int numero_Cliente) {
        this.numero_Cliente = numero_Cliente;
    }

    public String getComplemento_Cliente() {
        return complemento_Cliente;
    }

    public void setComplemento_Cliente(String complemento_Cliente) {
        this.complemento_Cliente = complemento_Cliente;
    }

    public String getBairro_Cliente() {
        return bairro_Cliente;
    }

    public void setBairro_Cliente(String bairro_Cliente) {
        this.bairro_Cliente = bairro_Cliente;
    }

    public String getCidade_Cliente() {
        return cidade_Cliente;
    }

    public void setCidade_Cliente(String cidade_Cliente) {
        this.cidade_Cliente = cidade_Cliente;
    }

    public String getEstado_Cliente() {
        return estado_Cliente;
    }

    public void setEstado_Cliente(String estado_Cliente) {
        this.estado_Cliente = estado_Cliente;
    }

    public String getCep_Cliente() {
        return cep_Cliente;
    }

    public void setCep_Cliente(String cep_Cliente) {
        this.cep_Cliente = cep_Cliente;
    }

    @Override
    public String toString() {
        return "Dados Cadastrados:\n" +
               "Nome Completo: " + nome_Cliente + " " + sobrenome_Cliente + " | " +
               "Email: " + email_Cliente + " | " +
               "Senha: " + (senha_Cliente != null ? "*".repeat(senha_Cliente.length()) : "Não cadastrada") + " | " +
               "Endereço: " + rua_Cliente + ", Nº " + numero_Cliente + 
               ", Complemento: " + complemento_Cliente + ", Bairro: " + bairro_Cliente + ", Cidade: " + cidade_Cliente + 
               ", Estado: " + estado_Cliente + " CEP: " + cep_Cliente;
    }

}