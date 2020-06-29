import java.util.*;

import static java.util.stream.Collectors.*;

public class MapTeste {
    public static void main(String args[])
    {
        //Usando List (itens duplicam)
        List<Usuario> usuarioList = new ArrayList<>();

        //Lista simples de uma classe de usuários
        usuarioList.add(new Usuario("Meu", 33, Float.valueOf(0)));
        usuarioList.add(new Usuario("Tatu", 32, Float.valueOf(1)));
        usuarioList.add(new Usuario("Cava", 31, Float.valueOf(2)));
        usuarioList.add(new Usuario("Buraco", 30, Float.valueOf(3)));
        usuarioList.add(new Usuario("Meu", 33, Float.valueOf(0)));

        System.out.println(usuarioList.toString());


        //
        //Usando HashSet (itens não duplica)
        HashSet<Usuario> usuarioHashSet = new HashSet<>();

        //Lista simples de uma classe de usuários
        usuarioHashSet.add(new Usuario("Meu", 33, Float.valueOf(0)));
        usuarioHashSet.add(new Usuario("Tatu", 32, Float.valueOf(1)));
        usuarioHashSet.add(new Usuario("Cava", 31, Float.valueOf(2)));
        usuarioHashSet.add(new Usuario("Buraco", 30, Float.valueOf(3)));
        usuarioHashSet.add(new Usuario("Meu", 33, Float.valueOf(0)));

        //Imprime a lista
        System.out.println(usuarioHashSet.toString());

        //Usando Stream (o acesso é sequencial não permitindo acesso por indices)
        System.out.println(usuarioList.stream().findFirst());

        //Soma idade de todos na lista
        System.out.println(usuarioList.stream().mapToInt(Usuario::getIdade).sum());

        //Filtra Ítens onde idade é maior que 32 e retorna uma lista
        List<Usuario> usuarioList_LT_32 = usuarioList.stream().filter(o -> o.getIdade() > 32).collect(toList());
        System.out.println(usuarioList_LT_32.toString());

        //Pega itens maior que 30, ordena decrescente e cria uma lista de inteiros
        List<Integer> idadeList = usuarioList.stream()
                .filter(o -> o.getIdade() > 30) //Filtra ítens que a idade é maior que 30
                .sorted(Comparator.comparing(Usuario::getIdade).reversed()) //Ordena de forma decrescente
                .map(Usuario::getIdade) //Pega somente a idade
                .collect(toList()); //Gera lista
        System.out.println(idadeList.toString());

        //Pega itens maior que 30, ordena decrescente e cria uma lista de inteiros sem duplicidades
        Set<Integer> idadeSemSuplicidadeList = usuarioList.stream()
                .filter(o -> o.getIdade() > 30)
                .sorted(Comparator.comparing(Usuario::getIdade).reversed())
                .map(Usuario::getIdade)
                .collect(toSet()); //Gera um Set
        System.out.println(idadeSemSuplicidadeList.toString());


        //Pega itens maior que 30, ordena decrescente e cria uma lista de inteiros sem duplicidades
        List<AlocaUsuario> alocaUsuarios = usuarioList.stream()
                .filter(o -> o.getIdade() > 30)
                .sorted(Comparator.comparing(Usuario::getIdade).reversed())
                .map(item -> new AlocaUsuario(item.getNome(), "Teste")) //Cria um objeto diferente do de entrada
                .collect(toList());
        System.out.println(alocaUsuarios.toString());

        //Pega itens maior que 30, ordena decrescente e cria uma lista com mesmos objetos de saída
        List<Usuario> novaUsuarioList = usuarioList.stream()
                .filter(o -> o.getIdade() > 30) //Filtra ítens que a idade é maior que 30
                .sorted(Comparator.comparing(Usuario::getIdade).reversed()) //Ordena de forma decrescente
                .collect(toCollection(LinkedList::new)); //Gera lista
        System.out.println(novaUsuarioList.toString());

        //Obs.: Os retornos de stream, filter, sorted, map, collect são do tipo Stream, ou seja podem ser usados
        //em ordens diferentes, repetidos .... sei lá.

        //Fontes: https://www.oracle.com/br/technical-resources/articles/java-stream-api.html
        //https://www.baeldung.com/java-8-collectors

    }
}
