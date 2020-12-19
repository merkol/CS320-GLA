public class Game{
    protected  String name;
    protected  String description;
    protected  String publisher;
    protected  float price;
    protected  float score;
    protected  boolean is_multi;

    public Game(String name,String description,String publisher,float price,float score,boolean is_multi){
        this.name=name;this.description=description;this.publisher=publisher; this.price=price; this.score=score;this.is_multi=is_multi;} 
    
    public String toString(){
        return this.name+"'s Features:\nDescription: "+this.description+", Publisher: "+this.publisher+", Price: "+this.price+", Score: "+this.score+", Multiplayer: "+this.is_multi;
    }

}



    
