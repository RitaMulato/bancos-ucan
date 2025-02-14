package edu.ucan.sdp2.bancoengenhariaservice.models;

import edu.ucan.sdp2.bancoengenhariaservice.models.generics.GenericId;
import edu.ucan.sdp2.conectacore.enums.Moeda;
import edu.ucan.sdp2.conectacore.enums.StatusTransacao;
import edu.ucan.sdp2.conectacore.enums.TipoOperacao;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transacoes")
public class Transacao extends GenericId {


    private String contaDestino;
    @ManyToOne
    private Movimento movimento;
    private TipoOperacao tipoOperacao;
    @ManyToOne
    private Utilizador operador;
    @Enumerated
    private StatusTransacao statusTransacao;


}
