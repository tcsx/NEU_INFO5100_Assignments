import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyJson {
    private static ArrayList<Vehicle> readAndGetVehicles(File file) {
        ArrayList<Vehicle> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("~");
                list.add(new Vehicle(arr));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    public static String getJsonString(ArrayList<Vehicle> vehicles) {
        String dealer = vehicles.get(0).webId;
        StringJoiner sj = new StringJoiner(",\n");
        for (Vehicle v : vehicles) {
            sj.add(v.toString());
        }
        return "{\n" + "\"" + dealer + "\" : [\n" + sj + "\n]\n}";
    }

    public static void writeToFile(String inputToWrite, String filePath) {
        File file = new File(filePath, "vehicles.json");
        try {
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            pw.print(inputToWrite);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for extra credit.
     */
    public static void jsonToText(String filePath) {
        File inFile = new File(filePath, "vehicles.json");
        File outFile = new File(filePath, "original.txt");
        Pattern data = Pattern.compile("\"(\\w+)\"\\s\\:\\s\"?([^\",]*)\"?,?");
        Matcher m;
        // Pattern priceData = Pattern.compile("\"(\\w+)\"\\s\\:\\s\"(\\S*)\"," );
        // Pattern photoData = Pattern.compile("\"(\\w+)\"\\s\\:\\s\"(\\S*)\"," );
        try {
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            PrintWriter pw = new PrintWriter(new FileWriter(outFile, true));
            pw.println("id~webId~category~year~make~model~trim~type~price~photo");
            br.readLine();
            String webId = br.readLine().split("\"")[1];
            String line;
            while ((line = br.readLine()) != null) {
                if ((m = data.matcher(line)).matches()) {
                    String field = m.group(1);
                    String content = m.group(2);
                    if ("id".equals(field)) {
                        pw.print(content + "~" + webId + "~");
                    } else if ("category".equals(field)) {
                        pw.print(content.toLowerCase() + "~");
                    } else if ("photo".equals(field)) {
                        pw.println(content);
                    } else {
                        pw.print(content + "~");
                    }
                }
            }
            br.close();
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        File file = new File("Question3_camino.txt");
        ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
        String s = getJsonString(vehicles);
        writeToFile(s, file.getParent());
        jsonToText(file.getParent());
    }
}

class Vehicle {

    String id;
    String webId;
    Category category;
    Year year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    Vehicle(String[] arr) {
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Year.parse(arr[3]);
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8]);
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",\n");
        sj.add("\"id\" : \"" + id + "\"")
        .add("\"category\" : \"" + category + "\"")
        .add("\"year\" : \"" + year + "\"")
        .add("\"make\" : \"" + make + "\"")
        .add("\"model\" : \"" + model + "\"")
        .add("\"trim\" : \"" + trim + "\"")
        .add("\"type\" : \"" + type + "\"")
        .add("\"price\" : " + price)
        .add("\"photo\" : \"" + photo + "\"");
        return "{\n" + sj + "\n}";
    }
}

enum Category {
    NEW, USED, CERTIFIED;

    public static Category getCategory(String cat) {
        switch (cat) {
        case "used":
            return USED;
        case "new":
            return NEW;
        case "certified":
            return CERTIFIED;
        default:
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case NEW:
            return "NEW";
        case USED:
            return "USED";
        case CERTIFIED:
            return "CERTIFIED";
        default:
            throw new IllegalArgumentException();
        }
    }
} 
