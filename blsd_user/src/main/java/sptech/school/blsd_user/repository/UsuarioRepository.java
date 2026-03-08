package sptech.school.blsd_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.blsd_user.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
