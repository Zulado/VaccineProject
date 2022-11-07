
public class citizen {
    private String name;
    private String ic;
    private String state;
    private int age;
    private String category;
    private String stat1stDose;
    private String stat2ndDose;
    private String certificate;


    public citizen() {
        this.name = null;
        this.ic = null;
        this.state = null;
        this.age = 0;
        this.category = null;
    }

    public citizen(String name, String ic, String state, int age, String category, String stat1stDose, String stat2ndDose, String certificate) {
        this.name = name;
        this.ic = ic;
        this.state = state;
        this.age = age;
        this.category = category;
        this.stat1stDose = stat1stDose;
        this.stat2ndDose = stat2ndDose;
        this.certificate = certificate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStat1stDose(String status) {
        this.stat1stDose = status;
    }

    public void setStat2ndDose(String status) {
        this.stat2ndDose = status;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getName() {
        return(this.name);
    }

    public String getIc() {
        return(this.ic);
    }

    public String getState() {
        return(this.state);
    }

    public int getAge() {
        return(this.age);
    }

    public String getCategory() {
        return(this.category);
    }

    public String getStat1stDose() {
        return(this.stat1stDose);
    }

    public String getStat2ndDose() {
        return(this.stat2ndDose);
    }

    public String getCertificate() {
        return(this.certificate);
    }

    public String toString() {
        return "Name: " + this.name + "\nIc: " + this.ic + "\nState: " + this.state + "\nAge: " + this.age + "\nCategory: " + this.category + "\n1st Dose: " + this.stat1stDose + "\n2nd Dose: " + this.stat2ndDose + "\nCertificate: " + this.certificate;
    }
}