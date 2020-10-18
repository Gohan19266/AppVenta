package learn.mode.appventa.model;

public class User {
    private String _id;
    private String username;
    private String password;
    private String rol;
    private String token;

    public User(String _id, String username, String password, String rol) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }
}
