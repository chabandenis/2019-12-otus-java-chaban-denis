import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DenArrayListTest {

    @Test
    void t1() {
        List<String> list = new DenArrayList();
        assertEquals(0, list.size());
    }

    @Test
    void test2() {
        List<String> list = new DenArrayList();
        list.add("q1");
        assertEquals(1, list.size());
        assertEquals("q1", list.get(0));
    }

    @Test
    void test3() {
        List<String> list = new DenArrayList();
        list.add("q1");
        list.add("q2");
        assertEquals(2, list.size());
        assertEquals("q1", list.get(0));
        assertEquals("q2", list.get(1));
    }

    @Test
    void test4() {
        List<String> list = new DenArrayList();
        list.add("q1");
        list.add("q2");
        list.add("q3");
        assertEquals(3, list.size());
        assertEquals("q1", list.get(0));
        assertEquals("q2", list.get(1));
        assertEquals("q3", list.get(2));
    }

    @Test
    void test5() {
        List<String> list = new DenArrayList();

        for (int i = 0; i < 100000; i++) {
            list.add("q" + i);
        }

        assertEquals(100000, list.size());

        for (int i = 0; i < 100000; i++) {
            assertEquals("q" + i, list.get(i));
        }
    }

    @Test
    void test6() {
        List<String> listFirst = new DenArrayList();

        int maxCount = 10;

        for (int i = 0; i < maxCount; i++) {
            listFirst.add("q" + i);
        }

        Iterator iterator = listFirst.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals("q" + i, iterator.next());
            i++;
        }

    }

    @Test
    void test7() {
        List<String> listFirst = new DenArrayList();

        int maxCount = 30;

        for (int i = 0; i < maxCount; i++) {
            listFirst.add("q" + i);
        }

        assertEquals(maxCount, listFirst.size());

        List<String> listSecond = new DenArrayList();

        listSecond.addAll(listFirst);
        assertEquals(listFirst.size(), listSecond.size());

        for (int i = 0; i < maxCount; i++) {
            assertEquals(listFirst.get(i), listSecond.get(i));
        }
    }

    @Test
    void test8() {
        List<String> listFirst = new DenArrayList();
        List<String> listSecond = new DenArrayList();

        int maxCount = 30;

        for (int i = 0; i < maxCount; i++) {
            listFirst.add("q" + i);
            listSecond.add("!");
        }

        assertEquals(maxCount, listFirst.size());

        Collections.copy(listSecond, listFirst);

        assertEquals(listFirst.size(), listSecond.size());

        for (int i = 0; i < maxCount; i++) {
            assertEquals(listFirst.get(i), listSecond.get(i));
        }
    }


    @Test
    void test9() {
        List<String> listFirst = new DenArrayList();
        List<String> listSecond = new DenArrayList();

        int maxCount = 8;

        for (int i = 0; i < maxCount; i++) {
            listFirst.add("q" + i);
            listSecond.add("!");
        }

        assertEquals(maxCount, listFirst.size());

        Collections.copy(listSecond, listFirst);

        assertEquals(listFirst.size(), listSecond.size());

        for (int i = 0; i < maxCount; i++) {
            assertEquals(listFirst.get(i), listSecond.get(i));
        }
    }

    @Test
    void test11() {
        List<String> listFirst = new DenArrayList();

        listFirst.add("q1");
        listFirst.add("q2");
        listFirst.add("q3");

        List<String> listEtalon = new ArrayList<String>();
        listEtalon.add("q1");
        listEtalon.add("q2");
        listEtalon.add("q3");

        ListIterator<String> listIterator = listFirst.listIterator();
        ListIterator<String> listIteratorEtalon = listEtalon.listIterator();

        String first = "";
        String etalon = "";

        first = listIterator.next();
        etalon = listIteratorEtalon.next();
        assertEquals(etalon, first);

        listIterator.set("11q");
        listIteratorEtalon.set("11q");

        first = listIterator.previous();
        etalon = listIteratorEtalon.previous();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 11q"); //11q

        first = listIterator.next();
        etalon = listIteratorEtalon.next();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 11q"); //11q

        first = listIterator.next();
        etalon = listIteratorEtalon.next();
        assertEquals(etalon, first);
        System.out.println(etalon + "; q2"); //q2

        listIterator.set("22q");
        listIteratorEtalon.set("22q");

        first = listIterator.next();
        etalon = listIteratorEtalon.next();
        assertEquals(etalon, first);
        System.out.println(etalon + "; q3");

        first = listIterator.previous();
        etalon = listIteratorEtalon.previous();
        assertEquals(etalon, first);
        System.out.println(etalon + "; q3");

        first = listIterator.next();
        etalon = listIteratorEtalon.next();
        assertEquals(etalon, first);
        System.out.println(etalon + "; q3");

        listIterator.set("33q");
        listIteratorEtalon.set("33q");

        first = listIterator.previous();
        etalon = listIteratorEtalon.previous();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 33q");

        first = listIterator.previous();
        etalon = listIteratorEtalon.previous();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 22q");

        first = listIterator.previous();
        etalon = listIteratorEtalon.previous();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 11q");

        listIterator.set("111q");
        listIteratorEtalon.set("111q");

        first = listIterator.next();
        etalon = listIteratorEtalon.next();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 22q");

        first = listIterator.previous();
        etalon = listIteratorEtalon.previous();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 111q");

        first = listIterator.next();
        etalon = listIteratorEtalon.next();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 111q");

        first = listIterator.next();
        etalon = listIteratorEtalon.next();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 22q");

        first = listIterator.next();
        etalon = listIteratorEtalon.next();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 33q");

        listIterator.set("333q");
        listIteratorEtalon.set("333q");

        first = listIterator.previous();
        etalon = listIteratorEtalon.previous();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 22q");

        listIterator.set("333q");
        listIteratorEtalon.set("333q");

        first = listIterator.previous();
        etalon = listIteratorEtalon.previous();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 22q");

        listIterator.set("222q");
        listIteratorEtalon.set("222q");

        first = listIterator.previous();
        etalon = listIteratorEtalon.previous();
        assertEquals(etalon, first);
        System.out.println(etalon + "; 111q");

    }

    @Test
    void test12() {
        List<String> listFirst = new DenArrayList();

        int maxCount = 50;

        for (int i = 0; i < maxCount; i++) {
            listFirst.add("q" + i);
        }

        ListIterator<String> listIterator = listFirst.listIterator();
        for (int i = 0; i < maxCount; i++) {
            assertEquals("q" + i, listIterator.next());
        }

        for (int i = maxCount - 1; i >= 0; i--) {
            assertEquals("q" + i, listIterator.previous());
        }

    }

    @Test
    void test13() {
        List<String> listFirst = new DenArrayList();
        List<String> listSecond = new DenArrayList();

        int maxCount = 120;

        for (int i = 0; i < maxCount; i++) {
            listFirst.add("q" + (maxCount - 1 - i));
            listSecond.add("q" + (maxCount - 1 - i));
        }

        assertEquals(maxCount, listFirst.size());

        Collections.sort(listFirst,
                new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });

        Collections.sort(listSecond,
                new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });


        for (int i = 0; i < maxCount; i++) {
            assertEquals(listSecond.get(i), listFirst.get(i));
        }
    }

}