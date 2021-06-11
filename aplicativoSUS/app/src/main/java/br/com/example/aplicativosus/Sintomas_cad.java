package br.com.example.aplicativosus;

import java.util.ArrayList;

public class Sintomas_cad {

    public String email_user;
    public String servico_atendimento;
    public String regiao_sintoma;
    public String onde;
    public ArrayList<String> sintomas;
    public String data_direcionamento;
    public String tipo_servico;
    public int hora_direcionamento;
    public double latitude_destino;
    public double longitude_destino;
    public double latitude_origem;
    public double longitude_origem;



    public Sintomas_cad(String email_user, String servico_atendimento, String regiao_sintoma, String onde, ArrayList<String> sintomas, int hora_direcionamento, String data_direcionamento, String tipo_servico, double latitude_destino, double longitude_destino, double latitude_origem, double longitude_origem){
        this.email_user = email_user;
        this.servico_atendimento = servico_atendimento;
        this.regiao_sintoma = regiao_sintoma;
        this.onde = onde;
        this.sintomas = sintomas;
        this.hora_direcionamento = hora_direcionamento;
        this.data_direcionamento = data_direcionamento;
        this.tipo_servico = tipo_servico;
        this.latitude_destino = latitude_destino;
        this.longitude_destino = longitude_destino;
        this.latitude_origem = latitude_origem;
        this.longitude_origem = longitude_origem;
    }

//    public String getServico_atendimento() {
//        return servico_atendimento;
//    }
//
//    public void setServico_atendimento(String servico_atendimento) {
//        this.servico_atendimento = servico_atendimento;
//    }
//
//    public String getRegiao_sintoma() {
//        return regiao_sintoma;
//    }
//
//    public void setRegiao_sintoma(String regiao_sintoma) {
//        this.regiao_sintoma = regiao_sintoma;
//    }
//
//    public String getOnde() {
//        return onde;
//    }
//
//    public void setOnde(String onde) {
//        this.onde = onde;
//    }
//
//    public String getSintoma() {
//        return sintoma;
//    }
//
//    public void setSintoma(String sintoma) {
//        this.sintoma = sintoma;
//    }
}
