
package Instagram_login_user;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Dell
 */
public class Post_Manager {
  
   private static final String BASE_FOLDER = "INSTA_RAIZ";
   private static final String POST_FILE = BASE_FOLDER+"/posts.ins";
   private RandomAccessFile postsFile;
   
   public Post_Manager(){
       File base = new File(BASE_FOLDER);
       if(!base.exists()) base.mkdir();
       
       try{
         postsFile = new RandomAccessFile(POST_FILE,"rw");  
       }catch(IOException e){
           System.out.println("Error abriendo posts.ins"+e.getMessage());
       }
   }
   
   
   public boolean crearPost(String username, String contenido, String hashtags, String menciones, String rutaImagen, String tipoMedia){
       
   }
    
    
    
}
