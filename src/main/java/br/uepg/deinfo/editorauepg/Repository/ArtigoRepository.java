package br.uepg.deinfo.editorauepg.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uepg.deinfo.editorauepg.Model.Artigo;


public interface ArtigoRepository extends JpaRepository<Artigo, Long>
{
    
    List<Artigo> findByPublicado(boolean publicado);

    List<Artigo> findByTituloContaining(String titulo);
}