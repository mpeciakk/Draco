package mpeciakk;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void test_main() {
        assertDoesNotThrow(() -> Main.main(new String[] {}));
    }
}
