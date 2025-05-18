import java.io.*;
import java.util.*;

import static java.lang.Long.parseLong;

public class Main {
    //hard code for genre files
    public static final String[] genre_Files_csv = {
            "Cartoons_Comics_Books.csv",
            "Hobbies_Collectibles_Books.csv",
            "Movies_TV.csv",
            "Music_Radio_Books.csv",
            "Nostalgia_Eclectic_Books.csv",
            "Old_Time_Radio.csv",
            "Sports_Sports_Memorabilia.csv",
            "Trains_Planes_Automobiles.csv"
    };

    //hardcode for binary files
    public static final String[] binary_Files_csv = {
            "Cartoons_Comics_Books.csv.ser",
            "Hobbies_Collectibles_Books.csv.ser",
            "Movies_TV.csv.ser",
            "Music_Radio_Books.csv.ser",
            "Nostalgia_Eclectic_Books.csv.ser",
            "Old_Time_Radio.csv.ser",
            "Sports_Sports_Memorabilia.csv.ser",
            "Trains_Planes_Automobiles.csv.ser"
    };

    //main method
    public static void main(String[] args) {
        do_part1();
        do_part2();
    }

    //method that checlks if every character in a string is a number
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    //method to remove brackets
    public static String removeBrackets(String original){
        String result;
        if (original.startsWith("[") && original.endsWith("]")){
            result = original.substring(1, original.length()-1);
        } else {result = original;}
        return result;
    }

    //method to log the different genres
    public static void logGenres(String[] fields, String genreFileName){
        //take the string array and convert into a string
        String originalDataFields = Arrays.toString(fields);

        //remove the square braces from the string
        String correctDataFields =  removeBrackets(originalDataFields);

        //add string to given genre
        try (PrintWriter pw = new PrintWriter(new FileWriter(genreFileName, true))) {
            pw.println(correctDataFields);
        }catch (IOException e) {
            System.out.println("There was an error printing the string to its genre");
        }

    }
    //Method that identifies the genre in string array and calls method to log the book
    public static void sendToGenre(String[] fields, String fileName){
        String genreCode = fields[4];
        String genreFileName;
        switch (genreCode){
            case "CCB":
                genreFileName = "Cartoons_Comics_Books.csv";
                logGenres(fields, genreFileName);
                break;
            case "HCB":
                genreFileName = "Hobbies_Collectibles_Books.csv";
                logGenres(fields, genreFileName);
                break;
            case "MTV":
                genreFileName = "Movies_TV.csv";
                logGenres(fields, genreFileName);
                break;
            case "MRB":
                genreFileName = "Music_Radio_Books.csv";
                logGenres(fields, genreFileName);
                break;
            case "NEB":
                genreFileName = "Nostalgia_Eclectic_Books.csv";
                logGenres(fields, genreFileName);
                break;
            case "OTR":
                genreFileName = "Old_Time_Radio.csv";
                logGenres(fields, genreFileName);
                break;
            case "SSM":
                genreFileName = "Sports_Sports_Memorabilia.csv";
                logGenres(fields, genreFileName);
                break;
            case "TPA":
                genreFileName = "Trains_Planes_Automobiles.csv";
                logGenres(fields, genreFileName);
                break;
            default:
                String[] error = new String[1];
                error[0] = ("invalid genre");
                String record = Arrays.toString(fields);
                logSyntaxError(error, record, fileName);
                break;
        }
    }

