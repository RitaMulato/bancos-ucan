package edu.ucan.sdp2.bancoengenhariaservice.repositories;


import edu.ucan.sdp2.bancoengenhariaservice.enums.Status;
import edu.ucan.sdp2.bancoengenhariaservice.models.Banco;
import edu.ucan.sdp2.bancoengenhariaservice.models.TelefoneVerificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BancoRepository extends JpaRepository<Banco, UUID> {

}
