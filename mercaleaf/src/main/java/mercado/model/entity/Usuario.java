package mercado.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_usuario")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private String direccion;

    private String nombreUsuario;

    private String password;

    private String foto;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    //	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
//	@JsonManagedReference
//	@JsonIgnore
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<PedidoUsuario> pedidoUsuario = new ArrayList<>();

    @ColumnDefault("true")
    private Boolean disponibilidadDeConsulta;


    // FOTO
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//	@JsonBackReference
    @JsonIgnore
    private List<ImageModel> fotos;

    public Usuario() {
        pedidoUsuario = new ArrayList<PedidoUsuario>();
    }

    public Usuario(String nombre, String apellido, String email) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    @PrePersist
    public void prePersist() {
        createAt = new Date();

    }

    public void setPedidoUsuario(PedidoUsuario pedidoUsuario) {
        this.pedidoUsuario.add(pedidoUsuario);
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", createAt=" + createAt +
                ", rol=" + rol +
                ", direccion='" + direccion + '\'' +
                ", pedidoUsuario=" + pedidoUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                ", foto='" + foto + '\'' +
                ", disponibilidadDeConsulta=" + disponibilidadDeConsulta +
                ", fotos=" + fotos +
                '}';
    }
}
