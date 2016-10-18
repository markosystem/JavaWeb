package controller;

import dao.ContatoDAO;
import factory.ContatoFactory;
import factory.TelefoneFactory;
import model.Contato;
import model.Telefone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Marcos on 18/10/2016.
 */
@ManagedBean
@ViewScoped
public class ContatoController {
    private Contato contato;
    private Telefone telefone;

    public void save(){
        if(ContatoDAO.save(getContato())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contato registrado com sucesso!", null));
            System.out.println("Contato registrado com sucesso!");
            setContato(null);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu um erro ao salvar o Contato, verifique o console!", null));
        }
    }

    public void delete(Contato contato){
        if(ContatoDAO.delete(contato)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contato excluído com Sucesso!", null));
            System.out.println("Contato excluído com Sucesso!");
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao Excluir o contato, verifique o console!", null));
        }
    }

    public void alterar(Contato contato) {
        setContato(contato);
    }

    public List<Contato> getList() {
        return ContatoDAO.getList();
    }


    public void removerNumero(Telefone telefone) {
        contato.getListTelefone().remove(telefone);
    }

    public void addNumero() {
        getContato().getListTelefone().add(getTelefone());
        setTelefone(null);
    }

    public Contato getContato() {
        if(contato == null)
            contato = ContatoFactory.initialize();
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Telefone getTelefone() {
        if(telefone == null)
            telefone = TelefoneFactory.initialize();
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
}
