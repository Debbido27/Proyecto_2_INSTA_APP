
package Instagram_login_user;

import Instagram_login_user.Base_cuenta.AccountStatus;
import Instagram_login_user.Base_cuenta.AccountType;
import Instagram_login_user.Base_cuenta.Gender;

public class Login_Manager {
    private static final int MAX_USERS = 50;
    
    private User[] users;
    private int totalUsers;
    private User currentUser;
    
    public Login_Manager(){
        users = new User[MAX_USERS];
        totalUsers=0;
        currentUser =null;
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
       
       currentUser = nuevo;
       return true;
       
    }
    
    public boolean login(String username, String password){
        User u = buscarUser(username);
        if(u != null && u.getPassword().equals(password) && u.getStatus()==AccountStatus.ACTIVE){
        currentUser = u;
        return true;
        }
        return false;
    }
    
    
    
    
    
    
}
