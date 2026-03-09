
package Instagram_login_user;

import Instagram_login_user.Base_cuenta.AccountStatus;
import Instagram_login_user.Base_cuenta.AccountType;
import Instagram_login_user.Base_cuenta.Gender;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Login_Manager {
    AccountStatus status;
    private RandomAccessFile usersFile;
    private User currentUser;
    private static final String BASE_FOLDER = "INSTA_RAIZ";
    File base = new File(BASE_FOLDER);
    public Login_Manager(){
        if(!base.exists()){
       base.mkdir();
        }
        
       try{
       usersFile = new RandomAccessFile(BASE_FOLDER+"/users.ins","rw");
       
       currentUser=null;
       limpiarUsuariosHuerfanos();
       }catch(IOException e){
           System.out.println("Error creando archivo de usuarios");
       }
    }
    
    public boolean UserExiste(String username) throws IOException{
        usersFile.seek(0);
        try {
            while(usersFile.getFilePointer()<usersFile.length()){
                String user = usersFile.readUTF();
                usersFile.readUTF();
                usersFile.readUTF();
                usersFile.readUTF();
                usersFile.readInt();
                usersFile.readLong();
                usersFile.readUTF();
                usersFile.readUTF();
                usersFile.readUTF();
                
                if(user.equals(username)){
                    return true;
                }
            }
        }catch(IOException e){
            System.out.println("Error Leyendo usuario");
        }
        return false;
    }
    
    
    
    
    public User buscarUser(String username){

    try{

        usersFile.seek(0);

        while(usersFile.getFilePointer() < usersFile.length()){

            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();

            if(user.equals(username)){

                User u = new User(user,pass,fullname,
                        Gender.valueOf(gender),
                        age,
                        AccountType.valueOf(type));

                u.setStatus(AccountStatus.valueOf(status));
                u.setProfilePath(profile);

                return u;
            }

        }

    }catch(IOException e){
        System.out.println("Error buscando usuario");
    }

    return null;
}
    
    
    
    
    public boolean crearUser (String username, String password, String fullname,
        Gender gender, int age, AccountType accountType){

   try{

       if(UserExiste(username)){
           return false;
       }

       usersFile.seek(usersFile.length());

       usersFile.writeUTF(username);
       usersFile.writeUTF(password);
       usersFile.writeUTF(fullname);
       usersFile.writeUTF(gender.toString());
       usersFile.writeInt(age);
       usersFile.writeLong(System.currentTimeMillis());
       usersFile.writeUTF(AccountStatus.ACTIVE.toString());
       usersFile.writeUTF(accountType.toString());
       usersFile.writeUTF("image.jpg");

       CrearFolder(username);

       currentUser = new User(username,password,fullname,gender,age,accountType);
       currentUser.setCreationDate(System.currentTimeMillis());
       return true;

   }catch(IOException e){
       System.out.println("Error creando usuario");
   }

   return false;
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
            if(!userFolder.exists()){
                return false;
            }
        currentUser = u;
        return true;
        }
        return false;
    }
    

 private void CrearFolder(String username){
    File base = new File(BASE_FOLDER);
    if(!base.exists()) base.mkdir();

    File userFolder = new File(base, username);
    userFolder.mkdir();

    new File(userFolder, "profile").mkdir();
    new File(userFolder, "stickers").mkdir();
    new File(userFolder, "posts").mkdir();
    new File(userFolder, "imagenes").mkdir();
    new File(userFolder, "folders_personales").mkdir();
    new File(userFolder, "stickers_personales").mkdir();

    try {
        new File(userFolder, "followers.ins").createNewFile();
        new File(userFolder, "following.ins").createNewFile();
        new File(userFolder, "inbox.ins").createNewFile();
        new File(userFolder, "insta.ins").createNewFile();
        new File(userFolder, "stickers.ins").createNewFile();
    } catch(IOException e){
        System.out.println("Error creando archivos internos: " + e.getMessage());
    }
}
    
    
    public void setFotoPerfil(String username, String rutaFoto){
    try {
        File tempFile = new File(BASE_FOLDER + "/users_temp.ins");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw");
        
        usersFile.seek(0);
        
        while(usersFile.getFilePointer() < usersFile.length()) {
            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();
            
            if(user.equals(username)) {
                profile = rutaFoto; 
            }
            
            temp.writeUTF(user);
            temp.writeUTF(pass);
            temp.writeUTF(fullname);
            temp.writeUTF(gender);
            temp.writeInt(age);
            temp.writeLong(date);
            temp.writeUTF(status);
            temp.writeUTF(type);
            temp.writeUTF(profile);
        }
        
        usersFile.close();
        temp.close();
        
        File original = new File(BASE_FOLDER + "/users.ins");
        original.delete();
        tempFile.renameTo(original);
        
        usersFile = new RandomAccessFile(BASE_FOLDER + "/users.ins", "rw");
        
        User u = buscarUser(username);
        if(u != null) {
            u.setProfilePath(rutaFoto);
        }
        
    } catch(IOException e) {
        System.out.println("Error guardando foto de perfil: " + e.getMessage());
    }
}
    
    public User[] buscarUsuariosCoincidentes(String texto){
    User[] encontrados = new User[50]; 
    int total = 0;

    try{
        usersFile.seek(0);

        while(usersFile.getFilePointer() < usersFile.length()){
            String username = usersFile.readUTF();
            String password = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();

            if(AccountStatus.valueOf(status) != AccountStatus.ACTIVE){
                continue;
            }

            if(username.toLowerCase().contains(texto.toLowerCase())){
                User u = new User(username,password,fullname,
                        Gender.valueOf(gender), age,
                        AccountType.valueOf(type));
                u.setStatus(AccountStatus.valueOf(status));
                u.setProfilePath(profile);

                encontrados[total] = u;
                total++;
            }
        }

    }catch(IOException e){
        System.out.println("Error buscando coincidencias");
    }

    User[] resultado = new User[total];
    for(int i=0;i<total;i++){
        resultado[i] = encontrados[i];
    }

    return resultado;
}
    
    public boolean eliminarUsuario(String username){
    boolean encontrado = false;
    try{
        File tempFile = new File(BASE_FOLDER + "/users_temp.ins");
        RandomAccessFile temp = new RandomAccessFile(tempFile,"rw");

        usersFile.seek(0);

        while(usersFile.getFilePointer() < usersFile.length()){
            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();

            if(!user.equals(username)){
                temp.writeUTF(user);
                temp.writeUTF(pass);
                temp.writeUTF(fullname);
                temp.writeUTF(gender);
                temp.writeInt(age);
                temp.writeLong(date);
                temp.writeUTF(status);
                temp.writeUTF(type);
                temp.writeUTF(profile);
            } else {
                long deletionDate = System.currentTimeMillis();
                System.out.println("Usuario " + username + " eliminado el: " + deletionDate);
                File userFolder = new File(BASE_FOLDER+"/"+username);
                borrarCarpeta(userFolder);
                encontrado = true;
            }
        }

        usersFile.close();
        temp.close();

        File original = new File(BASE_FOLDER + "/users.ins");
        original.delete();
        tempFile.renameTo(original);

        usersFile = new RandomAccessFile(BASE_FOLDER+"/users.ins","rw");

    }catch(IOException e){
        System.out.println("Error eliminando usuario");
    }

    return encontrado;
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
    
    
    public boolean cambiarPassword(String username, String newPassword) throws IOException{
    boolean modificado = false;
     usersFile.close();
        usersFile = new RandomAccessFile(BASE_FOLDER + "/users.ins", "rw");
    try {
        File tempFile = new File(BASE_FOLDER + "/users_temp.ins");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw");

        usersFile.seek(0);

        while(usersFile.getFilePointer() < usersFile.length()) {
            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();

            if(user.equals(username)) {
                pass = newPassword; // cambio persistente
                modificado = true;
            }

            // Escribimos TODO en el archivo temporal
            temp.writeUTF(user);
            temp.writeUTF(pass);
            temp.writeUTF(fullname);
            temp.writeUTF(gender);
            temp.writeInt(age);
            temp.writeLong(date);
            temp.writeUTF(status);
            temp.writeUTF(type);
            temp.writeUTF(profile);
        }

        // Cerramos archivos
        usersFile.close();
        temp.close();

        // Reemplazamos archivo original
        File original = new File(BASE_FOLDER + "/users.ins");
        original.delete();
        tempFile.renameTo(original);

        // Reabrimos usersFile
        usersFile = new RandomAccessFile(BASE_FOLDER + "/users.ins", "rw");

        // Actualizamos currentUser desde el archivo
        if(currentUser != null && currentUser.getUsername().equals(username)){
            currentUser = buscarUser(username); // 💡 esta línea garantiza que la contraseña sea la nueva
        }

    } catch(IOException e) {
        System.out.println("Error cambiando password: " + e.getMessage());
    }

    return modificado;
}
  
    public boolean cambiarUsername(String usernameA, String newUsername) throws IOException{
    if(UserExiste(newUsername)){
        return false;
    }
    boolean modificado = false;
    try{
        File tempFile = new File(BASE_FOLDER + "/users_temp.ins");
        RandomAccessFile temp = new RandomAccessFile(tempFile,"rw");

        usersFile.seek(0);

        while(usersFile.getFilePointer() < usersFile.length()){
            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();

            if(user.equals(usernameA)){
                user = newUsername;
                modificado = true;
                // renombrar carpeta física
                File oldFolder = new File(BASE_FOLDER+"/"+usernameA);
                File newFolder = new File(BASE_FOLDER+"/"+newUsername);
                oldFolder.renameTo(newFolder);
                
            if(!profile.isEmpty()) {
                String newProfilePath = profile.replace(usernameA, newUsername);
                profile = newProfilePath;
            }
            }

            temp.writeUTF(user);
            temp.writeUTF(pass);
            temp.writeUTF(fullname);
            temp.writeUTF(gender);
            temp.writeInt(age);
            temp.writeLong(date);
            temp.writeUTF(status);
            temp.writeUTF(type);
            temp.writeUTF(profile);
        }

        usersFile.close();
        temp.close();

        File original = new File(BASE_FOLDER + "/users.ins");
        original.delete();
        tempFile.renameTo(original);

        usersFile = new RandomAccessFile(BASE_FOLDER+"/users.ins","rw");

    }catch(IOException e){
        System.out.println("Error cambiando username");
    }

    return modificado;
}
    
    public boolean cambiarEdad(String username, int newAge) throws IOException{
    boolean modificado = false;
    usersFile.close();
    usersFile = new RandomAccessFile(BASE_FOLDER + "/users.ins", "rw");
    try{
        File tempFile = new File(BASE_FOLDER + "/users_temp.ins");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw");

        usersFile.seek(0);

        while(usersFile.getFilePointer() < usersFile.length()){
            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();

            if(user.equals(username)){
                age = newAge; // cambio persistente
                modificado = true;
            }

            temp.writeUTF(user);
            temp.writeUTF(pass);
            temp.writeUTF(fullname);
            temp.writeUTF(gender);
            temp.writeInt(age);
            temp.writeLong(date);
            temp.writeUTF(status);
            temp.writeUTF(type);
            temp.writeUTF(profile);
        }

        usersFile.close();
        temp.close();

        File original = new File(BASE_FOLDER + "/users.ins");
        original.delete();
        tempFile.renameTo(original);

        usersFile = new RandomAccessFile(BASE_FOLDER + "/users.ins","rw");

    } catch(IOException e){
        System.out.println("Error cambiando edad: " + e.getMessage());
    }
    return modificado;
}
    
    
    public boolean cambiarTipoCuenta(String username, AccountType tipo) throws IOException{
    boolean modificado = false;
    usersFile.close();
    usersFile = new RandomAccessFile(BASE_FOLDER + "/users.ins", "rw");
    try{
        File tempFile = new File(BASE_FOLDER + "/users_temp.ins");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw");

        usersFile.seek(0);

        while(usersFile.getFilePointer() < usersFile.length()){
            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();

            if(user.equals(username)){
                type = tipo.toString(); 
                modificado = true;
            }

            temp.writeUTF(user);
            temp.writeUTF(pass);
            temp.writeUTF(fullname);
            temp.writeUTF(gender);
            temp.writeInt(age);
            temp.writeLong(date);
            temp.writeUTF(status);
            temp.writeUTF(type);
            temp.writeUTF(profile);
        }

        usersFile.close();
        temp.close();

        File original = new File(BASE_FOLDER + "/users.ins");
        original.delete();
        tempFile.renameTo(original);

        usersFile = new RandomAccessFile(BASE_FOLDER+"/users.ins","rw");

    } catch(IOException e){
        System.out.println("Error cambiando tipo de cuenta: " + e.getMessage());
    }
    return modificado;
}
    
    public boolean desactivarCuenta(String username) throws IOException{
        usersFile.close();
    usersFile = new RandomAccessFile(BASE_FOLDER + "/users.ins", "rw");
    boolean modificado = false;
    try{
        File tempFile = new File(BASE_FOLDER + "/users_temp.ins");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw");

        usersFile.seek(0);

        while(usersFile.getFilePointer() < usersFile.length()){
            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();

            if(user.equals(username)){
                status = AccountStatus.INACTIVE.toString(); 
                modificado = true;
            }

            temp.writeUTF(user);
            temp.writeUTF(pass);
            temp.writeUTF(fullname);
            temp.writeUTF(gender);
            temp.writeInt(age);
            temp.writeLong(date);
            temp.writeUTF(status);
            temp.writeUTF(type);
            temp.writeUTF(profile);
        }

        usersFile.close();
        temp.close();

        File original = new File(BASE_FOLDER + "/users.ins");
        original.delete();
        tempFile.renameTo(original);

        usersFile = new RandomAccessFile(BASE_FOLDER+"/users.ins","rw");

    } catch(IOException e){
        System.out.println("Error desactivando cuenta: " + e.getMessage());
    }
    return modificado;
}
    
   public boolean activarCuenta(String username) throws IOException{
       usersFile.close();
usersFile = new RandomAccessFile(BASE_FOLDER + "/users.ins", "rw");
    boolean modificado = false;
    try{
        File tempFile = new File(BASE_FOLDER + "/users_temp.ins");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw");

        usersFile.seek(0);

        while(usersFile.getFilePointer() < usersFile.length()){
            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();

            if(user.equals(username)){
                status = AccountStatus.ACTIVE.toString(); // cambio persistente
                modificado = true;
            }

            temp.writeUTF(user);
            temp.writeUTF(pass);
            temp.writeUTF(fullname);
            temp.writeUTF(gender);
            temp.writeInt(age);
            temp.writeLong(date);
            temp.writeUTF(status);
            temp.writeUTF(type);
            temp.writeUTF(profile);
        }

        usersFile.close();
        temp.close();

        File original = new File(BASE_FOLDER + "/users.ins");
        original.delete();
        tempFile.renameTo(original);

        usersFile = new RandomAccessFile(BASE_FOLDER+"/users.ins","rw");

    } catch(IOException e){
        System.out.println("Error activando cuenta: " + e.getMessage());
    }
    return modificado;
} 
    
    public void limpiarUsuariosHuerfanos() {
    try {
        File tempFile = new File(BASE_FOLDER + "/users_temp.ins");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw");
        
        usersFile.seek(0);
        
        while(usersFile.getFilePointer() < usersFile.length()) {
            String user = usersFile.readUTF();
            String pass = usersFile.readUTF();
            String fullname = usersFile.readUTF();
            String gender = usersFile.readUTF();
            int age = usersFile.readInt();
            long date = usersFile.readLong();
            String status = usersFile.readUTF();
            String type = usersFile.readUTF();
            String profile = usersFile.readUTF();
            
            File userFolder = new File(BASE_FOLDER + "/" + user);
            
            if(userFolder.exists()) {
                temp.writeUTF(user);
                temp.writeUTF(pass);
                temp.writeUTF(fullname);
                temp.writeUTF(gender);
                temp.writeInt(age);
                temp.writeLong(date);
                temp.writeUTF(status);
                temp.writeUTF(type);
                temp.writeUTF(profile);
            }
        }
        
        usersFile.close();
        temp.close();
        
        File original = new File(BASE_FOLDER + "/users.ins");
        original.delete();
        tempFile.renameTo(original);
        
        usersFile = new RandomAccessFile(BASE_FOLDER + "/users.ins", "rw");
        
    } catch(IOException e) {
        System.out.println("Error limpiando usuarios huérfanos: " + e.getMessage());
    }
}
}
    
    
    
    
 

