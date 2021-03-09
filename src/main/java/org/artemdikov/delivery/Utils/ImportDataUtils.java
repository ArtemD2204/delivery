package org.artemdikov.delivery.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class ImportDataUtils {

    public void addCompaniesFromDataFile() throws Exception {
        StringBuilder query = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data.csv")))) {
            String str = br.readLine();
            while ((str=br.readLine()) != null) {
                String[] strArr = str.split(",");
                Double price = Double.valueOf(strArr[4].substring(0, strArr[4].length()-1));
                Integer time = converToHours(strArr[5]);
                query.append("INSERT INTO company (id, name, route_part, pickup_city, delivery_city, price, time) VALUES (")
                        .append("nextval('company_seq'), '").append(strArr[0]).append("', '").append(strArr[1]).append("', '").append(strArr[2]).append("', '")
                        .append(strArr[3]).append("', ").append(price).append(", ").append(time).append(");")
                        .append("\n");
            }
        }
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/delivery")){
            Statement statement = connection.createStatement();
            statement.executeUpdate(query.toString());
        }
    }

    private Integer converToHours(String time) throws Exception {
        String unit = time.substring(time.length()-1);
        Integer hoursNum = Integer.valueOf(time.substring(0, time.length()-1));
        if(unit.equals("d")) {
            hoursNum *= 24;
        } else if(!unit.equals("h")) {
            throw new Exception("Unknown unit of time: " + unit);
        }
        return hoursNum;
    }

}
