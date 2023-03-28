package readPropertyFiles;

import commons.GlobalConstants;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;



public class ReadDataJson {

    public static ReadDataJson get(String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(fileName), ReadDataJson.class);
        } catch (IOException e) {
            throw new RuntimeException(("[ReadDataJson][get]" + e.getMessage()));
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    @JsonProperty("userName")
    String userName;
    @JsonProperty("email")
    String email;
    @JsonProperty("password")
    String password;

    public ListUsers getListUsers() {
        return listUsers;
    }

    @JsonProperty
    ListUsers listUsers;

    static class ListUsers{
        @JsonProperty
        String Age;
        @JsonProperty
        String Address;

        public String getAge() {
            return Age;
        }

        public String getAddress() {
            return Address;
        }
    }


    public static void main(String[] args) {
        ReadDataJson readDataJson = ReadDataJson.get(GlobalConstants.DATA_JSON);
        System.out.println(readDataJson.getEmail());
        System.out.println(readDataJson.getListUsers().getAge());

    }
}
