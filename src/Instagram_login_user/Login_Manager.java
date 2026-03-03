
package Instagram_login_user;

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
    
    
}
