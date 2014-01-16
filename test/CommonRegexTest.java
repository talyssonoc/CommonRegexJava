
import commonregex.CommonRegex;
import commonregex.languages.UnsupportedLanguageException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author talyssonoc
 */
public class CommonRegexTest {

    String text = "John, please get that article on www.linkedin.com to me by 5:00PM\n"
            + "on Jan 9th 2012. 4:00 would be ideal, actually. If you have any questions,\n"
            + "you can reach my associate at (012)-345-6789 or associative@mail.com.\n"
            + "I\'ll be in UK during the whole week at a J.R.R. Tolkien convention.";

    CommonRegex commonRegex;

    public CommonRegexTest() {
        this.commonRegex = new CommonRegex(text);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void dateTest() {
        String[] result = new String[]{"Jan 9th 2012"};
        assertArrayEquals(result, commonRegex.getDates());
    }

    @Test
    public void timeTest() {
        String[] result = new String[]{"5:00PM", "4:00"};
        assertArrayEquals(result, commonRegex.getTimes());
    }

    @Test
    public void phoneTest() {
        String[] result = new String[]{"(012)-345-6789"};
        assertArrayEquals(result, commonRegex.getPhones());
    }

    @Test
    public void linkTest() {
        String[] result = new String[]{"www.linkedin.com"};
        assertArrayEquals(result, commonRegex.getLinks());
    }

    @Test
    public void emailTest() {
        String[] result = new String[]{"associative@mail.com"};
        assertArrayEquals(result, commonRegex.getEmails());
    }

    @Test
    public void IPv4Test() {
        String[] result = new String[]{"127.0.0.1"};
        assertArrayEquals(result, CommonRegex.getIPv4("The IPv4 address for localhost is 127.0.0.1, but not 127.1"));
    }

    @Test
    public void IPv6Test() {
        String[] result = new String[]{"0:0:0:0:0:0:0:1", "::1"};
        assertArrayEquals(result, CommonRegex.getIPv6("The IPv6 address for localhost is 0:0:0:0:0:0:0:1, or alternatively, ::1, but not :1."));
    }

    @Test
    public void hexColorTest() {
        String[] result = new String[]{"#ff6600"};
        assertArrayEquals(result, CommonRegex.getHexColors("Did you knew that Hacker News orange is #ff6600?"));
    }

    @Test
    public void acronymTest() {
        String[] result = new String[]{"UK", "J.R.R."};
        assertArrayEquals(result, commonRegex.getAcronyms());
    }

    @Test
    public void moneyTest() {
        String[] result = new String[]{"US$5,000.90", "US$3,900.5", "$1100.4"};
        assertArrayEquals(result, CommonRegex.getMoney("They said the price was US$5,000.90, actually it is US$3,900.5. It\'s $1100.4 less, can you imagine this?"));
    }

    @Test(expected = UnsupportedLanguageException.class)
    public void moneyExTest() {
        CommonRegex.getMoney("de_DE","They said the price was US$5,000.90, actually it is US$3,900.5. It\'s $1100.4 less, can you imagine this?");
    }

    @Test
    public void moneyStringTest() {
        String[] result = new String[]{"US$5,000.90", "US$3,900.5", "$1100.4"};
        assertArrayEquals(result, CommonRegex.getMoney("en_US", "They said the price was US$5,000.90, actually it is US$3,900.5. It\'s $1100.4 less, can you imagine this?"));
    }

    @Test
    public void percentageTest() {
        String[] result = new String[]{"99.9999999%", "5%"};
        assertArrayEquals(result, CommonRegex.getPercentages("I\'m 99.9999999% sure that I\'ll get a raise of 5%."));
    }

    @Test
    public void percentageStringTest() {
        String[] result = new String[]{"99.9999999%", "5%"};
        assertArrayEquals(result, CommonRegex.getPercentages("en_US", "I\'m 99.9999999% sure that I\'ll get a raise of 5%."));
    }

    @Test(expected = UnsupportedLanguageException.class)
    public void percentageStringExTest() {
        CommonRegex.getPercentages("de_DE", "I\'m 99.9999999% sure that I\'ll get a raise of 5%.");
    }
}
