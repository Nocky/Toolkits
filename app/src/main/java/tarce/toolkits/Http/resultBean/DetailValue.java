package tarce.toolkits.Http.resultBean;

import java.io.Serializable;

/**
 * Created by Daniel.Xu on 2017/4/19.
 */

public class DetailValue implements Serializable {
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getManson() {
        return manson;
    }

    public void setManson(String manson) {
        this.manson = manson;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getLast_sync_device_id() {
        return last_sync_device_id;
    }

    public void setLast_sync_device_id(String last_sync_device_id) {
        this.last_sync_device_id = last_sync_device_id;
    }
    private String user_id ;
    private String nickname ;
    private String manson ;
    private String user_mail ;
    private String user_mobile ;
    private String last_sync_device_id ;

}
