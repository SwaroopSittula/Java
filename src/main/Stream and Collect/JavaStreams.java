import java.util.*;


public class JavaStreams {

    void StreamCreation() {
        int[] a = new int[5];

    }

    /**
     * Class Demonstrates conversion of a Hashset<Integer> to List<String> using
     * stream - Iterate each element
     * sorted - sort the elements
     * map - convert datatype or map output
     * collect - collect the outputs
     */
    void HashSetToList() {
        // Hashset does not sort the element by default. It just uses hash to store the element.
        HashSet<Integer> hs = new HashSet<>(Arrays.asList(10188, 2, 2, 2, 8184082, 1582110, 4, 5));
        System.out.println(hs);

        // Convert Hashset into a List of String with the elements in ascending order
        List<String> al = hs.stream()
                .sorted(Integer::compare)
                .map(Object::toString)
                .toList();  //.collect(Collectors.toList());
        System.out.println(al);
    }

    public static void main(String[] args) {

    }
}
