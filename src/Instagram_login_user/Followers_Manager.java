
package Instagram_login_user;

import Instagram_login_user.Base_cuenta.AccountType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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
    
    public boolean dejarDeSeguir(String follower, String seguido) {
        boolean a = eliminarLinea(getFollowingPath(follower), seguido);
        boolean b = eliminarLinea(getFollowersPath(seguido), follower);
        return a && b;
    }

    public boolean estaSiguiendo(String follower, String seguido) {
        return contieneLinea(getFollowingPath(follower), seguido);
    }

    public boolean tieneSolicitudPendiente(String follower, String seguido) {
        return contieneLinea(getSolicitudesPath(seguido), follower);
    }
    
    
    public boolean aceptarSolicitud(String follower, String seguido) {
        if (!tieneSolicitudPendiente(follower, seguido)) return false;
        eliminarLinea(getSolicitudesPath(seguido), follower);
        agregarLinea(getFollowingPath(follower), seguido);
        agregarLinea(getFollowersPath(seguido), follower);
        return true;
    }

    public boolean rechazarSolicitud(String follower, String seguido) {
        return eliminarLinea(getSolicitudesPath(seguido), follower);
    }

    public String[] getFollowers(String username) {
        return leerLineas(getFollowersPath(username));
    }
    
     public String[] getFollowing(String username) {
        return leerLineas(getFollowingPath(username));
    }

    public String[] getSolicitudes(String username) {
        return leerLineas(getSolicitudesPath(username));
    }

    public int contarFollowers(String username) {
        return getFollowers(username).length;
    }

    public int contarFollowing(String username) {
        return getFollowing(username).length;
    }
    
    private String getFollowersPath(String username) {
        return BASE_FOLDER + "/" + username + "/followers.ins";
    }

    private String getFollowingPath(String username) {
        return BASE_FOLDER + "/" + username + "/following.ins";
    }

    private String getSolicitudesPath(String username) {
        // Crear el archivo si no existe
        File f = new File(BASE_FOLDER + "/" + username + "/solicitudes.ins");
        if (!f.exists()) {
            try { f.createNewFile(); } catch (IOException e) {}
        }
        return f.getPath();
    }

    // ── UTILIDADES DE ARCHIVO ──
    private boolean agregarLinea(String path, String valor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(valor);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error escribiendo en " + path + ": " + e.getMessage());
            return false;
        }
    }

    private boolean eliminarLinea(String path, String valor) {
        File file = new File(path);
        if (!file.exists()) return false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null) {
                if (linea.equals(valor) && !encontrado) {
                    encontrado = true;
                } else {
                    sb.append(linea).append(System.lineSeparator());
                }
            }
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
            bw.close();
            return encontrado;
        } catch (IOException e) {
            System.out.println("Error eliminando linea: " + e.getMessage());
            return false;
        }
    }

    private boolean contieneLinea(String path, String valor) {
        File file = new File(path);
        if (!file.exists()) return false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equals(valor)) return true;
            }
        } catch (IOException e) {
            System.out.println("Error leyendo " + path);
        }
        return false;
    }

    private String[] leerLineas(String path) {
        File file = new File(path);
        if (!file.exists()) return new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String[] temp = new String[500];
            int total = 0;
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    temp[total++] = linea.trim();
                }
            }
            String[] resultado = new String[total];
            for (int i = 0; i < total; i++) resultado[i] = temp[i];
            return resultado;
        } catch (IOException e) {
            System.out.println("Error leyendo lineas: " + e.getMessage());
            return new String[0];
        }
    }
    
    
    
}
