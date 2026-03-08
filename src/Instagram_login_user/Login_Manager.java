
package Instagram_login_user;

import Instagram_login_user.Base_cuenta.AccountStatus;
import Instagram_login_user.Base_cuenta.AccountType;
import Instagram_login_user.Base_cuenta.Gender;
import java.io.File;

public class Login_Manager {
    private static final int MAX_USERS = 50;
    AccountStatus status;
    private User[] users;
    private int totalUsers;
    private User currentUser;
    private static final String BASE_FOLDER = "instagram_data";
    public Login_Manager(){
        users = new User[MAX_USERS];
        totalUsers=0;
        currentUser =null;
        this.status=status;
    }
    
    public boolean UserExiste(String username){
        for(User p : users){
             if(p != null && p.getUsername().equals(username)){
              return true;   
             }
        }
        
        return false;
    }
    
    
    public User buscarUser(String username){
        for(User p : users){
            if(p.getUsername().equals(username)){
               return p ;
            }
        }
        return null;
    }
    
    
    public boolean crearUser (String username, String password, String fullname, Gender gender, int age, AccountType accountType){
       if(buscarUser(username)!= null){
           return false;
       }
       
       if(totalUsers>= MAX_USERS){
           return false;
       }
       
       User nuevo = new User(username, password, fullname, gender, age, accountType);
       
       users[totalUsers] = nuevo;
       totalUsers++;
       CrearFolder(username);
       currentUser = nuevo;
       return true;
       
    }
    
    public boolean userActive(String username){
        User u = buscarUser(username);
        
        if(u!=null){
            return u.getStatus()==AccountStatus.ACTIVE;
        }
        
        return false;
    }
    
    public boolean login(String username, String password){
        User u = buscarUser(username);
        if(u != null && u.getPassword().equals(password) && u.getStatus()==AccountStatus.ACTIVE){
            
            File userFolder = new File(BASE_FOLDER+"/"+username);
        currentUser = u;
        return true;
        }
        return false;
    }
    

    private void CrearFolder(String username){
        File base = new File(BASE_FOLDER);
        if(!base.exists()){
            base.mkdir();
        }
        
        File userFolder = new File(base,username);
        userFolder.mkdir();
        
        File profile = new File (userFolder,"profile");
        File stickers = new File(username,"stickers");
        File posts = new File(userFolder,"posts");
        
        profile.mkdir();
        stickers.mkdir();
        posts.mkdir();
    }
 
    public String loginValidar(String username){
        if(UserExiste(username)){
            return "LOGIN";
        }else{
      return "REGISTRO";       
    }
    }
    
    
    public void setFotoPerfil(String username, String rutaFoto){
        File profileFolder = new File(BASE_FOLDER+"/"+username+".profile");
        
        if(profileFolder.exists()){
            User u = buscarUser(username);
            if(u!= null){
                u.setProfilePath(rutaFoto);
            }
        }
    }
    
    public boolean eliminarUsuario(String username){
        for (int i = 0; i < totalUsers; i++) {
            if(users[i]!=null && users[i].getUsername().equals(username)){
                File userFolder = new File(BASE_FOLDER+"/"+username);
                borrarCarpeta(userFolder);
                
                for (int j = 0; j < totalUsers-1; j++) {
                    users[j]=users[j+1];
                }
                
                users[totalUsers-1]=null;
                totalUsers--;
                return true;
            }
        }
        return false;
    }
    
    
    //METODO RECURSIVO
    private void borrarCarpeta(File folder){
        if(folder.isDirectory()){
            File[]files=folder.listFiles();
            
            if(files!=null){
                for(File f :files){
                    borrarCarpeta(f);
                }
            }
        }
        folder.delete();
    }
    
}
    
    
    
    
 

