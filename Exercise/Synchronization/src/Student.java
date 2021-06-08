public class Student {
    private int id;
    private String name;
    private float grade;

    Student() { }

    Student(int id, String name, float grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public void studentInfo() {
        System.out.println(id + " " + name + " " + grade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
