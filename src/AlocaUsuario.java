import java.util.Objects;

public class AlocaUsuario {
    private String nome;
    private String setor;

    public AlocaUsuario(String nome, String setor) {
        this.nome = nome;
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlocaUsuario that = (AlocaUsuario) o;
        return Objects.equals(nome, that.nome) &&
                Objects.equals(setor, that.setor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, setor);
    }

    @Override
    public String toString() {
        return "AlocaUsuario{" +
                "nome='" + nome + '\'' +
                ", setor='" + setor + '\'' +
                '}';
    }
}
