package factory;

import model.Telefone;

/**
 * Created by Marcos on 18/10/2016.
 */
public class TelefoneFactory {
    public static Telefone initialize(){
        Telefone telefone = new Telefone();
        return telefone;
    }
}
