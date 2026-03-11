
package Instagram_login_user;

import Instagram_login_user.Base_cuenta.AccountType;
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
       
       try{
            postsFile.seek(postsFile.length());
            postsFile.writeUTF(username);
            postsFile.writeUTF(contenido);
            postsFile.writeUTF(hashtags);
            postsFile.writeUTF(menciones);
            postsFile.writeUTF(rutaImagen);
            postsFile.writeUTF(tipoMedia);
            postsFile.writeLong(System.currentTimeMillis());
            return true;    
       }catch(IOException e){
           System.out.println("Error creando post: "+e.getMessage());
           return false;
       }
   }
    

    public Post[] getPostsDeUsuario(String username) {
        Post[] temp = new Post[500];
        int total = 0;
        try {
            postsFile.seek(0);
            while (postsFile.getFilePointer() < postsFile.length()) {
                String user = postsFile.readUTF();
                String contenido = postsFile.readUTF();
                String hashtags = postsFile.readUTF();
                String menciones = postsFile.readUTF();
                String rutaImagen = postsFile.readUTF();
                String tipoMedia = postsFile.readUTF();
                long fecha = postsFile.readLong();

                if (user.equals(username)) {
                    temp[total++] = new Post(user, contenido, hashtags, menciones, rutaImagen, tipoMedia, fecha);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo posts: " + e.getMessage());
        }

        // Ordenar de más reciente a más antiguo
        Post[] resultado = new Post[total];
        for (int i = 0; i < total; i++) resultado[i] = temp[i];
        ordenarPorFecha(resultado);
        return resultado;
    }

    public Post[] getPostsFeed(String usuarioLogueado, Followers_Manager followersManager, Login_Manager loginManager) {
        Post[] temp = new Post[1000];
        int total = 0;

        String[] siguiendo = followersManager.getFollowing(usuarioLogueado);

        try {
            postsFile.seek(0);
            while (postsFile.getFilePointer() < postsFile.length()) {
                String user = postsFile.readUTF();
                String contenido = postsFile.readUTF();
                String hashtags = postsFile.readUTF();
                String menciones = postsFile.readUTF();
                String rutaImagen = postsFile.readUTF();
                String tipoMedia = postsFile.readUTF();
                long fecha = postsFile.readLong();

                // Incluir si es propio
                if (user.equals(usuarioLogueado)) {
                    temp[total++] = new Post(user, contenido, hashtags, menciones, rutaImagen, tipoMedia, fecha);
                    continue;
                }

                // Verificar si lo sigue
                boolean loSigue = false;
                for (String s : siguiendo) {
                    if (s.equals(user)) { loSigue = true; break; }
                }
                if (!loSigue) continue;

                // Verificar privacidad
                User u = loginManager.buscarUser(user);
                if (u == null) continue;
                if (u.getAccountType() == AccountType.PRIVATE && !loSigue) continue;

                temp[total++] = new Post(user, contenido, hashtags, menciones, rutaImagen, tipoMedia, fecha);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo feed: " + e.getMessage());
        }

        Post[] resultado = new Post[total];
        for (int i = 0; i < total; i++) resultado[i] = temp[i];
        ordenarPorFecha(resultado);
        return resultado;
    }
    
     private void ordenarPorFecha(Post[] posts) {
        // Bubble sort descendente por fecha
        for (int i = 0; i < posts.length - 1; i++) {
            for (int j = 0; j < posts.length - i - 1; j++) {
                if (posts[j].getFecha() < posts[j + 1].getFecha()) {
                    Post temp = posts[j];
                    posts[j] = posts[j + 1];
                    posts[j + 1] = temp;
                }
            }
        }
    }
    
    
}
