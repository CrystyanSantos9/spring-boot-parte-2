package med.voll.api.infra.initaldata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private static class SenhaUtils {
        public static String gerarBcryp(String senha) {
            if (senha == null) {
                return senha;
            }

            BCryptPasswordEncoder bCryptEnconder = new BCryptPasswordEncoder();
            return bCryptEnconder.encode(senha);
        }
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Insert Initial user
        Usuario usuario = new Usuario();

        String senhaEnconded = SenhaUtils.gerarBcryp("123456");
        usuario.setLogin("admin");
        usuario.setPassword(senhaEnconded);

        UserDetails usarioAlredyExists = usuarioRepository.findByLogin(usuario.getLogin());

        try {
            if (usarioAlredyExists != null) {
                throw new Exception("User alredy exists");
            } else {
                usuarioRepository.save(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
