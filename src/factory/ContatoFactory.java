package factory;

import model.Contato;
import model.Telefone;

import java.util.ArrayList;

/**
 * Created by Marcos on 18/10/2016.
 */
public class ContatoFactory {
    public static Contato initialize(){
        Contato contato = new Contato();
        contato.setListTelefone(new ArrayList<Telefone>());
        return contato;
    }
}