    //A method to check for the syntax errors and put them in one file and the others in respective genres
    public static void checkSyntaxErrors(String fileName, String[] modifiedString){
        String[] string_error_s = new String[7];
        int errorCounter = 0;
        boolean error = false;
        if (modifiedString.length < 6){
            //check if the last data field is a year
            if (isNumeric(modifiedString[modifiedString.length-1]) == false && modifiedString.length ==5){
                string_error_s[errorCounter] = "missing year";
                errorCounter++;
                String record = Arrays.toString(modifiedString);
                logSyntaxError(string_error_s, record, fileName );
            }
            error = true;
            try {
                throw new TooFewFieldsException();
            } catch (TooFewFieldsException e) {
                System.out.println("Too few fields");
            }
        } else if (modifiedString.length > 6) {
            error = true;
            try {
                throw new TooManyFieldsException();
            } catch (TooManyFieldsException e) {
                System.out.println("Too MANYYYY fields");
            }
        } else {
            for (int i = 0; i < modifiedString.length; i++) {
                if (modifiedString[i] == null || modifiedString[i] == " " || modifiedString[i] == ""){
                    error = true;
                    switch (i){
                        case 0:
                            string_error_s[errorCounter] = "missing title";
                            errorCounter++;
                            try {
                                throw new MissingFieldException();
                            } catch (MissingFieldException e) {
                                System.out.println("Missing Field: Title");
                            }
                            break;
                        case 1:
                            string_error_s[errorCounter] = "missing author";
                            errorCounter++;
                            try {
                                throw new MissingFieldException();
                            } catch (MissingFieldException e) {
                                System.out.println("Missing Field: Author");
                            }
                            break;
                        case 2:
                            string_error_s[errorCounter] = "missing price";
                            errorCounter++;
                            try {
                                throw new MissingFieldException();
                            } catch (MissingFieldException e) {
                                System.out.println("Missing Field: Price");
                            }
                            break;
                        case 3:
                            try {
                                string_error_s[errorCounter] = "missing isbn";
                                errorCounter++;
                                throw new MissingFieldException();
                            } catch (MissingFieldException e) {
                                System.out.println("Missing Field: ISBN");
                            }
                            break;
                        case 4:
                            try {
                                string_error_s[errorCounter] = "missing genre";
                                errorCounter++;
                                throw new MissingFieldException();
                            } catch (MissingFieldException e) {
                                System.out.println("Missing Field: Genre");
                            }
                            break;
                        case 5:
                            try {
                                string_error_s[errorCounter] = "missing year";
                                errorCounter++;
                                throw new MissingFieldException();
                            } catch (MissingFieldException e) {
                                System.out.println("Missing Field: Year");
                            }
                            break;
                    }
                }
            }
            if (error == false){
                sendToGenre(modifiedString,
                        fileName);
            } else {
                String record = Arrays.toString(modifiedString);
                logSyntaxError(string_error_s, record, fileName );
            }
        }
    }
    //method to log the syntax errors
    private static void logSyntaxError(String[]errors, String record, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("syntax_error_file.txt", true))) {
            writer.println("Syntax error in file: " + fileName);
            writer.println("====================");
            for (int i = 0; i< errors.length; i++){
                String errorToPrint = errors[i];
                if (errorToPrint != "" && errorToPrint!= null){
                writer.println("Error: " + errorToPrint);
                }
            }
            writer.println("Record: " + record);
            writer.println();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the syntax error file.");
            e.printStackTrace();
        }
    }

    //Method that reads what is in each book files
    public static void do_part1() {
        //The array with the book names (1995 - 2010
        String[] bookList = inputFileNames();

        //A counter for the number of the books
        int bookCounter = 0;
        //for loop to read within every books.csv.txt file and add them into the
        for (int i = 0; i < bookList.length; i++) {
            String s = bookList[i];
            System.out.println(s);
            try {
                Scanner bs = new Scanner(new FileInputStream(s));
                while (bs.hasNextLine()) {
                    String line = bs.nextLine();
                    String[] modifiedString = splitStringIntoFields(line);
                    checkSyntaxErrors(s, modifiedString);
                    bookCounter++;
                }
            } catch (FileNotFoundException e) {
                System.out.println("File  not found ");
            }
        }
    }

    //splitting of the strings into fields
    private static String[] splitStringIntoFields(String input) {
        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"; // Regex to split by commas not within quotes
        String[] splitFields = input.split(regex);
        String[] fields = new String[splitFields.length];
        int counter = 0;
        for (String field : splitFields) {
            // Trim whitespace and remove surrounding quotes if present
            field = field.trim();
            fields[counter] = field;
            counter++;
        }

        return fields;
    }

    //method that collects the input file names
    public static String[] inputFileNames() {
        String[] bookNames = null;
        try {
            Scanner books_Scanner = new Scanner(new FileInputStream("part1_input_file_names.txt"));
            int nOfBooks = books_Scanner.nextInt();
            bookNames = new String[nOfBooks];
            books_Scanner.nextLine();
            int counter = 0;
            while (books_Scanner.hasNextLine() && counter< bookNames.length) {
                String book_name = books_Scanner.nextLine();
                bookNames[counter]= book_name;
                counter++;
            }
            books_Scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found buddy");
        }
        return bookNames;
    }

    //method to do part 2
    public static void do_part2(){
        for (int i = 0; i < genre_Files_csv.length; i++) {
            String s = genre_Files_csv[i];
            System.out.println(s);
            try {
                Scanner gs = new Scanner(new FileInputStream(s));
                while (gs.hasNextLine()) {
                    String line = gs.nextLine();
                    String[] modifiedString = splitStringIntoFields(line);
                    checkForSemanticErrors(modifiedString, s);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File  not found ");
            }
        }
    }

    //method that checks for semantic errors
    public static void checkForSemanticErrors(String[] fields, String fileName){
        String[] errors = new String[5];
        String record = Arrays.toString(fields);
        boolean semantic_error = false;
        int errorCounter = 0;
        double price = Double.parseDouble(fields[2]);
        int year = Integer.parseInt(fields[5]);

        if (price<0){
            semantic_error = true;
            errors[errorCounter] = "invalid price";
            errorCounter++;
            try {
                throw new BadPriceException();
            } catch (BadPriceException e) {
                System.out.println("invalid price");
            }
        }
        if (year<1995 || year>2010){
            semantic_error = true;
            errors[errorCounter] = "invalid year";
            errorCounter++;
            try {
                throw new BadYearException();
            } catch (BadYearException e) {
                System.out.println("invalid year");
            }
        }
        String isbnString = fields[3];
        if (!verify_isbn(isbnString, errors, fileName, record)){
            logSemanticErrors(errors, fileName, record);
            errorCounter++;
        }
        if (errorCounter == 0){
            createBook(fields, fileName);
        }
    }

    //method that verifies  the isbn
    public static boolean verify_isbn(String isbnString, String[] errors, String fileName, String record){
        int errorLength = errors.length - 1;
        boolean booleanIsbn = true;
        int result = 0;
        int multiple = 10;
        int isbnLength = isbnString.length();
        if (!isNumeric(isbnString)){
            System.out.println(record + "\n" +"There is a character in the isbn");
            booleanIsbn = false;
            errors[errorLength] = "Character in isbn";
            logSemanticErrors(errors, record, fileName);
        }else {
            switch (isbnLength) {
                case 10:
                    for (int i = 0; i < 10; i++) {
                        char isbnChar = isbnString.charAt(i);
                        int isbnInt = Integer.parseInt(String.valueOf(isbnChar));
                        result = (isbnInt * multiple) + result;
                        multiple--;
                    }
                    if (result % 11 == 0) {
                        booleanIsbn = true;
                    } else {
                        String error = "invalid isbn 10";
                        errors[errorLength] = error;
                        errorLength--;
                        logSemanticErrors(errors, fileName, record);
                        try {
                            throw new BadIsbn10Exception();
                        } catch (BadIsbn10Exception e) {
                            System.out.println("invalid isbn 10");
                        }
                        booleanIsbn = false;
                    }
                    break;
                case 11:
                    multiple = 1;
                    for (int i = 0; i < 10; i++) {
                        if (i % 2 == 0) {
                            multiple = 1;
                        } else {
                            multiple = 3;
                        }
                        char isbnChar = isbnString.charAt(i);
                        int isbnInt = Integer.parseInt(String.valueOf(isbnChar));
                        result = (isbnInt * multiple) + result;
                    }
                    if (result % 10 == 0) {
                        booleanIsbn = true;
                    } else {
                        String error = "invalid isbn 13";
                        errors[errorLength] = error;
                        errorLength--;
                        logSemanticErrors(errors, fileName, record);
                        try {
                            throw new BadIsbn13Exception();
                        } catch (BadIsbn13Exception e) {
                            System.out.println("invalid isbn 13");
                        }
                        booleanIsbn = false;
                    }
                    break;
                default:
                    try {
                        throw new IllegalStateException("Unexpected value: " + isbnLength);
                    }
                    catch(IllegalStateException e){
                        System.out.println(e.getMessage());
                }
            }
        }
        return booleanIsbn;
    }

    //method to log semantic errors
    public static void logSemanticErrors(String[] errors, String record, String fileName){
        try (PrintWriter writer = new PrintWriter(new FileWriter("semantic_error_file.txt", true))) {
            writer.println("Semantic error in file: " + fileName);
            writer.println("====================");
            for (int i = 0; i< errors.length; i++){
                String errorToPrint = errors[i];
                if (errorToPrint != "" && errorToPrint!= null){
                    writer.println("Error: " + errorToPrint);
                }
            }
            writer.println("Record: " + record);
            writer.println();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the syntax error file.");
        }
    }

    //method that creates a new book and adds it o a .ser file
    public static void createBook(String[] fields, String filename) {
        //creation of book object
        String title = fields[0];
        String author = fields[1];
        double price = Double.parseDouble(fields[2]);
        long isbn = parseLong(fields[3]);
        String genre = fields[4];
        int year = Integer.parseInt(fields[5]);
        Book book = new Book(title, author, price, isbn, genre, year);

        //send book to serializable file
        String binaryFileName = filename+".ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binaryFileName))) {
            oos.writeObject(book);
        } catch (FileNotFoundException e) {
            System.out.println("File Not found");
        } catch (IOException e) {
            System.out.println("error writing the book to a binary file");
        }
    }

}

