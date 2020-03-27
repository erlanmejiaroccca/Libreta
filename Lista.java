
import data.Libreta;
import data.LibretaData;


public class Lista {
    public static void main(String[] args) {
        LibretaData LibretaData= new LibretaData();
        
        Libreta c = new Libreta();
        c.setId(1);
        c.setNombre("Erlan Mejia");
        c.setCelular("88888");
        LibretaData.create(c);

        Libreta c2 = new Libreta();
        c2.setId(2);
        c2.setNombre("Rosa Garcia");
        c2.setCelular("88888");
        LibretaData.create(c2);
        
        LibretaData.delete(c);
        System.out.println("Lista de libretas");
        for(Libreta d:LibretaData.list() ){
            System.out.println(d.getId() + "\t"+d.getNombre()); 
        }
        
    }
}