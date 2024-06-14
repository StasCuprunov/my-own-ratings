package en.ratings.own.my.test.utility.asserts;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertThatUtility {

    public static void assertThatIsNotNull(Object object) {
        assertThat(object).isNotEqualTo(null);
    }

    public static void assertThatIdIsDefined(String id) {
        assertThat(id).isNotNull().isNotBlank().isNotEmpty();
    }

}
