
package Instagram_login_user;


public class Followers_Manager {
   
    private static final String BASE_FOLDER = "INSTA_RAIZ";
    
    public boolean seguir(String follower, String seguido, Login_Manager loginManager) {
        if (estaSiguiendo(follower, seguido)) return false;

        User userSeguido = loginManager.buscarUser(seguido);
        if (userSeguido == null) return false;

        // Si es privada, manda solicitud
        if (userSeguido.getAccountType() == AccountType.PRIVATE) {
            if (tieneSolicitudPendiente(follower, seguido)) return false;
            return agregarLinea(getSolicitudesPath(seguido), follower);
        }

        // Si es publica, seguir directo
        agregarLinea(getFollowingPath(follower), seguido);
        agregarLinea(getFollowersPath(seguido), follower);
        return true;
    } 
    
}
