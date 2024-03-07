package br.uepg.deinfo.editorauepg.Model;

//Importacoes
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * Classe Artigo do modelo
 */

@Entity
@Table(name="artigo")
public class Artigo 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String titulo;

    @Column
    private String resumo;

    @Column
    private boolean publicado;

    /**
     * Construtor Artigo
     * @param titulo Titulo do artigo
     * @param resumo Resumo do artigo
     * @param publicado Se o artigo jah foi publicado
     */

    public Artigo(String titulo, String resumo, boolean publicado)
    {
        this.titulo = titulo;
        this.resumo = resumo;
        this.publicado = publicado;
    }


    //getters e setters
    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return this.resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public boolean isPublicado() {
        return this.publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

}
