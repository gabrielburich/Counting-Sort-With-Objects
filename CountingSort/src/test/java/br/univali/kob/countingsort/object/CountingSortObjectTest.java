package br.univali.kob.countingsort.object;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CountingSortObjectTest {

    private CountingSortObject countingSortObject;
    private People[] arrayToSort;
    private People[] returnArray;

    @BeforeAll
    public void initValues() {
        this.countingSortObject = new CountingSortObject();
        this.arrayToSort = new People[4];
        this.returnArray = new People[4];

        People p1 = new People("Sophia", 4);
        People p2 = new People("Lucas", 34);
        People p3 = new People("Oliver", 76);

        arrayToSort[0] = p2;
        arrayToSort[1] = p1;
        arrayToSort[2] = p3;
        arrayToSort[3] = p1;

        returnArray[0] = p1;
        returnArray[1] = p1;
        returnArray[2] = p2;
        returnArray[3] = p3;
    }

    @Test
    public void sortTest() {
        Object[] peoples =  this.countingSortObject.sort(this.arrayToSort);
        assertArrayEquals(this.returnArray, peoples);
    }

    /**
     * Class to test CountingSortObject
     * implements CountingSortAble
     */
    public class People implements CountingSortAble{
        private final String name;
        private final int age;

        public People(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        /**
         * @return key to sort
         */
        @Override
        public int sortValue() {
            return this.age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            People people = (People) o;
            return age == people.age &&
                    Objects.equals(name, people.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
