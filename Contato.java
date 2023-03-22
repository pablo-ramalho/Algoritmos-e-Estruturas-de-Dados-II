public class Contato{

    private String telefone, email;

    public Contato(String telefone, String email){
        this.telefone = telefone;
        this.email = email;
        
    }

    public String getTelefone(){
        return telefone;

    }

    public String getEmail(){
        return email;

    }

    @Override
    public String toString(){
        return "Contato [telefone=" + telefone + ", email=" + email + "]";
    
    }
    
}
