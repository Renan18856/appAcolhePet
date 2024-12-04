package com.example.acolhe_pet_app;

public class ModeloDenuncia {
    int idDenuncia;
    String email, data, rua, numero, bairro, cidade, descricaoProblema, urgencia, nomeDenun, celularDenun;

    //método construtor
    public ModeloDenuncia() {

    }
    //método construtor
    public ModeloDenuncia(int _id, String _email,String _data, String _numero, String _rua, String _bairro, String _cidade
            , String _descricaoProblema, String _urgencia, String _nomeDenun ,String _celularDenun) {
        this.setIdDenuncia(_id);
        this.setEmail(_email);
        this.setData(_data);
        this.setNumero(_numero);
        this.setRua(_rua);
        this.setBairro(_bairro);
        this.setCidade(_cidade);
        this.setDescricaoProblema(_descricaoProblema);
        this.setUrgencia(_urgencia);
        this.setCelularDenun(_celularDenun);
        this.setNomeDenun(_nomeDenun);
    }
    //set
    public void setIdDenuncia(int _id){
        this.idDenuncia = _id;
    }
    public void setEmail(String _email){
        this.email = _email;
    }
    public void setData(String _data){
        this.data = _data;
    }
    public void setNumero(String _numero){
        this.numero = _numero;
    }
    public void setRua(String _rua){
        this.rua = _rua;
    }
    public void setBairro(String _bairro){
        this.bairro = _bairro;
    }
    public void setCidade(String _cidade){
        this.cidade = _cidade;
    }
    public void setDescricaoProblema(String _descricaoProblema){
        this.descricaoProblema = _descricaoProblema;
    }
    public void setUrgencia(String _urgencia){
        this.urgencia = _urgencia;
    }
    public void setCelularDenun(String _celularDenun){
        this.celularDenun = _celularDenun;
    }
    public void setNomeDenun(String _nomeDenun){
        this.nomeDenun = _nomeDenun;
    }


    //get
    public int getIdDenuncia() {
        return this.idDenuncia;
    }
    public String getEmail() {
        return this.email;
    }
    public String getData() {
        return this.data;
    }
    public String getNumero() {
        return this.numero;
    }
    public String getRua(){
        return this.rua;
    }
    public String getBairro(){
        return this.bairro;
    }
    public String getCidade(){
        return this.cidade;
    }
    public String getDescricaoProblema(){
        return this.descricaoProblema;
    }
    public String getUrgencia(){
        return this.urgencia;
    }
    public String getCelularDenun(){
        return this.celularDenun;
    }
    public String getNomeDenun(){
        return this.nomeDenun;
    }
}
