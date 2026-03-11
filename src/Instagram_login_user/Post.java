
package Instagram_login_user;

public class Post {
 private String username;
 private String contenido;
 private String hashtags;
 private String menciones;
 private String rutaImagen;
 private String tipoMedia;
 private long fecha;
 
  public Post(String username, String contenido, String hashtags, String menciones, String rutaImange, String tipoMedia, long fecha){
      this.username=username;
      this.contenido=contenido;
      this.hashtags=hashtags;
      this.menciones=menciones;
      this.rutaImagen=rutaImagen;
      this.tipoMedia=tipoMedia;
      this.fecha=fecha;
  }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public String getMenciones() {
        return menciones;
    }

    public void setMenciones(String menciones) {
        this.menciones = menciones;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getTipoMedia() {
        return tipoMedia;
    }

    public void setTipoMedia(String tipoMedia) {
        this.tipoMedia = tipoMedia;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }
  
  
    
 
}
