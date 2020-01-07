package cuong.sample.collections;

import java.util.Collections;
import java.util.List;

public class EmptyListTest {
    public static void main(String[] args) {
        var list = createEmptyList();
        list.add(11114);

    }

    private static List<Integer> createEmptyList(){
        return Collections.emptyList();
    }
}
