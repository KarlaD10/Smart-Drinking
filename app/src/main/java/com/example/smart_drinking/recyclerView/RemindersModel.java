package com.example.smart_drinking.recyclerView;

public class RemindersModel {
    String dia, hora, horario;
    int img;
    //ha

    public RemindersModel(String dia, String hora, String horario, int img) {
        this.dia = dia;
        this.hora = hora;
        this.horario = horario;
        this.img = img;
    }

    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public String getHorario() {
        return horario;
    }

    public int getImg() {
        return img;
    }
}
