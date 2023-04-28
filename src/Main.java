import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Collection<String> list = Arrays.asList("a1","a2","a3","a1");

        // получение первого
        String first = list.stream().findFirst().get();

        // последний
        String last = list.stream().skip(list.size()-1).findAny().get();

        // найти
        String find = list.stream().filter("a2"::equals).findFirst().get();

        // вернуть 6-ый элемент коллекции
       // String nval = list.stream().skip(5).findAny().get();

        // вернуть все элемены по шаблону

        List<String> result = list.stream()
                .filter((s) -> s.contains("1"))
                .collect(Collectors.toList());
        // ["a1","a1"]

        // anyMatch - true (если оно истино хотя бы для 1 го элемента)

        // найти существует ли хоть одно совпадение с шаблоном
        boolean flag = list.stream().anyMatch("a1"::equals);

        // найти существуют ли все совпадения с шаблоном

        boolean flag2 = list.stream().allMatch((s) -> s.contains("a"));

        // сравнение на неравенство

        boolean flag3 = list.stream().noneMatch("a7"::equals);


        // foreach
        list.stream()
                .map(String::toUpperCase)
                .forEach((e)-> System.out.println(e + ", "));

        // вернуть первые два элемента

        List<String> limit1 = list.stream().limit(2).collect(Collectors.toList());

        // вернуть два элемента начиная с 2го

        List<String> limit2 = list.stream()
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());


        List<String> result4 = list.stream()
                .map((s)-> s+"_")
                .collect(Collectors.toList());
        // ["a1_",.....]

        // убрать первый символ и вернуть числа

        List<Integer> numbers = list.stream()
                .map((s)-> Integer.parseInt(s.substring(1)))
                .collect(Collectors.toList());


        String max = list.stream().max(String::compareTo).get();
        String min = list.stream().min(String::compareTo).get();

        class People {
            private String name;
            private int age;

            public People(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }


        }

        List<People> peoples = Arrays.asList(
                new People("Ivan", 20),
                new People("Petr", 21),
                new People("Maria", 23),
                new People("Olga", 19),
                new People("Natalya", 17)
        );

        // Найти человека с минимальным возрастом

        People who1 = peoples.stream()
                .max((p1,p2)->p1.getAge()-p2.getAge()).get();

        People who2 = peoples.stream()
                .max(Comparator.comparingInt(People::getAge)).get();
        System.out.println(who1.getName());

        // reduce -  позволяет выполнять агрегатные функции над всей коллекцией

        List<Integer> nums = Arrays.asList(43,1,2,4,4,55,3,8);

        Integer sum = nums.stream().reduce((s1,s2)->s1+s2).orElse(0);

        Integer max1 = nums.stream().reduce((s1,s2)->s1 > s2 ? s1 : s2).orElse(0);

        // последний элемент
        Integer last3 = nums.stream().reduce((s1, s2)->s2).orElse(0);

        // вернуть сумму числе, которые > 2

        Integer sum2 = nums.stream()
                .filter( o -> o > 2)
                .reduce((s1, s2) -> s1+s2)
                .orElse(0);

        // вернуть сумму нечетных

        Integer sum3 = nums.stream()
                .filter( o -> o%2 != 0)
                .reduce((s1, s2) -> s1+s2)
                .orElse(0);

       enum DogOrCat {
            DOG,
            CAT
        }

        class Animal {
            private String alias;
            private Integer age;

            private DogOrCat type;


            Animal(String alias, Integer age, DogOrCat dc) {
                this.alias = alias;
                this.age = age;
                this.type = dc;
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public Integer getAge() {
                return age;
            }

            public void setAge(Integer age) {
                this.age = age;
            }

            @Override
            public String toString() {
                return "Animal{" +
                        "alias='" + alias + '\'' +
                        ", age=" + age +
                        ", type=" + type +
                        '}';
            }
        }


        List<Animal> animals = Arrays.asList(
              new Animal("Barsik",5, DogOrCat.DOG),
               new Animal("Bbbb",1, DogOrCat.CAT),
                new Animal("Aaaa",16, DogOrCat.CAT),
                new Animal("Ddddd",8, DogOrCat.DOG)
        );

        Integer result5 = animals.stream()
                .filter((p) -> p.type == DogOrCat.DOG)
                .map(Animal::getAge)
                .reduce((s1,s2) -> s1>s2 ? s1 : s2)
                .get();
        // 8

      // Найти минимальный возраст животного у которого в имени есть буква а

       Integer result6 = animals.stream()
               .filter((p)-> p.alias.contains("a"))
               .mapToInt(Animal::getAge)
               .reduce((s1,s2) -> s1<s2 ? s1: s2).orElse(0);

       // sorted() - сортировать либо натур порядки либо через Comparator

        Collection<String> list2 = Arrays.asList("a1","a2","a3","a1");

        // отсортировать значения по алфавиту

        List<String> result7 = list2.stream().sorted().collect(Collectors.toList());

        // отсортировать по алфавиту и убрать дубликаты

        List<String> result8 = list2.stream()
                .sorted()
                .distinct()
                .collect(Collectors.toList());

        // отсортировать по алфавиту в обратном порядке


        List<String> result9 = list2.stream()
                .sorted(((o1, o2) -> -o1.compareTo(o2)))
                .collect(Collectors.toList());


        // Отсортировать по имени в обратном алф порядке

        List<Animal> sortedanimals = animals.stream()
                .sorted((o1, o2) -> -o1.alias.compareTo(o2.alias))
                .collect(Collectors.toList());

        // Отсортировать животных по типу (DOG, CAT), а потом по возрасту

        List<Animal> sortedanimals2 = animals.stream()
                .sorted((o1, o2) ->
                        o1.type != o2.type
                                ? o1.type.compareTo(o2.type)
                                : o1.age.compareTo(o2.age)
                )
                .collect(Collectors.toList());

        System.out.println(sortedanimals2);

        // Найти средний возраст DOG
        double avg = animals.stream()
                .filter((p)-> p.type == DogOrCat.DOG)
                .mapToInt(Animal::getAge)
                .average()
                .getAsDouble();
        System.out.println(avg);

        // Найти количество всех DOG возраст которых > 3 лет и CAT возраст которых < 2

        long count = animals.stream()
                .filter((p)-> (p.type == DogOrCat.DOG && p.age > 3))
                .filter((p)-> (p.type == DogOrCat.CAT && p.age < 2))
                .count();












    }
}