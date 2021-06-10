import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"name"})
class Person {
    private String personId;
    private String name;
    private String email;
}

class Test {
    public static void main(String[] args) {
        Person person1 = new Person("9", "John Doe", "JohnDoe@tw.com");

    }
}