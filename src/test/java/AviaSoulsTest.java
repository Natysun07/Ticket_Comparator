import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AviaSoulsTest {
    public static void main(String[] args) {
    }

    AviaSouls souls = new AviaSouls();

    Ticket ticket1 = new Ticket("Москва", "Пекин", 25_000, 15, 23);
    Ticket ticket2 = new Ticket("Москва", "Санкт-Петербург", 5_000, 11, 12);
    Ticket ticket3 = new Ticket("Казань", "Москва", 10_000, 6, 9);
    Ticket ticket4 = new Ticket("Санкт-Петербург", "Берлин", 18_000, 20, 23);
    Ticket ticket5 = new Ticket("Нижний Новгород", "Санкт-Петербург", 5_000, 12, 14);
    Ticket ticket6 = new Ticket("Москва", "Евпатория", 13_000, 14, 16);
    Ticket ticket7 = new Ticket("Москва", "Санкт-Петербург", 6_500, 14, 16);
    Ticket ticket8 = new Ticket("Москва", "Санкт-Петербург", 6_000, 9, 10);

    @BeforeEach
    public void setup() {
        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);
        souls.add(ticket7);
        souls.add(ticket8);
    }

    @Test
    public void compareTicketsByPrice1() {

        Assertions.assertEquals(-1, ticket2.compareTo(ticket4));
    }

    @Test
    public void compareTicketsByPrice2() {

        Assertions.assertEquals(1, ticket1.compareTo(ticket6));
    }

    @Test
    public void compareTicketsByPrice3() {

        Assertions.assertEquals(0, ticket2.compareTo(ticket5));
    }

    @Test
    public void shouldSortTicketsByPrice() {
        Ticket[] tickets = souls.findAll();
        Arrays.sort(tickets);

        Ticket[] expected = {ticket2, ticket5, ticket8, ticket7, ticket3, ticket6, ticket4, ticket1};
        Ticket[] actual = souls.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsByFlightTime1() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Assertions.assertEquals(1, timeComparator.compare(ticket1, ticket6));
    }

    @Test
    public void shouldCompareTicketsByFlightTime2() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Assertions.assertEquals(-1, timeComparator.compare(ticket2, ticket4));
    }

    @Test
    public void shouldCompareTicketsByFlightTime3() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Assertions.assertEquals(0, timeComparator.compare(ticket3, ticket4));
    }

    @Test
    public void shouldSortTicketsByFlightTime() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Ticket[] tickets = souls.findAll();
        Arrays.sort(tickets, timeComparator);

        Ticket[] expected = {ticket2, ticket8, ticket5, ticket6, ticket7, ticket3, ticket4, ticket1};
        Ticket[] actual = souls.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindFew() {
        Ticket[] expected = {ticket2, ticket8, ticket7};
        Ticket[] actual = souls.search("Москва", "Санкт-Петербург");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOne() {
        Ticket[] expected = {ticket1};
        Ticket[] actual = souls.search("Москва", "Пекин");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindNothing() {
        Ticket[] expected = {};
        Ticket[] actual = souls.search("Москва", "Шанхай");

        Assertions.assertArrayEquals(expected, actual);
    }

}
