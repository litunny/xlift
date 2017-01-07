package osinnowo.com.xlift.model;

/**
 * Created by upperlink on 06/01/2017.
 */

public class Person {
    private String FirstName ;
    private String LastName ;
    private String MiddleName ;

    public Person(String firstName, String lastName, String middleName) {
        FirstName = firstName;
        LastName = lastName;
        MiddleName = middleName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }
}
