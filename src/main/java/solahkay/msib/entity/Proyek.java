package solahkay.msib.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "proyek")
public class Proyek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_proyek", nullable = false)
    private String namaProyek;

    @Column(name = "client", nullable = false)
    private String client;

    @Column(name = "tgl_mulai")
    private LocalDateTime tglMulai;

    @Column(name = "tgl_selesai")
    private LocalDateTime tglSelesai;

    @Column(name = "pimpinan_proyek", nullable = false)
    private String pimpinanProyek;

    @Column(name = "keterangan", columnDefinition = "TEXT")
    private String keterangan;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "proyek_lokasi",
            joinColumns = @JoinColumn(name = "proyek_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lokasi_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Lokasi> lokasi;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy ?
                hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy ?
                hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Proyek proyek = (Proyek) o;
        return getId() != null && Objects.equals(getId(), proyek.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy hibernateProxy ?
                hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }

}
