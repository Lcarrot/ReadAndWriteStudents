import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;

public class StudentsFile {

    public static ArrayList<Student> read(File name) {

        ArrayList<Student> students = new ArrayList<>();
        boolean sex;
        int group;
        try (InputStream is = new FileInputStream(name)) {
            int b = is.read();
            ByteBuffer bf;
            while (b != -1) {

                String[] Student = new String[2];
                for (int k = 0; k < 2; k++) {
                    bf = ByteBuffer.allocate(40);
                    while (b != ' ') {
                        bf.put((byte)b);
                        b = is.read();
                    }
                    Student[k] = new String(bf.array());
                    if (k == 0) {
                        b  = is.read();
                    }
                }
                b = is.read();
                 sex = b == 1;
                bf = ByteBuffer.allocate(4);
                for (int i = 0; i < 4; i++) {
                    bf.put((byte) is.read());
                }
                bf.rewind();
                group = bf.getInt();
                students.add(new Student(Student[0], Student[1], sex, group));
                b = is.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void write(ArrayList<Student> students) {
        Iterator<Student> iterator = students.iterator();
        ByteBuffer bf;
        try (OutputStream os = new FileOutputStream("src/Students")) {
            while (iterator.hasNext()) {
                Student student = iterator.next();
                String[] inf = new String[2];
                inf[0] = student.getName();
                inf[1] = student.getSurname();
                for (String s : inf) {
                    bf = ByteBuffer.allocate(s.length()*2+2);
                    for (int k = 0; k < s.length(); k++) {
                        bf.putChar(s.charAt(k));
                    }
                    bf.putChar(' ');
                    os.write(bf.array());
                }
                int b = (student.getSex()) ?  1 :  2;
                os.write(b);
                os.flush();
                bf = ByteBuffer.allocate(4);
                bf.putInt(student.getGroup());
                bf.rewind();
                os.write(bf.slice().array());
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Oleg", "denis", true, 902));
        students.add(new Student("Pavel", "denis", false, 902));
        write(students);
        ArrayList<Student> students1 =  read(new File("src/Students"));
        System.out.println(students1.get(0));
        System.out.println(students1.get(1));
    }
}
