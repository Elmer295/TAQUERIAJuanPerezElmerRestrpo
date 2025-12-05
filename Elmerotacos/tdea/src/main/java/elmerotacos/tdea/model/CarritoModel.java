package elmerotacos.tdea.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items_carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",length = 50, nullable = false)
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_tacos_id", nullable = false)
    private TacoModel taco;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false)
    private int cantidad;
   
     public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setUsuario(String numero) { this.numero = numero; }

    public TacoModel getTacoModel() { return taco; }
    public void setOpcion(TacoModel taco) { this.taco = taco; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }
    
}
