package model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="searchFilter")
public class SearchFilter {

    private String type;
    private  int expectedSum;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExpectedSum() {
        return expectedSum;
    }

    public void setExpectedSum(int expectedSum) {
        this.expectedSum = expectedSum;
    }
}
