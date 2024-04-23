package cinema;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class Cinema {

    public int rows;
    public int seatsPerRow;
    public int seats;
    public int price;
    public int ticketRow;
    public int ticketSeat;
    public int currentIncome;
    public int maxIncome;
    public int ticketCount;
    public double percentage;
    public boolean seat;
    Scanner scanner = new Scanner(System.in);
    ArrayList<Integer> array = new ArrayList<>(seats);

    /* public void cinemaPrint() {
        System.out.println("""
                Cinema:
                  1 2 3 4 5 6 7 8
                1 S S S S S S S S
                2 S S S S S S S S
                3 S S S S S S S S
                4 S S S S S S S S
                5 S S S S S S S S
                6 S S S S S S S S
                7 S S S S S S S S
                """);
    } */

    public void methods() {
        dataInput();
        totalIncome();
        menu();
        //cinemaPrintLoop(rows, seatsPerRow);
        //totalIncome();
        //oneTicket();
        //cinemaPrintLoop(rows, seatsPerRow);
        //ticketPrice(ticketRow);
    }

    public void showSeats() {
        cinemaPrintLoop(rows, seatsPerRow);
        menu();
    }

    public void buyTicket() {
        oneTicket();
        cinemaPrintLoop(rows, seatsPerRow);
        ticketPrice(ticketRow);
        menu();
    }

    public void statistics() {

        percentage = (double) ticketCount / seats * 100;

        System.out.println("Number of purchased tickets: " + ticketCount);
        System.out.printf("Percentage: %.2f%% %n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + maxIncome + "\n");

        menu();
    }

    public void menu() {
        System.out.println("1. Show the seats ");
        System.out.println("2. Buy a ticket ");
        System.out.println("3. Statistics ");
        System.out.println("0. Exit ");


        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> showSeats();
            case 2 -> buyTicket();
            //case 0 -> System.exit(0);
            case 3 -> statistics();
            case 0 -> {
                return;
            }
            default -> {
                System.out.println("That is not a valid choice. Please try again.");
                menu();
            }
        }
    }

    public void dataInput() {
        System.out.println("Enter the number of rows: ");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        seatsPerRow = scanner.nextInt();
        seats = rows * seatsPerRow;
        currentIncome = 0;
        ticketCount = 0;
        percentage = 0;
    }

    public void oneTicket() {
        System.out.println("Enter a row number: ");
        ticketRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        ticketSeat = scanner.nextInt();

        String chosenSeat = String.valueOf(ticketRow) + String.valueOf(ticketSeat);
        int newSeat = Integer.parseInt(chosenSeat);

        boolean present = array.contains(newSeat);

        if (ticketCount == seats) {
            System.out.println("The cinema is sold out!\n");
            System.exit(0);
            return;
        } else if (present) {
            System.out.println("That ticket has already been purchased!\n");
            oneTicket();
        } else if ((ticketRow > rows || ticketSeat > seatsPerRow) || (ticketRow < 1 || ticketSeat < 1)) {
            System.out.println("Wrong input!\n");
            oneTicket();
        }
    }

    public void cinemaPrintLoop(int rows, int seatsPerRow) { // povodne void namiesto string
        System.out.println("Cinema: ");
        for (int i = 0; i <= rows; i++) {

            if (i == 0) {
                for (int k = 0; k <= seatsPerRow; k++) {
                    if (k == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print(k + " ");
                    }
                }
                System.out.println();

            } else {
                System.out.print(i + " ");
                for (int j = 0; j < seatsPerRow; j++) {
                    seat = false;
                    indicateSeat(i, j);
                        if (seat) {
                            continue;
                        }
                        System.out.print("S ");
                }
                System.out.println();
            }

        }
        System.out.println();
    }

    public void indicateSeat(int i, int j) {
        if ((ticketRow != 0) && (ticketSeat != 0)) { // this will have to include OR int array != 0

            String reservedSeat = String.valueOf(i) + String.valueOf(j);
            int intSeat = Integer.parseInt(reservedSeat) + 1;
            boolean present = array.contains(intSeat);

            if ((ticketRow == i) && ( (ticketSeat - 1) == j) && (!present)) { // and string doesnt equal any of the already collected int array of booked seats [ij],[ij],[ij]
                System.out.print("B ");
                array.add(intSeat);
                ticketCount++;
                // add this combination string = "i+j", valueOf "i+j" to int, add int to array
                seat = true;
            } else if ((ticketRow == i) && ( (ticketSeat - 1) == j) || (present)) {
                System.out.print("B ");
                seat = true;
            }

        }
    }

    public void totalIncome() {
        seats = rows * seatsPerRow;
        price = 0;
        int frontSeat = 10;
        int backSeat = 8;
        int halfRows = rows / 2;
        if (seats <= 60) {
            price = seats * 10;
        } else if (seats > 60) {
            if (rows % 2 == 0) {
                int count = (halfRows * frontSeat * seatsPerRow) + (halfRows * backSeat * seatsPerRow);
                price = count;
            } else {
                price = (((rows - 1) / 2) * frontSeat * seatsPerRow) + (((rows + 1) / 2) * backSeat * seatsPerRow);
            }
        }
        //System.out.println("Total income: \n$" + price);
        maxIncome = price;
    }

    public void ticketPrice(int ticketRow) {
        if (seats <= 60) {
            price = 10;

        } else {
            if ( (rows % 2 == 0) && (ticketRow <= (rows / 2)) ) { // even and front part
                price = 10;
            } else if ((rows % 2 == 1) && (ticketRow < ( (rows + 1) / 2) )) { // odd and front part
                price = 10;
            } else {
                price = 8;
            }
        }
        System.out.println("Ticket price: \n$" + price + "\n");
        currentIncome = currentIncome + price;
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.methods();

    }
}

