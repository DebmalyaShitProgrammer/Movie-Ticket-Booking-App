/**
 * A Movie ticket booking app
 *
 * Debmalya Bose
 * 3.11.2024
 */
import java.util.*;
import java.io.*;

public class MovieTicket
{
    static boolean[][] hall = new boolean [10][20];
    static int price[] = {0, 550, 350, 350, 350, 350, 350, 350, 250, 250, 250};
    public static void main(String args[])
    {
        updateHall();
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            printMenu();
            int choice = sc.nextInt();
            System.out.println();
            switch(choice)
            {
                case 1:
                    displayHall();
                    System.out.println();
                    billSystem();
                    break;
                case 2:
                    return;
                default:
                    System.out.print("Invalid Choice");
            }
            System.out.println();
        }
    }
    
    static void printMenu()
    {
        System.out.println("1. Book Tickets");
        System.out.println("2. Exit");
        System.out.print("Enter an option: ");
    }
    
    static void displayHall()
    {
        char row = 'A';
        System.out.println("  01 02 03 exit 04 05 06 07 08 09 10 exit 11 12 13 14 15 16 17 exit 18 19 20");
        System.out.println();
        for(int i = 0; i < hall.length; i++)
        {
            System.out.print(row + " ");
            for(int j = 0; j < hall[i].length; j++)
            {
                if(hall[i][j])
                    System.out.print("<>");
                else
                    System.out.print("[]");
                System.out.print(" ");
                
                if(j == 2 || j == 9 || j == 16)
                    System.out.print("     ");
            }
            if(row == 'A')
            {
                System.out.print("\tRs.550");
                System.out.println();
            }
            else if(row == 'D')
            {
                System.out.println();
                System.out.print("                                                                            \tRs.350");
            }
            else if(row == 'I')
            {
                System.out.print("\tRs.250");
                System.out.println();   
            }
            else
                System.out.println();
            if(row == 'A' || row == 'G')
                System.out.println();
            System.out.println();
            row++;
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("          _________________________________________________________");
        System.out.println("                                    screen");
        System.out.println("<> Booked");
        System.out.println("[] Not Booked");
    }
    
    static void billSystem()
    {
        Scanner sc = new Scanner(System.in);
        int seats, bill = 0;
        System.out.print("How many seats do you want to book: ");
        seats = sc.nextInt();
        String[] booked = new String[seats];
        
        System.out.print("Enter name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        for (int i = 0; i < seats;)
        {
            System.out.print("Enter seat number: ");
            String preferredSeat = sc.next();

            int row = preferredSeat.substring(0, 1).toUpperCase().charAt(0) - 65;
            int col = Integer.parseInt(preferredSeat.substring(1)) - 1;
        
            boolean seatAvailable = !hall[row][col];

            if (seatAvailable)
            {
                booked[i] = preferredSeat;
                bill += price[row + 1];
                hall[row][col] = true;
                updateFile(preferredSeat);
                i++;
            }
            else
                System.out.println("Seat not available. Try another seat.");
        }

        System.out.println("\nBooking Summary:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.print("Seats: ");
        for (int i = 0; i < seats; i++)
        {
            if(i == seats-1)
                System.out.println(booked[i]);
            else
                System.out.print(booked[i] + ", ");
        }
        System.out.println("Total Amount: " + bill);
    }


    static void updateFile(String seat)
    {
        try
        {
            FileWriter file = new FileWriter("seating hall.txt", true);
            file.write(seat + "\n");
            file.close();
        }
        catch (IOException e)
        {
            System.out.println("Unable to access file");
            e.printStackTrace();
        }
    }

    
    static void updateHall()
    {
        File file = new File("seating hall.txt");
        try
        {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                String seat = sc.nextLine();
                hall[seat.substring(0, 1).toUpperCase().charAt(0)-65][Integer.parseInt(seat.substring(1))-1] = true;
            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}