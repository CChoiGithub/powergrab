package uk.ac.ed.inf.powergrab;

public class ParseInput {

    String dd;
    String mm;
    String yyyy;
    Position p = new Position(0,0);
    int seed;
    String type;

    //constructor
    public ParseInput(String[] inputString){
        this.dd = inputString[0];
        this.mm = inputString[1];
        this.yyyy = inputString[2];
        this.p.latitude = Double.parseDouble(inputString[3]);
        this.p.longitude = Double.parseDouble(inputString[4]);
        this.seed = Integer.parseInt(inputString[5]);
        this.type = inputString[6];
    }
    public String getDD(){
        return this.dd;
    }
    public String getMM(){
        return this.mm;
    }
    public String getYYYY(){
        return this.yyyy;
    }
    public Position getP(){
        return this.p;
    }
    public int getSeed(){
        return this.seed;
    }
    public String getType(){
        return this.type;
    }
}
