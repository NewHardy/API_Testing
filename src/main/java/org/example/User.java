package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class User
{
    private int id;
    private String name;
    private String username;
    private String email;

    public User() {
    }

    private static class Address
    {
        private String street;
        private String suite;
        private String city;
        private String zipcode;

        public Address(String street, String suite, String city, String zipcode, Geo geo) {
        }

        private static class Geo
        {
            private String lat;
            private String lng;

            public Geo(String lat, String lng) {
            }
        }
        private Geo geo;
    }
    private Address address;
    private String phone;
    private String website;
    private static class Company
    {
        private String name;
        private String catchPhrase;
        private String bs;

        public Company(String name, String catchPhrase, String bs) {
        }
    }
    private Company company;
    private static ArrayList<User> database = new ArrayList<>();
    private static Gson gson = new Gson();
    static private final String FILE_PATH ="src/main/java/org/example/File.json";
    static private File file= new File(FILE_PATH);
    private static final String LOCATOR = "https://jsonplaceholder.typicode.com/users";


    public static int getId(User user)
    {
        return user.id;
    }
    public static String getUserName(User user)
    {
        return user.username;
    }

    public static void getHTML () throws IOException
    {
        URL url = new URL(LOCATOR);
        HttpURLConnection connector = (HttpURLConnection) url.openConnection();
        connector.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(connector.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while((inputLine=br.readLine())!=null)
        {
            sb.append(inputLine).append("\n");
        }
        br.close();
        String jsonString = gson.toJson(inputLine);
        try(FileWriter fw = new FileWriter(file))
        {
            fw.write(jsonString);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public static void postNewJson(User user)
    {

    }
    public static ArrayList<User> deleteUser(int id)
    {
        return new ArrayList<>();
    }
    public static void getJson()
    {
        try (Scanner scanner= new Scanner(file))
        {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext())
            {
                sb.append(scanner.nextLine());
            }
            String json=sb.toString();
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            String test= new Gson().fromJson(json,userListType);
            System.out.println(test);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public static User getUserByName(String username)
    {
        for (int i = 0; i < database.size(); i++)
        {
            if (User.getUserName(database.get(i)).equals(username))
            {
                return database.get(i);
            }
        }
        return new User();
    }
    public static User getUserById(int id)
    {
        for (int i = 0; i < database.size(); i++)
        {
            if (User.getId(database.get(i))==(id))
            {
                return database.get(i);
            }
        }
        return new User();
    }

    public static void main(String[] args) throws IOException {
        User newUser =new User();
        getHTML();
    }
}
