package top.aiteky.onebook.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_err
 * @author 
 */
public class SysErr implements Serializable {
    private Integer id;

    private String message;

    private Date datetime;

    private String clazz;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysErr other = (SysErr) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getDatetime() == null ? other.getDatetime() == null : this.getDatetime().equals(other.getDatetime()))
            && (this.getClazz() == null ? other.getClazz() == null : this.getClazz().equals(other.getClazz()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getDatetime() == null) ? 0 : getDatetime().hashCode());
        result = prime * result + ((getClazz() == null) ? 0 : getClazz().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", message=").append(message);
        sb.append(", datetime=").append(datetime);
        sb.append(", clazz=").append(clazz);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}