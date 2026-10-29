public class Tarefa {
    private int id;
    private String descricao;
    private Boolean concluida;

    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = false;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void concluir() {
        this.concluida = true;
    }

}
