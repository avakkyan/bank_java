package Entites.Clients;

public class ClientBuilder {
    private String firstName;
    private String lastName;
    private String address;
    private String passport;
    public ClientBuilder addFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public ClientBuilder addLastName(String lastName){
        this.lastName = lastName;
        return this;
    }
    public ClientBuilder addAddress(String address){
        this.address = address;
        return this;
    }
    public ClientBuilder addPassport(String passport){
        this.passport = passport;
        return this;
    }
    public Client toBuild(){
        Client client = new Client(firstName,lastName);
        return client;
    }
}
