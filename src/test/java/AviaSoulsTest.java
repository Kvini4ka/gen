
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.AviaSouls;
import ru.netology.Ticket;
import ru.netology.TicketTimeComparator;


import java.util.Comparator;

public class AviaSoulsTest {
    Ticket ticket1 = new Ticket("Moscow", "London", 30_000, 15, 19);
    Ticket ticket2 = new Ticket("Paris", "Moscow", 31_000, 23, 1);
    Ticket ticket3 = new Ticket("Moscow", "London", 5_000, 11, 13);
    Ticket ticket4 = new Ticket("Sochi", "Moscow", 30_000, 2, 6);
    Ticket ticket5 = new Ticket("Paris", "Moscow", 10_000, 15, 17);
    Ticket ticket6 = new Ticket("Moscow", "London", 22_000, 5, 11);
    Ticket ticket7 = new Ticket("Moscow", "London", 50_000, 23, 5);
    AviaSouls manager = new AviaSouls();

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
    }

    @Test
    public void testCompareToReturnsOneForHigherPriceTicket() {
        System.out.println(ticket1.compareTo(ticket5));

        int expected = 1;
        int actual = ticket1.compareTo(ticket5);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testCompareToReturnsMinusOneForLowerPriceTicket() {
        System.out.println(ticket1.compareTo(ticket2));

        int expected = -1;
        int actual = ticket1.compareTo(ticket2);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testCompareToReturnsZeroForEqualPriceTickets() {
        System.out.println(ticket1.compareTo(ticket4));

        int expected = 0;
        int actual = ticket1.compareTo(ticket4);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testSearchReturnsTicketsSortedByPrice() {
        Ticket[] expected = {ticket3, ticket6, ticket1, ticket7};
        Ticket[] actual = manager.search("Moscow", "London");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchReturnsSingleMatchingTicket() {
        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.search("Sochi", "Moscow");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchReturnsEmptyArrayWhenNoMatchingTicketsFound() {
        Ticket[] expected = {};
        Ticket[] actual = manager.search("Paris", "LA");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByReturnsTicketsSortedByFlightTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket3, ticket1, ticket6, ticket7};
        Ticket[] actual = manager.searchAndSortBy("Moscow", "London", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

}
