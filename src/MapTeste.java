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

        //Pega itens maior que 30, e retorna total de itens
        Long toral_gt = usuarioList.stream()
                .filter(o -> o.getIdade() > 30) //Filtra ítens que a idade é maior que 30
                .map(Usuario::getIdade) //Pega somente a idade
                .collect(counting()); //conta
        System.out.println("counting");
        System.out.println(toral_gt);

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


        //Gera lista de UnmodifiableList
        List<Usuario> imodificavelUsuarioList = usuarioList.stream()
                .collect(toUnmodifiableList());
                

        //--- parte 2 ---

        System.out.println("--toUnmodifiableList--");
        System.out.println(imodificavelUsuarioList.toString());


        //List<String> result = givenList.stream()
        //.collect(collectingAndThen(toList(), ImmutableList::copyOf))


        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        String result = givenList.stream()
                .collect(joining());
        System.out.println(result);

        String result_separator = givenList.stream()
                .collect(joining(" "));
        System.out.println(result_separator);

        String result_separator_pre_pos = givenList.stream()
                .collect(joining(" ", "PRE-", "-POST"));
        System.out.println(result_separator_pre_pos);

        DoubleSummaryStatistics result_summarizated = givenList.stream()
                .collect(summarizingDouble(String::length));

        System.out.println("--summarizingDouble--");
        System.out.println("getAverage" + result_summarizated.getAverage());
        System.out.println("getCount" + result_summarizated.getCount());
        System.out.println("getMax" + result_summarizated.getMax());
        System.out.println("getMin" + result_summarizated.getMin());
        System.out.println("getSum" + result_summarizated.getSum());


        Double result_avg = givenList.stream()
                .collect(averagingDouble(String::length));
        System.out.println("averagingDouble" + result_avg);

        Double result_sum = givenList.stream()
        .collect(summingDouble(String::length));
        System.out.println("summingDouble" + result_sum);

        Optional<String> result_max = givenList.stream()
        .collect(maxBy(Comparator.naturalOrder()));
        System.out.println("result_max" + result_max);

        Map<Integer, Set<String>> result_grpby = givenList.stream()
        .collect(groupingBy(String::length, toSet()));
        System.out.println("groupingBy" + result_grpby);

        Map<Boolean, List<String>> result_partition = givenList.stream()
        .collect(partitioningBy(s -> s.length() > 2));
        System.out.println("partitioningBy" + result_partition);

        // --- teeing ---
        List<Integer> numbers = Arrays.asList(42, 4, 2, 24);
        Optional<Integer> min = numbers.stream().collect(minBy(Integer::compareTo));
        Optional<Integer> max = numbers.stream().collect(maxBy(Integer::compareTo));
        // do something useful with min and max


        Result result_aaa  = numbers.stream().collect(teeing(
                minBy(Integer::compareTo), // The first collector
                maxBy(Integer::compareTo), // The second collector
                (minInternal, maxInternal) -> new Result(minInternal, maxInternal)// Receives the result from those collectors and combines them
              ));

        // Imprimindo o resultado
        System.out.println("Soma: " + result_aaa.getMin());
        System.out.println("Média: " + result_aaa.getMax());

        // --- end teeing ---



        //Obs.: Os retornos de stream, filter, sorted, map, collect são do tipo Stream, ou seja podem ser usados
        //em ordens diferentes, repetidos .... sei lá.

        //Fontes: https://www.oracle.com/br/technical-resources/articles/java-stream-api.html
        //https://www.baeldung.com/java-8-collectors

    }
    // Classe para armazenar os resultados
    static class Result {
        private final Optional<Integer> min;
        private final Optional<Integer> max;

        public Result(Optional<Integer> min2, Optional<Integer> max2) {
            this.min = min2;
            this.max = max2;
        }

        public Optional<Integer> getMin() {
            return min;
        }

        public Optional<Integer> getMax() {
            return max;
        }
    }
}
