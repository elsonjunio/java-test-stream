import java.util.Objects;

public class Usuario {
    private String nome;
    private Integer idade;
    private Float prioridade;

    public Usuario(String nome, Integer idade, Float prioridade) {
        this.nome = nome;
        this.idade = idade;
        this.prioridade = prioridade;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Float prioridade) {
        this.prioridade = prioridade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) &&
                Objects.equals(idade, usuario.idade) &&
                Objects.equals(prioridade, usuario.prioridade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade, prioridade);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", prioridade=" + prioridade +
                '}';
    }
}
