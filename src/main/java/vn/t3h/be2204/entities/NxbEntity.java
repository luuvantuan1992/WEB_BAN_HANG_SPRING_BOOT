package vn.t3h.be2204.entities;

import javax.persistence.*;

@Entity
@Table(name = "nxb", schema = "2204", catalog = "")
public class NxbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "ADDRESS")
    private String address;
    @Basic
    @Column(name = "MA_NXB")
    private String maNxb;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaNxb() {
        return maNxb;
    }

    public void setMaNxb(String maNxb) {
        this.maNxb = maNxb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NxbEntity nxbEntity = (NxbEntity) o;

        if (id != nxbEntity.id) return false;
        if (name != null ? !name.equals(nxbEntity.name) : nxbEntity.name != null) return false;
        if (address != null ? !address.equals(nxbEntity.address) : nxbEntity.address != null) return false;
        if (maNxb != null ? !maNxb.equals(nxbEntity.maNxb) : nxbEntity.maNxb != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (maNxb != null ? maNxb.hashCode() : 0);
        return result;
    }
}
