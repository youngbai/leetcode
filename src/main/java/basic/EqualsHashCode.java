package basic;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Ref:
https://blog.csdn.net/zpf336/article/details/42397415
https://javagoal.com/hashcode-method-in-java/

 */
public class EqualsHashCode {

    class Student {
        int id;
        String name;

        Student(int id, String name) {
            this.id = id;
            this.name = name;
        }


        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (o instanceof Student) {
                Student s = (Student) o;
                return s.id == this.id && s.name.equals(this.name);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return id + name.hashCode();
        }

    }


    @Test
    public void test1() {

        Integer i1 = 1;
        Integer i2 = 1;

        assertTrue(i1.equals(i2));

        Set<Integer> set = new HashSet<>();
        set.add(i1);
        set.add(i2);
        System.out.println(set);    // [1]
    }


    @Test
    public void test2() {

        Student s1 = new Student(1, "A");
        Student s2 = new Student(1, "A");

        assertTrue(s1.equals(s2));

        Set<Student> set = new HashSet<>();
        set.add(s1);
        set.add(s2);
        System.out.println(set);    // only one student print out
    }


    @Test
    public void test3_Array() {

        int[] arr1 = {1, 2};
        int[] arr2 = {1, 2};

        Set<int[]> set = new HashSet<>();
        set.add(arr1);
        set.add(arr2);

        System.out.println(set);    // print two element
    }


}
