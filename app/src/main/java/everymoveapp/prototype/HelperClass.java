package everymoveapp.prototype;

public class HelperClass {
    String name, email, password, height, weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public HelperClass(String name, String email, String password, String height, String weight) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.height = height;
        this.weight = weight;
    }

    public HelperClass() {
    }
}
