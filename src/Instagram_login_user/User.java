
package Instagram_login_user;

import Instagram_login_user.Base_cuenta.AccountStatus;
import Instagram_login_user.Base_cuenta.AccountType;
import Instagram_login_user.Base_cuenta.Gender;
import java.time.LocalDate;


public class User {
   String username;
   String password;
   String fullname;
    int age;
    LocalDate registerDate;
    Gender gender;
   AccountStatus status;
   AccountType accountType;
   String profilePath;
   private long creationDate;
  
    
    
    
    
   public User(String username, String password,String fullname, Gender gender, int age, AccountType accountType){
       this.username=username;
       this.password=password;
       this.fullname=fullname;
       this.gender=gender;
       this.age=age;
       this.accountType = accountType;
       this.registerDate = LocalDate.now();
       this.status = AccountStatus.ACTIVE;
       this.profilePath="";
   }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }


   
  @Override
  public String toString(){
      return "Usuario: "+username+"\nContrasena: "+password+"\n";
      
  }
         
public void setCreationDate(long date){ this.creationDate = date; }
public long getCreationDate(){ return this.creationDate; }  
}
