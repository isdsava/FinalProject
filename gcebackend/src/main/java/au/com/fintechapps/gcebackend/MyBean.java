package au.com.fintechapps.gcebackend;


import au.com.fintechapps.javajoshingredux.Joshing;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = new Joshing().joshOn(1) ;
    }
}