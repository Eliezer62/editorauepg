package br.uepg.deinfo.editorauepg.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.uepg.deinfo.editorauepg.Repository.ArtigoRepository;
import br.uepg.deinfo.editorauepg.Model.Artigo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")

public class ArtigoController 
{
    @Autowired
    ArtigoRepository rep;

    /**
     * Retorna os todos os artigos disponiveis
     * GET /api/artigos listar artigos
     * @param titulo Titulo do artigo - opcional params
     * @return List<Artigo> Lista de Artigos - se vazio retorna NO_CONTENT HTTP CODE
     */

    @GetMapping("/artigos")
    public ResponseEntity<List<Artigo>> getAllArtigos(@RequestParam(required = false) String titulo)
    {
        List<Artigo> ListaArtigos = new ArrayList<Artigo>();
        

        if(titulo==null) rep.findAll().forEach(ListaArtigos::add);
        else
        {
            rep.findByTituloContaining(titulo).forEach(ListaArtigos::add);
        }
        if(ListaArtigos.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        else 
            return new ResponseEntity<List<Artigo>>(ListaArtigos, HttpStatus.OK);
    }


    @GetMapping("/artigos/{id}")
    public ResponseEntity<Artigo> getArtigobyId(@PathVariable Long id) 
    {
        Optional<Artigo> artigo = rep.findById(id);
        if(!artigo.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(artigo.get(), HttpStatus.OK);
    }
    
    /**
     * Post para salvar artigos no banco
     * 
     * @param artigo Data da requisicao POST
     * @return HTTP Status
     */
    
    @PostMapping("/artigos")
    public ResponseEntity<Artigo> postArtigos( @RequestBody Artigo artigo) 
    {
        try
        {
            Artigo salvo = rep.save(artigo);
            return new ResponseEntity<>(salvo,HttpStatus.CREATED);
        }
        catch(RuntimeException en)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch(Exception en)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/artigos/{id}")
    public ResponseEntity<Artigo> putArtigo(@PathVariable Long id, @RequestBody Artigo ant) {
        Optional<Artigo> artigo = rep.findById(id);
        if(artigo.isPresent())
        {
            Artigo copia = artigo.get();
            copia.setTitulo(ant.getTitulo());
            copia.setResumo(ant.getResumo());
            copia.setPublicado(ant.isPublicado());
            rep.save(copia);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/artigos")
    public ResponseEntity<HttpStatus> deleteAllArtigo()
    {
        rep.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/artigos/{id}")
    public ResponseEntity<HttpStatus> deleteArtigo(@PathVariable Long id)
    {
        rep.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    
}
