public class Student {

    private String name;
    private String surname;
    private boolean sex;
    private int group;

    public Student(String name, String surname, boolean sex, int group) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    public boolean getSex() {
        return sex;
    }

    public String getSurname() {
        return surname;
    }


    @Override
    public String toString() {
        String gender =  sex ? "man" : "woman";
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", group=" + group +
                ", gender='" + gender + '\'' +
                '}';
    }
}
